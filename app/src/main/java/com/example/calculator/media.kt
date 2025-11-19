package com.example.calculator
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import android.os.Environment
import android.Manifest
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class media : AppCompatActivity() {
    lateinit var song_name: TextView
    lateinit var current_time: TextView
    lateinit var total_time: TextView
    lateinit var song_list: ListView
    lateinit var seekBar: SeekBar
    lateinit var seekBar_Vol: SeekBar
    var musicFiles = mutableListOf<File>()
    lateinit var mediaPlayer: MediaPlayer
    var Play = false
    var song_poz = 0
    var handler = Handler()
    lateinit var audio_manager: AudioManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Разрешение получено", Toast.LENGTH_SHORT).show()
            loadMusicFiles()
            showMusicList()
        } else {
            Toast.makeText(this, "Нужно разрешение для доступа к музыке", Toast.LENGTH_LONG).show()
        }
    }

    fun loadMusicFiles() {
        val download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        if (download.exists()) {
            download.listFiles()?.forEach { file ->
                if (file.name.endsWith(".mp3")) {
                    musicFiles.add(file)
                }
            }
        }
    }

    fun showMusicList() {
        val adapter = ArrayAdapter(
            this,android.R.layout.simple_list_item_1, musicFiles.map {it.nameWithoutExtension })
        song_list.adapter =adapter
    }

    fun time(millis: Int): String {
        val total = millis/1000
        val min = total/60
        val sec = total%60
        return String.format("%d:%02d",min,sec)
    }
    fun updateSeekBar(){
        handler.postDelayed({
            if(mediaPlayer.isPlaying){
                val currentPos = mediaPlayer.currentPosition
                seekBar.progress = currentPos
                current_time.text = time(currentPos)
                updateSeekBar()
            }
        }, 1000)
    }

    fun setupVolumeSeekBar() {
        val max_Volume = audio_manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        val current_Volume = audio_manager.getStreamVolume(AudioManager.STREAM_MUSIC)

        seekBar_Vol.max = max_Volume
        seekBar_Vol.progress = current_Volume

        seekBar_Vol.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    audio_manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_media)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

        var button_play = findViewById<Button>(R.id.button_play)
        var button_stop = findViewById<Button>(R.id.button_stop)
        var button_pervios = findViewById<Button>(R.id.button_pervios)
        var button_next = findViewById<Button>(R.id.button_next)
        song_name = findViewById(R.id.song_name)
        current_time = findViewById(R.id.current_time)
        total_time = findViewById(R.id.total_time)
        seekBar = findViewById(R.id.seekBar)
        seekBar_Vol = findViewById(R.id.seekBar_Vol)
        song_list = findViewById(R.id.songs_ListView)
        mediaPlayer = MediaPlayer()
        audio_manager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        setupVolumeSeekBar()

        fun play_select_song(position: Int) {
            val file = musicFiles[position]
            song_poz = position
            mediaPlayer.reset()
            mediaPlayer.setDataSource(file.absolutePath)
            mediaPlayer.prepare()
            var d = mediaPlayer.duration
            seekBar.max = d
            total_time.text = time(d)
            song_name.text = file.nameWithoutExtension
            mediaPlayer.start()
            button_play.text = "⏸️"
            Play = true
            updateSeekBar()
        }
        fun play_pause(){
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                button_play.text="▶️"
                Play = false

            } else{
                mediaPlayer.start()
                button_play.text= "⏸️"
                Play = true
            }
        }
        fun stop(){
            mediaPlayer.stop()
            button_play.text="▶️"
            Play = false
            seekBar.progress = 0
            current_time.text = "0:00"
        }
        fun next(){
            if(song_poz<musicFiles.size-1){
                song_poz = song_poz+1
            }else{
                song_poz =0
            }
            play_select_song(song_poz)
        }
        fun pervios(){
            if(song_poz>0){
                song_poz = song_poz-1
            }else{
                song_poz = musicFiles.size-1
            }
            play_select_song(song_poz)
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                current_time.text = time(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                handler.removeCallbacksAndMessages(null)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mediaPlayer.seekTo(seekBar.progress)
                updateSeekBar()
                mediaPlayer.start()
            }
        })

        button_play.setOnClickListener { play_pause()}
        button_stop.setOnClickListener {stop()}
        button_pervios.setOnClickListener { pervios()}
        button_next.setOnClickListener { next()}
        song_list.setOnItemClickListener {parent, view, position, id ->
            play_select_song(position)
        }

    }
    override fun onResume() {
        super.onResume()
        if (::audio_manager.isInitialized) {
            val currentVolume = audio_manager.getStreamVolume(AudioManager.STREAM_MUSIC)
            seekBar_Vol.progress = currentVolume
        }
    }
}

