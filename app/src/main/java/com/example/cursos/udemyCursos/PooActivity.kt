package com.example.cursos.udemyCursos

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.cursos.R
import com.example.cursos.databinding.ActivityPooBinding

private lateinit var binding: ActivityPooBinding

class PooActivity : AppCompatActivity() {
    companion object {
        lateinit var maincontext: Context
    }

    private lateinit var pok: Pokemon
    private lateinit var waterPok: Waterpokemon
    private lateinit var firePok: Firepokemon
    private lateinit var earthPok: Earthpokemon

    object fernanda {
        var apodo = "fer"
        fun saludo() {
            println("Hola, me llaman $apodo")
        }
    }

    // Funciones de extension, permiten añadir metodos a objetos que existen
    private fun String.noSpaces(): String {
        return this.replace(" ", "")
    }

    private fun IntArray.show() {
        print("[")
        for (i in this) print("$i")
        print("]")
    }

    //Funciones de odern superior
    // Funciones que reciben funciones

    private fun calculadora(n1: Int, n2: Int, fn: (Int, Int) -> Int): Int {
        return fn(n1, n2)
    }

    private fun suma(x: Int, y: Int): Int {
        return x + y
    }

    private fun resta(x: Int, y: Int): Int {
        return x - y
    }

    private fun multiplica(x: Int, y: Int) = x * y
    private fun divide(x: Int, y: Int) = x / y

    private fun inColombia(h: Float): Boolean {
        return h > 1.6f
    }

    private fun inSpain(h: Float): Boolean {
        return h > 1.65f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPooBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var num: Int = 0
        val jota: Person = Person("Jota", "ASD1Q23",1.62f)
        val anonimo: Person = Person()
        anonimo.Person()
        println(jota.alive)
        println(jota.nombre)
        println(jota.passport)
        jota.die()

        println(jota.alive)
        if (jota.checkPolice(::inColombia)) println("${jota.nombre} puede ser Policia en colombia")
        if (jota.checkPolice(::inSpain)) println("${jota.nombre} puede ser Policia en colombia")

        val bicho: Pokemon = Pokemon()
        println(bicho.getName())
        println(bicho.getAttackPower())
        bicho.setLife(30f)
        println(bicho.getLife())

        /*Funciones de extension*/
        var usuario = "    soy   yo    "
        println("$usuario ${usuario.length}  - ${usuario.noSpaces()} ${usuario.noSpaces().length}")
        var array1: Array<Int> = arrayOf(5, 4, 3, 2, 1)
        var array2 = IntArray(3)
        array2[0] = 10
        array2[1] = 20
        array2[2] = 30

        // se pueden hacer varias instrucciones en la misma linea separando con  ;
        println("Array2 : "); array2.show()
        var array3: IntArray = intArrayOf(1, 2, 3, 4, 5)
        println("Array3 : "); array3.show()

        /*Funciones de orden superior*/
        println("La suma de 80 y 20 es  ${calculadora(80, 20, ::suma)}")
        println("La resta de 50 y 10 es  ${calculadora(80, 20, ::resta)}")
        println("la multiplicacion de 7 y 7 es  ${calculadora(80, 20, ::multiplica)}")
        println("La division de 12 y 3 es  ${calculadora(80, 20, ::divide)}")

        /*Lambdas*/

        var funcion = { x : Int, y : Int -> x+y}
        println("La suma de 80 y 20 con variable es  ${calculadora(80, 20, funcion)}")

        funcion = { x : Int, y : Int -> x-y}
        println("La resta de 50 y 20 con variable es  ${calculadora(80, 20, funcion)}")

        println("La suma de 80 y 20 con lambdas es  ${calculadora(80, 20) { x: Int, y: Int -> x - y }}")
        println("La resta de 50 y 20 con lambdas es  ${calculadora(80, 20) { x: Int, y: Int -> x - y }}")
        println("La potencia de 2 con lambdas es  ${calculadora(80, 20) 
        { x, y -> 
            var valor = 1
            for (i in 1..y) valor *= x
            valor
             }}")

        //Accede a clase padre
        var sc = SubClasses()
        println(sc.presentar())
        //Accede a clase anidada
        var ani = SubClasses.Anidada()
        println(ani.presentar())
        //Accede a clase interna
        var inn = SubClasses().Interna()
        println(inn.presentar())

        //Objetos anonimos se pueden implementar propiedades y funciones
        println(fernanda.saludo())
        fernanda.apodo = "SuperFer"
        println(fernanda.saludo())


        var sol: star = star("Sol", 696340f, "Vía Láctea")
        println(sol)

        var betelgeuse: star = star("Betelgeuse", 617100000f, "Orión")
        betelgeuse.alive = false
        println(betelgeuse.alive)

        var nueva: star = star()
        println(nueva)

        var hoy: dias = dias.LUNES
        var semana = dias.values()
        for (i in semana) println(i)

        println(dias.valueOf("MIERCOLES"))
        println(hoy.name)
        println(hoy.ordinal)

        println(hoy.saludo())
        println(hoy.laboral)
        println(hoy.jornada)

        hoy = dias.DOMINGO

        var btFight = findViewById<Button>(R.id.btFight)
        btFight.setOnClickListener {
            fight(firePok, earthPok)
        }

    }

