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

        retirar_dinero(50.0f)
        retirar_dinero(2000.0f)

        // Array
        var recibos: Array<String> = arrayOf("Luz", "Agua", "Gas")
        recibos[2] = "internet"
        recorrer_arrar(recibos)
        //Matriz donde se tienen varios arrays
        var matriz = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6, 7, 8, 9, 10),
            intArrayOf(11, 12, 13, 14)
        )

        // recorrer arrays dentro de matrices
        for (i in (0 until matriz.size)) {
            println()
            for (j in (0 until matriz[i].size))
                println("Posicion [$i][$j] : ${matriz[i][j]}")
        }

        //Colecciones set no se pueden añadir elementos
        var clientesVIP: Set<Int> = setOf(1234, 5678, 4040)
        var setMezclado = setOf(2, 4.454, "casa", "c")

        println("Clientes VIP : \n")
        println(clientesVIP)
        println("Numero de clientes VIP  : ${clientesVIP.size}")

        if (clientesVIP.contains(1234)) println("1234 es VIP")
        if (clientesVIP.contains(1234)) println("1235 es VIP")

        //colecciones mutables si se pueden añadir elementos
        var clientes: MutableSet<Int> = mutableSetOf(1234, 5678, 4040, 8970)
        println("Clientes : \n")
        println(clientes)

        clientes.add(3026)
        println(clientes)

        clientes.remove(5678)
        println(clientes)
        println("Numero de clientes  ${clientes.size}")

        clientes.clear()
        println(clientes)

        println("Numero de clientes  ${clientes.size}")

        var divisas: List<String> = listOf("USE", "EUR", "YEN")
        println(divisas)

        var bolsa: MutableList<String> = mutableListOf("Coca-Cola", "Adidas", "Amazon", "Pficer")
        println(bolsa)
        /*
                bolsa.add("Adobe")
                println(bolsa)

                bolsa.add("Nvidia")
                println(bolsa)

                bolsa.removeAt(2)
                println(bolsa)

                println(bolsa.first())
                println(bolsa.last())
                println(bolsa.elementAt(2))
                println(bolsa.none())

                bolsa.clear()
                println(bolsa)
                println(bolsa.none())
        */
        var mapa: Map<Int, String> = mapOf(
            1 to "España",
            2 to "Mexico",
            3 to "Colombia"
        )

        println(mapa)

        var inversiones = mutableMapOf<String, Float>()
        println(inversiones)

        var empresa: String? = null

        mostrar_saldo()
        var cantidad_a_invertir = 90f
        var index = 0

        while (saldo >= cantidad_a_invertir) {
            empresa = bolsa.elementAtOrNull(index)
            if (empresa != null) {
                saldo -= cantidad_a_invertir
                println("Se ha invertido $cantidad_a_invertir $moneda en $empresa")
                inversiones.put(empresa, cantidad_a_invertir)
            } else break
            index++
        }

        mostrar_saldo()

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

    fun ingresar_dinero(cantidad: Float) {
        saldo += cantidad
        println("Se ha ingresado $cantidad $moneda")
        mostrar_saldo()
    }

    fun retirar_dinero(cantidad: Float) {
        if (verificarCantidad(cantidad)) {
            saldo -= cantidad
            println("Se ha retirado dinero $cantidad $moneda")
            mostrar_saldo()
        } else println("Cantidad superior al saldo. Imposible realizar la operacion.")
    }

    fun verificarCantidad(cantidad: Float): Boolean {
        return cantidad <= saldo
    }

    fun recorrer_arrar(array: Array<String>) {
        for (i in array)
            println(i)
        for (i in array.indices)
            println(array[i])
        for (i in 0 until array.size)
            println("${i + 1} : ${array[i]}")
    }
}