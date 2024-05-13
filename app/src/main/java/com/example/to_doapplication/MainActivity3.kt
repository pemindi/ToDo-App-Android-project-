package com.example.to_doapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Button

class MainActivity3 : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // Find buttons by their IDs
        val addButton: ImageButton = findViewById(R.id.imageButton)
        val dashboardButton: ImageButton = findViewById(R.id.imageButton2delete)
        val viewAllButton: ImageButton = findViewById(R.id.imageButton11)
        val mainButton: Button = findViewById(R.id.button)

        // Set click listeners
        addButton.setOnClickListener(this)
        dashboardButton.setOnClickListener(this)
        viewAllButton.setOnClickListener(this)
        mainButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageButton -> {
                // Navigate to MainActivity7
                val intent = Intent(this, MainActivity7::class.java)
                startActivity(intent)
            }
            R.id.imageButton2delete -> {
                // Reload the page (start current activity again)
                val intent = intent
                finish()
                startActivity(intent)
            }
            R.id.imageButton11 -> {
                // Navigate to MainActivity5
                val intent = Intent(this, MainActivity5::class.java)
                startActivity(intent)
            }

            R.id.button -> {
                // Navigate to MainActivity (Main)
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            }
            // Handle other button clicks if any
        }
    }
}