package com.example.to_doapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity4 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        // Find buttons by their IDs
        val addButton: ImageButton = findViewById(R.id.imageButton10)
        val dashboardButton: ImageButton = findViewById(R.id.imageButton7)

        // Set click listeners
        addButton.setOnClickListener(this)
        dashboardButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageButton10 -> {
                // Navigate to MainActivity7
                val intent = Intent(this, MainActivity7::class.java)
                startActivity(intent)
            }
            R.id.imageButton7 -> {
                // Navigate to MainActivity3
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
            // Handle other button clicks if any
        }
    }
}