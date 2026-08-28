package com.example.api_libros;

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
@RequestMapping("/api/libros")
public class LibroController {

    private List<Libro> libros = new ArrayList<>();

    public LibroController() {

        libros.add(new Libro(1L, "Harry Potter y la piedra filosofal",
                "J.K. Rowling", "Fantasia", 120.00));

        libros.add(new Libro(2L, "Don Quijote de la Mancha",
                "Miguel de Cervantes", "Novela", 100.00));

        libros.add(new Libro(3L, "El diario de Ana Frank",
                "Ana Frank", "Biografia", 85.00));

        libros.add(new Libro(4L, "El Principito",
                "Antoine de Saint-Exupery", "Fabula", 75.00));

        libros.add(new Libro(5L, "Cien anos de soledad",
                "Gabriel Garcia Marquez", "Realismo magico", 110.00));
    }

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libros;
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {

        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                return libro;
            }
        }

        return null;
    }

    @PostMapping
    public Libro crear(@RequestBody Libro libro) {

        Long nuevoId = 1L;

        for (Libro l : libros) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        libro.setId(nuevoId);
        libros.add(libro);

        return libro;
    }

    @PutMapping("/{id}")
    public Libro actualizar(
            @PathVariable Long id,
            @RequestBody Libro datos) {

        for (int i = 0; i < libros.size(); i++) {

            if (libros.get(i).getId().equals(id)) {

                datos.setId(id);
                libros.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Libro actualizarParcial(
            @PathVariable Long id,
            @RequestBody Libro datos) {

        for (Libro libro : libros) {

            if (libro.getId().equals(id)) {

                if (datos.getTitulo() != null) {
                    libro.setTitulo(datos.getTitulo());
                }

                if (datos.getAutor() != null) {
                    libro.setAutor(datos.getAutor());
                }

                if (datos.getGenero() != null) {
                    libro.setGenero(datos.getGenero());
                }

                if (datos.getPrecio() != null) {
                    libro.setPrecio(datos.getPrecio());
                }

                return libro;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < libros.size(); i++) {

            if (libros.get(i).getId().equals(id)) {

                libros.remove(i);

                return "Libro eliminado correctamente";
            }
        }

        return "Libro no encontrado";
    }
}