package com.example.cursos.udemyCursos

enum class dias(val laboral: Boolean, val jornada: Int) {
    LUNES(true, 8), MARTES(true, 8), MIERCOLES(true, 5),
    JUEVES(true, 8), VIERNES(true, 4), SABADO(false, 0),
    DOMINGO(false, 0);

    // con la funcion sqaludo() regresa un String dependiendo del valor que tiene guardado
    // cuando se ejecuta en el activity, se referencia con this
    fun saludo(): String {
        return when (this) {
            LUNES -> "empezando con alegría!!"
            MARTES -> "ya queda menos!!"
            MIERCOLES -> "sabías que los miércoles son los dias más productivos?"
            JUEVES -> "esta noche es juernes!"
            VIERNES -> "hoy es viernes y tu cuerpo lo sabe"
            else -> "a quemar el findeeee!"
        }
    }
}