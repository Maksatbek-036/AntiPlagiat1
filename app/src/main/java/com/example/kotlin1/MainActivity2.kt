package com.example.kotlin1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButtonToggleGroup

class MainActivity2 : AppCompatActivity() {
     @SuppressLint("MissingInflatedId")
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

         val button = findViewById<Button>(R.id.startButton)
         button.setOnClickListener {
         val act3 = Intent(this, MainActivity3::class.java)
         startActivity(act3)}

         val backbut : Button = findViewById(R.id.back1)
         backbut.setOnClickListener { finish() }

        val toggleGroup = findViewById<MaterialButtonToggleGroup>(R.id.tabs)
        val conText = findViewById<View>(R.id.cText)
        val conFile = findViewById<View>(R.id.cFile)
        val conURL = findViewById<View>(R.id.cURL)

         conText.visibility= View.VISIBLE
         conFile.visibility = View.GONE
         conURL.visibility = View.GONE

        toggleGroup.addOnButtonCheckedListener {group,checkId,isChecked->
            if (isChecked){
                conText.visibility = View.GONE
                conFile.visibility = View.GONE
                conURL.visibility = View.GONE
                when(checkId){
                    R.id.bText->conText.visibility= View.VISIBLE
                    R.id.bFile->conFile.visibility= View.VISIBLE
                    R.id.bURL->conURL.visibility= View.VISIBLE
                }
            }
        }
    }
}