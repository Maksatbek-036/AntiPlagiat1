package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButtonToggleGroup
import java.io.File

class ImportFragment : Fragment() {

    private val openFileLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(requireContext(), "Файл выбран: $it", Toast.LENGTH_SHORT).show()

            val file = File(uri.path ?: return@let)

            val text = when {
                file.name.endsWith(".docx") -> DocReader.readDocx(file)
                else -> ""
            }

            if (text.isNotEmpty()) {
                val corpus = listOf("Пример диплома", "Алгоритмы машинного обучения")
                val similarity = AntiPlagiarism.similarity(text, corpus[0], corpus)
                val percentage = (similarity * 100).toInt()
                val wordCount = text.split("\\s+".toRegex()).size

                val intent = Intent(requireContext(), MainActivity3::class.java).apply {
                    putExtra("percentage", percentage)
                    putExtra("wordCount", wordCount)
                    putExtra("sources", 3)
                }
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Неподдерживаемый формат файла", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_main2, container, false)

        val backbut: Button = view.findViewById(R.id.back1)
        backbut.setOnClickListener { activity?.finish() }

        val toggleGroup = view.findViewById<MaterialButtonToggleGroup>(R.id.tabs)
        val conText = view.findViewById<View>(R.id.cText)
        val conFile = view.findViewById<View>(R.id.cFile)
        val conURL = view.findViewById<View>(R.id.cURL)

        val inputText = view.findViewById<EditText>(R.id.inputText)
        val urlInput = view.findViewById<EditText>(R.id.inputUrl)

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

        val startButton = view.findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            when (toggleGroup.checkedButtonId) {
                R.id.bURL -> {
                    val url = urlInput.text.toString().trim()
                    if (url.isNotEmpty()) {
                        openWebPage(url)
                    } else {
                        Toast.makeText(requireContext(), "Введите адрес сайта", Toast.LENGTH_SHORT).show()
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

                        val intent = Intent(requireContext(), MainActivity3::class.java).apply {
                            putExtra("percentage", percentage)
                            putExtra("wordCount", wordCount)
                            putExtra("sources", 3)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "Введите текст", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
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
