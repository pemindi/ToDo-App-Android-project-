package com.example.to_doapplication

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Find the login button by its ID
        val loginButton: Button = findViewById(R.id.button2)

        // Set OnClickListener for the login button
        loginButton.setOnClickListener {
            // Create an Intent to start MainActivity3
            val intent = Intent(this, MainActivity3::class.java)

            // Start MainActivity3
            startActivity(intent)

            // Finish MainActivity2 (optional, depending on your navigation flow)
            finish()
        }
    }
}