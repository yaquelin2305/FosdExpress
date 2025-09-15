import kotlinx.coroutines.*
import org.example.Producto

fun main() = runBlocking {
    println("=== Sistema de Gestion de Pedidos ===")

    // Inicializar catálogo
    val catalogo: List<Producto> = inicializarCatalogo()
    println("\n--- Catalogo de Productos ---")
    catalogo.forEach { println(it.descripcion()) }

    // Tipo de cliente
    val clienteTipo = "VIP"

    // Pedido del cliente (3 productos del catálogo)
    val pedidoProductos = listOf(
        catalogo[0],
        catalogo[2],
        catalogo[3]
    )

    println("\nCliente tipo: $clienteTipo")
    println("\n--- Pedido ---")
    pedidoProductos.forEach { println(it.descripcion()) }

    // Cálculos de compra
    val subtotal = pedidoProductos.sumOf { it.total() }
    val descuento = calcularDescuento(clienteTipo, subtotal)
    val impuesto = calcularImpuesto(subtotal - descuento)
    val total = subtotal - descuento + impuesto

    println("\nSubtotal: $$subtotal")
    println("Descuento: -$$descuento")
    println("IVA (19%): +$$impuesto")
    println("Total a pagar: $$total")

    // Procesar pedido
    println("\n--- Procesando Pedido ---")
    procesarPedido(pedidoProductos)

    // Mostrar Premium
    println("\n--- Productos Premium del catalogo ---")
    val premium = filtrarPremium(catalogo)
    premium.forEach { println(it.descripcion()) }

    println("\n=== Fin del sistema ===")
}
