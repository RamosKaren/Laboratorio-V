package com.example.api_peliculas;

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
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {

        peliculas.add(new Pelicula(
                1L,
                "Harry Potter y la piedra filosofal",
                "Chris Columbus",
                "Fantasia",
                2001
        ));

        peliculas.add(new Pelicula(
                2L,
                "El Señor de los Anillos",
                "Peter Jackson",
                "Fantasia",
                2001
        ));

        peliculas.add(new Pelicula(
                3L,
                "Spider-Man",
                "Sam Raimi",
                "Accion",
                2002
        ));

        peliculas.add(new Pelicula(
                4L,
                "Enredados",
                "Nathan Greno y Byron Howard",
                "Animacion",
                2010
        ));

        peliculas.add(new Pelicula(
                5L,
                "Interestelar",
                "Christopher Nolan",
                "Ciencia ficcion",
                2014
        ));
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getId().equals(id)) {
                return pelicula;
            }
        }

        return null;
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {

        Long nuevoId = 1L;

        for (Pelicula p : peliculas) {

            if (p.getId() >= nuevoId) {
                nuevoId = p.getId() + 1;
            }
        }

        pelicula.setId(nuevoId);
        peliculas.add(pelicula);

        return pelicula;
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(
            @PathVariable Long id,
            @RequestBody Pelicula datos) {

        for (int i = 0; i < peliculas.size(); i++) {

            if (peliculas.get(i).getId().equals(id)) {

                datos.setId(id);
                peliculas.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Pelicula actualizarParcial(
            @PathVariable Long id,
            @RequestBody Pelicula datos) {

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getId().equals(id)) {

                if (datos.getTitulo() != null) {
                    pelicula.setTitulo(datos.getTitulo());
                }

                if (datos.getDirector() != null) {
                    pelicula.setDirector(datos.getDirector());
                }

                if (datos.getGenero() != null) {
                    pelicula.setGenero(datos.getGenero());
                }

                if (datos.getAnio() != null) {
                    pelicula.setAnio(datos.getAnio());
                }

                return pelicula;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < peliculas.size(); i++) {

            if (peliculas.get(i).getId().equals(id)) {

                peliculas.remove(i);

                return "Pelicula eliminada correctamente";
            }
        }

        return "Pelicula no encontrada";
    }
}