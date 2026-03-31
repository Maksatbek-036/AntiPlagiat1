package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import java.io.File

class MainActivity2 : AppCompatActivity() {

    private val openFileLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(this, "Файл выбран: $it", Toast.LENGTH_SHORT).show()

            // Преобразуем Uri в File
            val file = File(uri.path ?: return@let)

            // Определяем расширение
            val text = when {
                file.name.endsWith(".docx") -> DocReader.readDocx(file)

                else -> ""
            }

            if (text.isNotEmpty()) {
                val corpus = listOf("Пример диплома", "Алгоритмы машинного обучения")
                val similarity = AntiPlagiarism.similarity(text, corpus[0], corpus)
                val percentage = (similarity * 100).toInt()
                val wordCount = text.split("\\s+".toRegex()).size

                val intent = Intent(this, MainActivity3::class.java).apply {
                    putExtra("percentage", percentage)
                    putExtra("wordCount", wordCount)
                    putExtra("sources", 3)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неподдерживаемый формат файла", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val backbut: Button = findViewById(R.id.back1)
        backbut.setOnClickListener { finish() }

        val toggleGroup = findViewById<MaterialButtonToggleGroup>(R.id.tabs)
        val conText = findViewById<View>(R.id.cText)
        val conFile = findViewById<View>(R.id.cFile)
        val conURL = findViewById<View>(R.id.cURL)

        val inputText = findViewById<EditText>(R.id.inputText)
        val urlInput = findViewById<EditText>(R.id.inputUrl)

        conText.visibility = View.VISIBLE
        conFile.visibility = View.GONE
        conURL.visibility = View.GONE

        toggleGroup.addOnButtonCheckedListener { _, checkId, isChecked ->
            if (isChecked) {
                conText.visibility = View.GONE
                conFile.visibility = View.GONE
                conURL.visibility = View.GONE
                when (checkId) {
                    R.id.bText -> conText.visibility = View.VISIBLE
                    R.id.bFile -> conFile.visibility = View.VISIBLE
                    R.id.bURL -> conURL.visibility = View.VISIBLE
                }
            }
        }

        conFile.setOnClickListener {
            openFileLauncher.launch("*/*")
        }

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            when (toggleGroup.checkedButtonId) {
                R.id.bURL -> {
                    val url = urlInput.text.toString().trim()
                    if (url.isNotEmpty()) {
                        openWebPage(url)
                    } else {
                        Toast.makeText(this, "Введите адрес сайта", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.bFile -> {
                    openFileLauncher.launch("*/*")
                }
                else -> {
                    val text = inputText.text.toString().trim()
                    if (text.isNotEmpty()) {
                        val corpus = listOf("Пример диплома", "Алгоритмы машинного обучения")
                        val similarity = AntiPlagiarism.similarity(text, corpus[0], corpus)
                        val percentage = (similarity * 100).toInt()
                        val wordCount = text.split("\\s+".toRegex()).size

                        val intent = Intent(this, MainActivity3::class.java).apply {
                            putExtra("percentage", percentage)
                            putExtra("wordCount", wordCount)
                            putExtra("sources", 3)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun openWebPage(url: String) {
        var finalUrl = url
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            finalUrl = "https://$url"
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
        startActivity(intent)
    }
}
