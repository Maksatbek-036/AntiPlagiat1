package com.example.myapplication


import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileInputStream

object DocReader {

    // Чтение старых .doc файлов


    // Чтение современных .docx файлов
    fun readDocx(file: File): String {
        val fis = FileInputStream(file)
        val docx = XWPFDocument(fis)
        val text = docx.paragraphs.joinToString("\n") { it.text }
        docx.close()
        fis.close()
        return text
    }
}


