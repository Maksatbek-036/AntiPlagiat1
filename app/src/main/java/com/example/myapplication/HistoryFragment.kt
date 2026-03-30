package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.DocumentAdapter
import com.example.myapplication.databinding.FragmentTestBinding
import com.example.myapplication.model.Document

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private lateinit var adapter: DocumentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadData()
    }

    private fun setupRecyclerView() {
        adapter = DocumentAdapter(emptyList())
        binding.recyclerViewDocuments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HistoryFragment.adapter
        }
    }

    private fun loadData() {
        val documents = listOf(
            Document(
                title = "Курсовая работа по...",
                date = "1 янв 2026",
                time = "14:32",
                words = "2 345 слов",
                percentage = 97
            ),
            Document(
                title = "Статья для публикации",
                date = "1 янв 2026",
                time = "09:15",
                words = "1 023 слов",
                percentage = 92
            ),
            Document(
                title = "Реферат по истории",
                date = "28 дек 2025",
                time = "16:45",
                words = "3 256 слов",
                percentage = 48
            ),
            Document(
                title = "Дипломная работа...",
                date = "25 дек 2025",
                time = "11:22",
                words = "8 932 слов",
                percentage = 65
            ),
            Document(
                title = "Эссе по философии",
                date = "20 дек 2025",
                time = "09:50",
                words = "1 876 слов",
                percentage = 84
            )
        )

        binding.tvVerifications.text = "5"
        binding.tvAverage.text = "78%"
        binding.tvTotalWords.text = "18,4K"

        adapter.updateData(documents)
    }
}