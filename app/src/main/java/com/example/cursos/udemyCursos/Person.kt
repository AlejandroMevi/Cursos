package com.example.cursos.udemyCursos

import android.annotation.SuppressLint

open class Person(var nombre : String = "Persona", var passport : String? = null){
    var alive : Boolean = true

    @SuppressLint("NotConstructor")
    fun Person(){
        nombre = "Juan"
        passport = "ASDA"
    }
    fun die(){
        alive = false
    }
}
// clase que hereda de clase Persona, se herada con dos puntos : y la clase
class Athlete(name: String, passport: String?, var sport: String): Person(name, passport)