    fun createNewPokemon(v: View) {
        var etName = findViewById<EditText>(R.id.etName)
        var etAttackPower = findViewById<EditText>(R.id.etAttackPower)

        pok = Pokemon()

        if (!etName.text.isNullOrEmpty() && !etAttackPower.text.isNullOrEmpty())
            pok.Pokemon(etName.text.toString(), etAttackPower.text.toString().toFloat())

        var ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        ivPokemon.setImageResource(R.mipmap.pokemon)

        var tvPokemon = findViewById<TextView>(R.id.tvPokemon)
        loadDataPokemon(tvPokemon, pok)
    }

    fun createNewWaterPokemon(v: View) {
        var etWaterName = findViewById<EditText>(R.id.etWaterName)
        var etWaterAttackPower = findViewById<EditText>(R.id.etWaterAttackPower)
        var etWaterMaxResistence = findViewById<EditText>(R.id.etWaterMaxResistence)

        waterPok = Waterpokemon()

        if (!etWaterName.text.isNullOrEmpty() && !etWaterAttackPower.text.isNullOrEmpty())
            waterPok.Waterpokemon(
                etWaterName.text.toString(),
                etWaterAttackPower.text.toString().toFloat(),
                etWaterMaxResistence.text.toString().toInt()
            )

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water)
        ivWaterPokemon.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPok)
    }

    fun cureWaterPokemon(v: View) {
        waterPok.cure()
        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPok)
    }

    fun sayHiWaterPokemon(v: View) {
        waterPok.sayHi()
    }

    fun evolveWaterPokemon(v: View) {

        var etEvolveWaterPokemon = findViewById<EditText>(R.id.etEvolveWaterPokemon)

        waterPok.evolve(etEvolveWaterPokemon.text.toString())

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water_evolved)

        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPok)
    }

    fun createNewFirePokemon(v: View) {
        var etFireName = findViewById<EditText>(R.id.etFireName)
        var etFireAttackPower = findViewById<EditText>(R.id.etFireAttackPower)
        var etFireBallTemperature = findViewById<EditText>(R.id.etFireBallTemperature)

        firePok = Firepokemon()

        if (!etFireName.text.isNullOrEmpty() && !etFireAttackPower.text.isNullOrEmpty())
            firePok.Firepokemon(
                etFireName.text.toString(),
                etFireAttackPower.text.toString().toFloat(),
                etFireBallTemperature.text.toString().toInt()
            )

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire)
        ivFirePokemon.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)
    }

    fun cureFirePokemon(v: View) {
        firePok.cure()
        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)
    }

    fun sayHiFirePokemon(v: View) {
        firePok.sayHi()
    }

    fun evolveFirePokemon(v: View) {

        var etEvolveFirePokemon = findViewById<EditText>(R.id.etEvolveFirePokemon)

        firePok.evolve(etEvolveFirePokemon.text.toString())

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire_evolved)

        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)
    }


    fun createNewEarthPokemon(v: View) {
        var etEarthName = findViewById<EditText>(R.id.etEarthName)
        var etEarthAttackPower = findViewById<EditText>(R.id.etEarthAttackPower)
        var etEarthMaxDepth = findViewById<EditText>(R.id.etEarthMaxDepth)

        earthPok = Earthpokemon()

        if (!etEarthName.text.isNullOrEmpty() && !etEarthAttackPower.text.isNullOrEmpty())
            earthPok.Earthpokemon(
                etEarthName.text.toString(),
                etEarthAttackPower.text.toString().toFloat(),
                etEarthMaxDepth.text.toString().toInt()
            )

        var ivEarthPokemon = findViewById<ImageView>(R.id.ivEarthPokemon)
        ivEarthPokemon.setImageResource(R.mipmap.earth)
        ivEarthPokemon.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }

    fun cureEarthPokemon(v: View) {
        earthPok.cure()
        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }

    fun sayHiEarthPokemon(v: View) {
        earthPok.sayHi()
    }

    fun evolveEarthPokemon(v: View) {

        var etEvolveEarthPokemon = findViewById<EditText>(R.id.etEvolveEarthPokemon)

        earthPok.evolve(etEvolveEarthPokemon.text.toString())

        var ivEarthPokemon = findViewById<ImageView>(R.id.ivEarthPokemon)
        ivEarthPokemon.setImageResource(R.mipmap.earth_evolved)

        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }

    fun sayByeEarthPokemon(v: View) {
        earthPok.sayBye()
    }

    private fun fight(p1: Pokemon, p2: Pokemon) {

        var emtLog = findViewById<EditText>(R.id.emtLog)
        emtLog.setText("")
        var text = ""

        text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs ${p2.getName()} (${
            p2.getLife().toInt()
        })"

        while (p1.getLife() > 0 && p2.getLife() > 0) {
            text += "\n${p1.getName()} ataca!"
            p1.attack()
            p2.setLife(p2.getLife() - p1.getAttackPower())
            text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs ${p2.getName()} (${
                p2.getLife().toInt()
            })"
            if (p2.getLife() > 0) {
                text += "\n${p2.getName()} ataca!"
                p2.attack()
                p1.setLife(p1.getLife() - p2.getAttackPower())
                text += "\n${p1.getName()} (${
                    p1.getLife().toInt()
                }) Vs ${p2.getName()} (${p2.getLife().toInt()})"
            }
        }

        if (p1.getLife() > 0) text += "\n\nEL CAMPEON ES ${p1.getName()}"
        else text += "\n\nEL CAMPEON ES ${p2.getName()}"

        emtLog.setText(text)


        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)

        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }

    private fun loadDataPokemon(tv: TextView, p: Pokemon) {
        var description: String = ""

        description += p.getName() + " ("
        description += "AP: " + p.getAttackPower().toInt()
        description += " - L: " + p.getLife().toInt() + ")"

        tv.text = description
    }
}