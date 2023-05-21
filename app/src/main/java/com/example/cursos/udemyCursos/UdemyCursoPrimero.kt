package com.example.cursos.udemyCursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cursos.R
import com.example.cursos.databinding.ActivityUdemyCursoPrimeroBinding

private lateinit var binding: ActivityUdemyCursoPrimeroBinding

class UdemyCursoPrimero : AppCompatActivity() {
    companion object {
        // valor constante no puede ser definido como variable local
        // basicamente es una variable global
        const val moneda = "EUR"
    }

    var saldo: Float = 300.54f
    var sueldo: Float = 764.82f
    var entero: Int = 62

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUdemyCursoPrimeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*Tipos de variables*/
        // val (valores) no puede ser reasignado
        val fecha = "01/05/1990"
        //           0123456789
        // var es un dato variable que puede cambia
        var nombre: String = "Ale"
        var vip: Boolean = true
        var saludo = "Hola $nombre"


        saludo += if (vip == true) ", Te queremos mucho"
        else ", Quieres ser VIP"

        mostrar_saldo()

        /*Tipos de datos
        * string, booleab, Int, float, double, Long*/

        var dia = fecha.subSequence(0, 2).toString().toInt()
        if (dia == 1) ingresar_sueldo()

        var mes = fecha.subSequence(3, 5).toString().toInt()

        when (mes) {
            1, 2, 3 -> print("\n En invierno no hay ofertas ")
            4, 5, 6 -> print("\n En primavera no hay ofertas ")
            7, 8, 9 -> print("\n En verano no hay ofertas ")
            10, 11, 12 -> print("\n En otoño no hay ofertas ")
            else -> print("\n La fecha es erronea")
        }
        println(saludo)

        var pin: Int = 1234
        var intentos: Int = 0

        var clave_ingresada: Int = 1230

        do {
            println("Ingrese el PIN : ")
            println("Clave ingresada : ${clave_ingresada++}")
            if (clave_ingresada == pin) break
            intentos++
        } while (intentos < 3 && clave_ingresada != pin)

        if (intentos == 3) println("Tarjeta Bloqueada")

        mostrar_saldo()
        ingresar_dinero(50.5f)
        retirar_dinero(40.0f)


        /*/*Operarodes logicos*/

        var a : Boolean = true
        var b : Boolean = true
        var c : Boolean = false
        var d : Boolean = false

        a && b  // && = AND
        a || b // ||  = OR

        a && c
        a && c

        !d      // ! = negacion*/
        /*/*Operadores de calculo*/
        var a: Int = 5 + 5 // 10
        var b: Int = 10 - 2 // 8
        var c: Int = 3 * 4 // 12
        var d: Int = 10 / 5 // 2
        var e: Int = 10 % 3 // es el restante de la division
        var f: Int = 10 / 6 // division infinita, solo se mantiene la parte entera*/
        /*
                /*Incrementos y drecementos*/
                var aPreIncremento: Int = 5
                var bPreDecremento: Int = 5
                var cPostIncremento: Int = 5
                var dPostDecremento: Int = 5

                println(aPreIncremento)
                println(++aPreIncremento) // primero incrementa el dato
                println(aPreIncremento)

                println(bPreDecremento)
                println(--bPreDecremento) // primero decrementa el dato
                println(bPreDecremento)

                println(cPostIncremento)
                println(cPostIncremento--) // primero muestra el valor y despues incrementa
                println(cPostIncremento)

                println(dPostDecremento)
                println(dPostDecremento++) // primero muestra el valor y despues incrementa
                println(dPostDecremento)

                saldo += sueldo
        */
        /*/*Operadores de comparacion*/
        a == b
        a != b
        a > b
        a < b
        a >= b
        a <= b */


    }

    fun mostrar_saldo() {
        println("Tienes $saldo $moneda")
    }

    fun ingresar_sueldo() {
        saldo += sueldo
        println("Se ha ingresado tu sueldo de $sueldo $moneda")
        mostrar_saldo()
    }
    fun ingresar_dinero(cantidad : Float){
        saldo += cantidad
        println("Se ha ingresado $cantidad $moneda")
        mostrar_saldo()
    }
    fun retirar_dinero(cantidad : Float){
        saldo -= cantidad
        println("Se ha retirado dinero $cantidad $moneda")
        mostrar_saldo()
    }
}