package com.example.myapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDocumentBinding
import com.example.myapplication.model.Document

class DocumentAdapter(private var documents: List<Document>) :
    RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    inner class DocumentViewHolder(private val binding: ItemDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(document: Document) {
            binding.apply {
                tvTitle.text = document.title
                tvDate.text = document.date
                tvTime.text = document.time
                tvWords.text = document.words
                tvPercentage.text = "${document.percentage}%"

                val color = when {
                    document.percentage < 60 -> "#FF5252"
                    document.percentage < 70 -> "#FFB74D"
                    else -> "#66BB6A"
                }

                badgePercentage.setBackgroundColor(Color.parseColor(color))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = ItemDocumentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DocumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(documents[position])
    }

    override fun getItemCount() = documents.size

    fun updateData(newDocuments: List<Document>) {
        documents = newDocuments
        notifyDataSetChanged()
    }
}