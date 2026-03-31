package com.example.kotlin1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ProgressBar





class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty4)



        val percent = intent.getIntExtra("percentage", 0)
        val words = intent.getIntExtra("wordCount", 0)
        val sources = intent.getIntExtra("sources", 0)

        val tvUniquenessPercent = findViewById<TextView>(R.id.tv_uniqueness_percent)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val tvWarning = findViewById<TextView>(R.id.tv_warning)
        val tvSources = findViewById<TextView>(R.id.m) // количество источников
        val tvWords = findViewById<TextView>(R.id.md) // количество слов
        val tvPercent=findViewById<TextView>(R.id.tv_percent)
        val  tvPercent23=findViewById<TextView>(R.id.tv_percent23)
        val wordsCount=findViewById<TextView>(R.id.words_count)


        tvUniquenessPercent.text = "$percent%"
        progressBar.progress = percent
        tvWarning.text = if (percent < 70) "Обнаружены совпадения с внешними источниками." else "Текст уникален."
        tvSources.text = sources.toString()
        tvWords.text = words.toString()
        wordsCount.text="$words слов"
        tvPercent.text=tvUniquenessPercent.text
        tvPercent23.text=(100-percent).toString()


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