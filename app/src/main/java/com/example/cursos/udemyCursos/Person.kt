package com.example.cursos.udemyCursos

import android.annotation.SuppressLint

open class Person(var nombre : String = "Persona", var passport : String? = null, var height : Float = 1.6f){
    var alive : Boolean = true

    @SuppressLint("NotConstructor")
    fun Person(){
        nombre = "Juan"
        passport = "ASDA"
    }
    fun die(){
        alive = false
    }
    // se utiliza una funcion dentro de otra
    // la funcion que se envia a la principal puede tener mas logica
    // se obtiene el valor deseado de la primera y se sigue con la funcion principal
    fun checkPolice(fn:(Float) -> Boolean): Boolean{
        return fn(height)
    }
}
// clase que hereda de clase Persona, se herada con dos puntos : y la clase
class Athlete(name: String, passport: String?, var sport: String): Person(name, passport)