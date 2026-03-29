package com.example.kotlin1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity5)

        val bac = findViewById<Button>(R.id.butt5)

        bac.setOnClickListener {
            finish()
        }
    }
}