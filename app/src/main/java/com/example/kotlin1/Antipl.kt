package com.example.kotlin1

import kotlin.math.ln
import kotlin.math.sqrt

object AntiPlagiarism {

    private fun tokenize(text: String): List<String> =
        text.lowercase().split("\\s+".toRegex())

    private fun termFrequency(words: List<String>): Map<String, Double> {
        val count = words.groupingBy { it }.eachCount()
        val total = words.size.toDouble()
        return count.mapValues { it.value / total }
    }

    private fun tfidfVector(text: String, corpus: List<String>): Map<String, Double> {
        val words = tokenize(text)
        val tf = termFrequency(words)

        val docCount = corpus.size.toDouble()
        val idf = corpus.flatMap { tokenize(it) }
            .groupingBy { it }
            .eachCount()
            .mapValues { ln(docCount / (1 + it.value)) }

        return tf.mapValues { (word, tfValue) -> tfValue * (idf[word] ?: 0.0) }
    }

    fun similarity(text1: String, text2: String, corpus: List<String>): Double {
        val vec1 = tfidfVector(text1, corpus)
        val vec2 = tfidfVector(text2, corpus)

        val allWords = vec1.keys + vec2.keys
        var dotProduct = 0.0
        var norm1 = 0.0
        var norm2 = 0.0

        for (word in allWords) {
            val x = vec1[word] ?: 0.0
            val y = vec2[word] ?: 0.0
            dotProduct += x * y
            norm1 += x * x
            norm2 += y * y
        }

        return if (norm1 == 0.0 || norm2 == 0.0) 0.0
        else dotProduct / (sqrt(norm1) * sqrt(norm2))
    }
}