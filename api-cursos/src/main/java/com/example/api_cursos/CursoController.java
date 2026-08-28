package com.example.api_cursos;

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
@RequestMapping("/api/cursos")
public class CursoController {

    private List<Curso> cursos = new ArrayList<>();

    public CursoController() {

        cursos.add(new Curso(
                1L,
                "Programacion I",
                "Introduccion a la programacion y logica",
                4,
                "Presencial"
        ));

        cursos.add(new Curso(
                2L,
                "Bases de Datos",
                "Fundamentos de bases de datos y SQL",
                4,
                "Virtual"
        ));

        cursos.add(new Curso(
                3L,
                "Desarrollo Web",
                "Desarrollo de aplicaciones web",
                3,
                "Presencial"
        ));

        cursos.add(new Curso(
                4L,
                "Ciberseguridad",
                "Principios de seguridad informatica",
                4,
                "Virtual"
        ));

        cursos.add(new Curso(
                5L,
                "Analisis de Datos",
                "Introduccion al analisis y visualizacion de datos",
                3,
                "Hibrida"
        ));
    }

    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursos;
    }

    @GetMapping("/{id}")
    public Curso obtenerPorId(@PathVariable Long id) {

        for (Curso curso : cursos) {

            if (curso.getId().equals(id)) {
                return curso;
            }
        }

        return null;
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {

        Long nuevoId = 1L;

        for (Curso c : cursos) {

            if (c.getId() >= nuevoId) {
                nuevoId = c.getId() + 1;
            }
        }

        curso.setId(nuevoId);
        cursos.add(curso);

        return curso;
    }

    @PutMapping("/{id}")
    public Curso actualizar(
            @PathVariable Long id,
            @RequestBody Curso datos) {

        for (int i = 0; i < cursos.size(); i++) {

            if (cursos.get(i).getId().equals(id)) {

                datos.setId(id);
                cursos.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Curso actualizarParcial(
            @PathVariable Long id,
            @RequestBody Curso datos) {

        for (Curso curso : cursos) {

            if (curso.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    curso.setNombre(datos.getNombre());
                }

                if (datos.getDescripcion() != null) {
                    curso.setDescripcion(datos.getDescripcion());
                }

                if (datos.getCreditos() != null) {
                    curso.setCreditos(datos.getCreditos());
                }

                if (datos.getModalidad() != null) {
                    curso.setModalidad(datos.getModalidad());
                }

                return curso;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < cursos.size(); i++) {

            if (cursos.get(i).getId().equals(id)) {

                cursos.remove(i);

                return "Curso eliminado correctamente";
            }
        }

        return "Curso no encontrado";
    }
}
