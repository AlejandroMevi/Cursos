package com.example.cursos.udemyCursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cursos.R
import com.example.cursos.databinding.ActivityUdemyCursoPrimeroBinding

private lateinit var binding : ActivityUdemyCursoPrimeroBinding

class UdemyCursoPrimero : AppCompatActivity() {
    companion object{
        // valor constante no puede ser definido como variable local
        // basicamente es una variable global
        const val moneda = "EUR"
    }
    var saldo : Float = 300.54f
    var sueldo = 764.82
    var entero : Int = 62

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUdemyCursoPrimeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Tipos de variables*/
        // val (valores) no puede ser reasignado
        val fecha = "05/06/1990"
        // var es un dato variable que puede cambia
        var nombre: String = "Ale"
        var vip: Boolean = false
        var saludo = "Hola $nombre"

        /*Tipos de datos
        * string, booleab, Int, float, double, Long*/

        println(nombre)
    }
}