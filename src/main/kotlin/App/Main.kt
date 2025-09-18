package App

import Estado_Gestor.*
import Model.*
import kotlinx.coroutines.*

fun main() = runBlocking {
    // Creamos catálogo
    val catalogo = inicializarCatalogo()

    // Preguntar tipo de cliente
    println("Ingrese tipo de cliente (normal, vip, premium): ")
    val clienteTipo = readLine()?.trim()?.lowercase() ?: "normal"

    // Mostrar catálogo
    println("\n--- Catalogo de productos ---")
    catalogo.forEachIndexed { i, p ->
        println("${i + 1}. ${p.descripcion()}")
    }

    // Elegir productos por número
    println("\nIngrese los numeros de los productos separados por coma (ej: 1,2): ")
    val entrada = readLine() ?: ""
    val indices = entrada.split(",").mapNotNull { it.trim().toIntOrNull() }

    // Lista de productos del pedido
    val pedido = indices.mapNotNull { catalogo.getOrNull(it - 1) }

    if (pedido.isEmpty()) {
        println(" No se seleccionaron productos.")
        return@runBlocking
    }

    // Calcular subtotal
    val subtotal = pedido.sumOf { it.total() }
    val descuento = calcularDescuento(clienteTipo, subtotal)
    val iva = calcularImpuesto(subtotal - descuento)
    val total = subtotal - descuento + iva

    // Mostrar pedido
    println("\n--- Pedido ---")
    pedido.forEach { println(it.descripcion()) }
    println("Subtotal: $subtotal")
    println("Descuento: $descuento")
    println("IVA: $iva")
    println("Total a pagar: $total")

    // Procesar pedido (corrutinas)
    println("\nProcesando pedido...")
    procesarPedido(pedido)

    // Mostrar premium
    println("\nProductos premium en el catalogo:")
    filtrarPremium(catalogo).forEach { println(it.descripcion()) }
}
