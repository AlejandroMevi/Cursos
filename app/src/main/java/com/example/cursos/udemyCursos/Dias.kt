package com.example.cursos.udemyCursos

enum class dias(val laboral: Boolean, val jornada: Int) {
    LUNES(true, 8), MARTES(true, 8), MIERCOLES(true, 5),
    JUEVES(true, 8), VIERNES(true, 4), SABADO(false, 0),
    DOMINGO(false, 0);

    fun saludo(): String{
        when (this){
            LUNES -> return "empezando con alegría!!"
            MARTES -> return "ya queda menos!!"
            MIERCOLES -> return "sabías que los miércoles son los dias más productivos?"
            JUEVES -> return "esta noche es juernes!"
            VIERNES -> return "hoy es viernes y tu cuerpo lo sabe"
            else -> return "a quemar el findeeee!"
        }
    }
}