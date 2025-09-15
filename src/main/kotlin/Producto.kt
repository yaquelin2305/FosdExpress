package org.example

open class Producto (val nombre: String , val precioBase: Int ,val tiempoPrep: Int ){

    open fun descripcion(): String {
        return "Producto: $nombre, Precio: $$precioBase, Tiempo preparación: ${tiempoPrep}min"
    }
    open fun total(): Int {
        // Precio base sin descuentos ni recargos
        return precioBase
    }


}




