package org.example

class Bebida(
    nombre: String,
    precioBase: Int,
    tiempoPrep: Int,
    val tamanno: String
) : Producto(nombre, precioBase, tiempoPrep) {

    override fun descripcion(): String {
        return "Bebida: $nombre, Precio: $$precioBase, Tamaño: $tamanno"
    }

    override fun total(): Int {
        val recargo = when (tamanno.lowercase()) {
            "grande" -> 500
            "mediana" -> 300
            else -> 0
        }
        return precioBase + recargo
    }
}

