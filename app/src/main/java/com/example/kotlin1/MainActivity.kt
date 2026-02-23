package com.example.kotlin1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment8, FirstFragment.newInstance())
                .commit()

    }
}