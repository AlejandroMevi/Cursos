package com.example.cursos.udemyCursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cursos.R
import com.example.cursos.databinding.ActivityUnidadesMedidaBinding

class UnidadesMedida : AppCompatActivity() {
    private lateinit var binding : ActivityUnidadesMedidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnidadesMedidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}