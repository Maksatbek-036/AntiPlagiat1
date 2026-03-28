package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var ft: FragmentTransaction
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.btnHistory.setOnClickListener {
          selectTab(it)
          setNewFragment(HistoryFragment())

      }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun selectTab(selectedButton: View) {
        val buttons = listOf(
            binding.btnMain,
            binding.btnImport,
            binding.btnProfile,
            binding.btnHistory
        )
        buttons.forEach { it.isSelected = (it == selectedButton) }
    }

    private fun setNewFragment(fragment: Fragment) {
        ft = supportFragmentManager.beginTransaction()
        if (currentFragment != null && !currentFragment!!.isRemoving) {
            ft.remove(currentFragment!!)
        }
        currentFragment = fragment
        ft.replace(R.id.fragment_container, currentFragment!!)
        ft.commit()
    }
}