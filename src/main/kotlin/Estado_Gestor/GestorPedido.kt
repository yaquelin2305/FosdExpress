package Estado_Gestor

import kotlinx.coroutines.*
import Model.Producto
import Model.Bebida
import Model.Comida

fun inicializarCatalogo(): List<Producto> {
    return listOf(
        Comida("Hamburguesa Clasica", 8990, 10, premium = false),
        Comida("Salmon Grillado", 15990, 15, premium = true),
        Bebida("Coca Cola", 1990, 2, "mediana"),
        Bebida("Jugo Natural", 2990, 3, "grande")
    )
}

// Calcular descuento según tipo de cliente
fun calcularDescuento(tipoCliente: String, subtotal: Int): Int {
    val porcentaje = when (tipoCliente.lowercase()) {
        "vip" -> 0.10
        "premium" -> 0.15
        else -> 0.05
    }
    return (subtotal * porcentaje).toInt()
}

// Calcular impuesto (IVA 19%)
fun calcularImpuesto(subtotal: Int): Int {
    return (subtotal * 0.19).toInt()
}

// Procesar pedido con corrutinas simulando preparación
suspend fun procesarPedido(productos: List<Producto>) {
    var estado: EstadoPedido = EstadoPedido.Pendiente
    println("Estado inicial: Pendiente")

    try {
        delay(2000)
        estado = EstadoPedido.EnPreparacion
        println("Estado: En preparacion")

        delay(2000)
        estado = EstadoPedido.Listo
        println("Estado final: Listo")

    } catch (e: Exception) {
        estado = EstadoPedido.Error("Fallo en la preparacion: ${e.message}")
        println(estado)
    }
}

// Operaciones funcionales sobre colecciones
fun filtrarPremium(productos: List<Producto>): List<Producto> {
    return productos.filter { it.precioBase > 10000 }
        .sortedBy { it.nombre }
}
