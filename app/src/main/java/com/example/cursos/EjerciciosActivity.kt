package com.example.cursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cursos.databinding.ActivityEjerciciosBinding

private lateinit var binding: ActivityEjerciciosBinding

class EjerciciosActivity : AppCompatActivity() {

    private fun numero(int: Int, fn: (Int) -> Boolean) {
        fn(int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjerciciosBinding.inflate(layoutInflater)
        view()
        setContentView(binding.root)
    }

    private fun view() {
        binding.button.setOnClickListener {
            numero(binding.editText.text.toString().toInt(), ::parOimpar)
            lambadas()
        }
    }

    /*Crea una funcion de orden superior que reciba un int y una funcion
    * que reciba un int y devuelva un Boolean
    * Llama a difa funcion de orden superior con expresiones lambdas
    * - comprobar si el numero es un numero par
    * - comprobar si el numero es un numero primo
    * - comprobar si el numero es un numero cool (cuando el resultado de la suma
    * de numeros consecutivos desde el 1. por ejemplo 1+2+3+4+5 = 10, 10 si es cool)
    * */
    private fun parOimpar(int: Int): Boolean {
        val int = binding.editText.text.toString().toInt()
        val a = int % 2
        if (a == 0) {
            Toast.makeText(this, "Par", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Impar", Toast.LENGTH_SHORT).show()
        }
        return a == 0
    }

    private fun numeroPrimo(int: Int): Boolean {
        for (i in 2 .. binding.editText.text.toString().toInt()) {
            if (binding.editText.text.toString().toInt() % i == 0 && i != binding.editText.text.toString().toInt())  {
                return false
                break
            }
        }
        return true
    }

    private fun cool(n: Int): Boolean {
        var suma = 0

        for (i in (1 until binding.editText.text.toString().toInt())) {
            suma += i
            if (suma == binding.editText.text.toString().toInt()) break
        }

        return suma == binding.editText.text.toString().toInt()
    }

    private fun lambadas() {

        println(
            "Numero par : ${
                numero(binding.editText.text.toString().toInt()) {
                    val b: Boolean
                    val int = binding.editText.text.toString().toInt()
                    val a = int % 2
                    if (a == 0) {
                        b = true
                        Toast.makeText(this, "Par", Toast.LENGTH_SHORT).show()
                    } else {
                        b = false
                        Toast.makeText(this, "Impar", Toast.LENGTH_SHORT).show()
                    }
                    b
                }
            }"
        )

        println(
            "Numero primo : ${
                numero(binding.editText.text.toString().toInt()) {
                    var b: Boolean
                    for (i in 2 .. binding.editText.text.toString().toInt()) {
                        if (binding.editText.text.toString().toInt() % i == 0 && i != binding.editText.text.toString().toInt())  {
                            b = false
                            break
                        }
                    }
                    b = true
                    b
                }
            }"
        )

        println(
            "Numero cool : ${
                numero(binding.editText.text.toString().toInt()) {
                    val b: Boolean
                    var suma = 0

                    for (i in (1 until binding.editText.text.toString().toInt())) {
                        suma += i
                        if (suma == binding.editText.text.toString().toInt()) break
                    }

                    b = suma == binding.editText.text.toString().toInt()
                    b
                }
            }"
        )
    }
}