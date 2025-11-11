package com.example.calculator
import android.os.Bundle
import android.widget.Button
import android.net.Uri
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Button_calculator = findViewById<Button>(R.id.Button_сalculator)
        val Button_mediaplayer = findViewById<Button>(R.id.Button_mediaplayer)

        Button_calculator.setOnClickListener({
            startActivity(Intent(this, Calc::class.java))
        })

        Button_mediaplayer.setOnClickListener({
            startActivity(Intent(this, media::class.java))
        })
    }
}