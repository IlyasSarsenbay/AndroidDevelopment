package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.PepeBinding

class Pepe: AppCompatActivity() {
    private lateinit var binding: PepeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PepeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.leave.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }
}