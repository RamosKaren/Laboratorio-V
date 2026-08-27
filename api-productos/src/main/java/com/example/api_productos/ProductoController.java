package com.example.api_productos;

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
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Laptop Lenovo", 4500.00, "Tecnologia"));
        productos.add(new Producto(2L, "Mouse inalambrico", 150.00, "Accesorios"));
        productos.add(new Producto(3L, "Teclado mecanico", 350.00, "Accesorios"));
        productos.add(new Producto(4L, "Monitor Samsung", 1800.00, "Tecnologia"));
        productos.add(new Producto(5L, "Audifonos Sony", 650.00, "Audio"));
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        productos.add(producto);
        return producto;
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id,
            @RequestBody Producto producto) {

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                producto.setId(id);
                productos.set(i, producto);
                return producto;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Producto modificarProducto(@PathVariable Long id,
            @RequestBody Producto producto) {

        for (Producto p : productos) {
            if (p.getId().equals(id)) {

                if (producto.getNombre() != null) {
                    p.setNombre(producto.getNombre());
                }

                if (producto.getPrecio() != 0) {
                    p.setPrecio(producto.getPrecio());
                }

                if (producto.getCategoria() != null) {
                    p.setCategoria(producto.getCategoria());
                }

                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {

        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                productos.remove(producto);
                return "Producto eliminado";
            }
        }

        return "Producto no encontrado";
    }
}