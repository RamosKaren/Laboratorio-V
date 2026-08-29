package com.example.api_pedido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {

        pedidos.add(new Pedido(1L, "Sofia Ramos", "Laptop Lenovo", 1, 4500.00, "PENDIENTE"));
        pedidos.add(new Pedido(2L, "Karen Lopez", "Mouse inalambrico", 2, 300.00, "ENVIADO"));
        pedidos.add(new Pedido(3L, "Ricardo Hernandez", "Teclado mecanico", 1, 350.00, "ENTREGADO"));
        pedidos.add(new Pedido(4L, "Jose Martinez", "Monitor Samsung", 1, 1800.00, "PENDIENTE"));
        pedidos.add(new Pedido(5L, "Karla Gomez", "Audifonos Sony", 2, 700.00, "EN PROCESO"));
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {

        for (Pedido pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }

        return null;
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {

        Long nuevoId = 1L;

        for (Pedido p : pedidos) {
            if (p.getId() >= nuevoId) {
                nuevoId = p.getId() + 1;
            }
        }

        pedido.setId(nuevoId);
        pedidos.add(pedido);

        return pedido;
    }

    @PutMapping("/{id}")
    public Pedido actualizar(
            @PathVariable Long id,
            @RequestBody Pedido datos) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId().equals(id)) {

                pedido.setCliente(datos.getCliente());
                pedido.setProducto(datos.getProducto());
                pedido.setCantidad(datos.getCantidad());
                pedido.setTotal(datos.getTotal());
                pedido.setEstado(datos.getEstado());

                return pedido;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Pedido actualizarParcial(
            @PathVariable Long id,
            @RequestBody Pedido datos) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId().equals(id)) {

                if (datos.getCliente() != null) {
                    pedido.setCliente(datos.getCliente());
                }

                if (datos.getProducto() != null) {
                    pedido.setProducto(datos.getProducto());
                }

                if (datos.getCantidad() != null) {
                    pedido.setCantidad(datos.getCantidad());
                }

                if (datos.getTotal() != null) {
                    pedido.setTotal(datos.getTotal());
                }

                if (datos.getEstado() != null) {
                    pedido.setEstado(datos.getEstado());
                }

                return pedido;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getId().equals(id)) {

                pedidos.remove(i);

                return "Pedido eliminado correctamente";
            }
        }

        return "Pedido no encontrado";
    }
}