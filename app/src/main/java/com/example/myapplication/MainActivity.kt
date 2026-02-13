                package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding

                class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var ft: FragmentTransaction
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener {
           selectTab(it)
fragment= TestFragment()
            setNewFragment(fragment)
        }
        binding.btnImport.setOnClickListener { selectTab(it) }
        binding.btnHistory.setOnClickListener { selectTab(it) }
        binding.btnProfile.setOnClickListener { selectTab(it) }


    }
@SuppressLint("SuspiciousIndentation")
private fun selectTab(selectedButton: View) {
                        // Список всех ваших кнопок
        val buttons = listOf(
                            binding.btnMain,
                            binding.btnImport,
                            binding.btnProfile,
                            binding.btnHistory,

                        )

                        // Выключаем все, включаем только нажатую
                        buttons.forEach { it.isSelected = (it == selectedButton) }
                    }
private fun setNewFragment(fragment: Fragment){
                        ft=supportFragmentManager.beginTransaction()

                        if(!fragment.isRemoving){
                            ft.remove(this.fragment)
                        }
                        this.fragment=fragment
                        ft.replace(R.id.fragment_container,this.fragment)
                        ft.commit()
                    }


}