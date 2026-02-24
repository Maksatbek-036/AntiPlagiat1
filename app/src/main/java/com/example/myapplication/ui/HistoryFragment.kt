package com.example.myapplication.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.HistoryAdapter
import com.example.myapplication.data.CheckModel

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val list = listOf(
            CheckModel("Курсовая работа", "78%"),
            CheckModel("Реферат", "85%"),
            CheckModel("Диплом", "90%")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HistoryAdapter(list)

        return view
    }
}
}