package Model

import Model.Producto

class Comida(
    nombre: String,
    precioBase: Int,
    tiempoPrep: Int,
    val premium: Boolean
) : Producto(nombre, precioBase, tiempoPrep) {

    override fun descripcion(): String {
        return "Comida: $nombre, Precio: $$precioBase, Premium: $premium"
    }

    override fun total(): Int {
        // Si es premium, cuesta un 10% más
        return if (premium) (precioBase * 1.1).toInt() else precioBase
    }
}