package com.example.cursos.udemyCursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cursos.R
import com.example.cursos.databinding.ActivityPooBinding

private lateinit var binding: ActivityPooBinding
class PooActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPooBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var num : Int = 0
        val jota : Person = Person()
        println(jota.alive)
        jota.die()
        println(jota.alive)
        println(jota.alive)
    }
}