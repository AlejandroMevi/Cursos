package com.example.cursos.udemyCursos

import android.annotation.SuppressLint
import android.widget.Toast
import com.example.cursos.udemyCursos.PooActivity.Companion.maincontext

open class Pokemon(
    protected var name: String = "Pok",
    protected var attackPower: Float = 30f,
    protected var life: Float = 100f
) : thanks() {

    @SuppressLint("NotConstructor")
    fun Pokemon(n: String, aP: Float) {
        this.name = n
        this.attackPower = aP
        this.life = 100f
    }
// protected

    internal fun getName(): String {
        return this.name
    }

    internal fun getAttackPower(): Float {
        return this.attackPower
    }

    internal fun getLife(): Float {
        return this.life
    }

    internal fun setLife(l: Float) {
        this.life = l
    }

    fun sayHi() {
        Toast.makeText(maincontext, "Hola!!! Soy $name", Toast.LENGTH_LONG).show()
    }

    fun cure() {
        this.life = 100f
        this.thanksCure()
    }

    fun evolve(n: String) {
        this.name = n
        this.attackPower *= 1.20f
        this.sayHi()
    }

    open fun attack() {
        Toast.makeText(maincontext, "Al ataqueeee", Toast.LENGTH_LONG).show()
    }

}


class Waterpokemon(n: String = "Pok", aP: Float = 30f, l: Float = 100f) : Pokemon(n, aP, l) {

    private var maxResistance: Int = 500
    private var submergedTime: Int = 0

    //public  var life: Float = 100f // SIN METODO GET, EL PROPIO ERROR EN ROJO LO INDICA

    @SuppressLint("NotConstructor")
    fun Waterpokemon(n: String, aP: Float, mR: Int) {
        this.name = n
        this.attackPower = aP
        this.life = 100f
        this.maxResistance = mR
        this.sayHi()
    }

    fun breathe() {
        this.submergedTime = 0
    }

    fun immerse() {
        this.submergedTime++
    }


    override fun attack() {
        Toast.makeText(maincontext, "Ataque con chorro!!", Toast.LENGTH_LONG).show()
    }
}

class Firepokemon(n: String = "Pok", aP: Float = 30f, l: Float = 100f) : Pokemon(n, aP, l) {
    private var ballTemperature: Int = 90
    lateinit var ball: ballFire
    var numBall: Int = 0

    @SuppressLint("NotConstructor")
    fun Firepokemon(n: String, aP: Float, bT: Int) {
        this.name = n
        this.attackPower = aP
        this.life = 100f
        this.ballTemperature = bT
        this.sayHi()
    }

    override fun attack() {
        super.attack()
        Toast.makeText(maincontext, "Ataque con fuego!!", Toast.LENGTH_LONG).show()

        Toast.makeText(maincontext, "Bola ${++numBall}", Toast.LENGTH_LONG).show()
        ball = ballFire(ballTemperature)
        ball.throwBall()
    }
}

class ballFire(var t: Int = 100) {
    fun throwBall() {
        Toast.makeText(maincontext, "Tirando bola con temperatura de $t", Toast.LENGTH_LONG).show()
    }
}


class Earthpokemon(n: String = "Pok", aP: Float = 30f, l: Float = 100f) : Pokemon(n, aP, l), sayBay,
    sayBay2, sayBay3 {
    private var depth: Int = 150
    override var dato: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    @SuppressLint("NotConstructor")
    fun Earthpokemon(n: String, aP: Float, d: Int) {
        this.name = n
        this.attackPower = aP
        this.life = 100f
        this.depth = d
        this.sayHi()
    }

    fun digTunnel() {
        Toast.makeText(
            maincontext, "Cabaré un tunnel de ${this.depth}m de profundidad",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun attack() {
        Toast.makeText(maincontext, "Ataque con piedras", Toast.LENGTH_SHORT).show()
    }
}

abstract class thanks() {
    fun thanksCure() {
        Toast.makeText(maincontext, "Gracias por curarme!", Toast.LENGTH_SHORT).show()
    }
}

interface sayBay {
    var dato: Int
    fun sayBye() {
        Toast.makeText(maincontext, "ByeBye!", Toast.LENGTH_SHORT).show()
    }
}

interface sayBay2 {
    fun sayBye2() {
        Toast.makeText(maincontext, "ByeBye!", Toast.LENGTH_SHORT).show()
    }
}

interface sayBay3 {
    fun sayBye3() {
        Toast.makeText(maincontext, "ByeBye!", Toast.LENGTH_SHORT).show()
    }
}
