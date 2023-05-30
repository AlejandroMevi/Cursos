package com.example.cursos.udemyCursos

class SubClasses {
    private var name = "Padre"
    fun presentar(): String {
        return this.name
    }

    class Anidada {
        private val nameAnidada = "Anidada"
        fun presentar(): String {
            return this.nameAnidada
        }
    }
    // Con clases internas se pueden acceder a los valores del padre
    // Se puede invocar Interna desde la actividad o fragmento
    inner class Interna {
        private val nameInterna = "Interna"
        fun presentar(): String {
            return "hola, soy ${this.nameInterna}, hija de ${name}"
        }
    }


}