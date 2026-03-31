package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // Получаем данные из MainActivity2
        val percent = intent.getIntExtra("percentage", 0)
        val words = intent.getIntExtra("wordCount", 0)
        val sources = intent.getIntExtra("sources", 0)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity4::class.java).apply {
                putExtra("percentage", percent)
                putExtra("wordCount", words)
                putExtra("sources", sources)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 1000)
    }
}
