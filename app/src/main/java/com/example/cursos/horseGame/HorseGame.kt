package com.example.cursos.horseGame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cursos.databinding.ActivityHorseGameBinding

class HorseGame : AppCompatActivity() {
    private lateinit var binding: ActivityHorseGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorseGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}