package com.example.to_doapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val loginButton: Button = findViewById(R.id.button2)

        loginButton.setOnClickListener {
            // Start MainActivity3 when the login button is clicked
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
            finish() // Optional: Finish MainActivity2 if you don't want it in the back stack
        }
    }
}