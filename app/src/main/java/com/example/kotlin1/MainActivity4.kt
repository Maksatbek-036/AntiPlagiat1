package com.example.kotlin1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty4)

        val backButton: Button = findViewById(R.id.bacc)
        backButton.setOnClickListener {
            finish()
        }


        val goButton: Button = findViewById(R.id.go)
        goButton.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
    }
}