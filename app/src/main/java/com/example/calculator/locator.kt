package com.example.calculator

import android.Manifest
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.location.Location
import android.location.LocationManager


class locator : AppCompatActivity() {

    lateinit var Latitude: TextView
    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Разрешение 1 получено", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Нужно разрешение для доступа к локации", Toast.LENGTH_LONG).show()
        }
    }
    val requestPermissionLauncher2 = registerForActivityResult(ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Разрешение 2 получено", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this, "Нужно разрешение для доступа к локации", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_locator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        requestPermissionLauncher2.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        Latitude = findViewById(R.id.Latitude)



    }
}