package com.example.api_tareas;

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
@RequestMapping("/api/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();

    public TareaController() {

        tareas.add(new Tarea(
                1L,
                "Estudiar Java",
                "Repasar conceptos de programacion orientada a objetos",
                "Alta",
                false
        ));

        tareas.add(new Tarea(
                2L,
                "Hacer ejercicio",
                "Realizar una hora de ejercicio",
                "Media",
                false
        ));

        tareas.add(new Tarea(
                3L,
                "Entregar laboratorio",
                "Completar y entregar el laboratorio de APIs REST",
                "Alta",
                false
        ));

        tareas.add(new Tarea(
                4L,
                "Leer un libro",
                "Leer al menos 30 paginas",
                "Baja",
                true
        ));

        tareas.add(new Tarea(
                5L,
                "Organizar archivos",
                "Ordenar los archivos de la universidad",
                "Media",
                false
        ));
    }

    @GetMapping
    public List<Tarea> obtenerTodas() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable Long id) {

        for (Tarea tarea : tareas) {

            if (tarea.getId().equals(id)) {
                return tarea;
            }
        }

        return null;
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {

        Long nuevoId = 1L;

        for (Tarea t : tareas) {

            if (t.getId() >= nuevoId) {
                nuevoId = t.getId() + 1;
            }
        }

        tarea.setId(nuevoId);
        tareas.add(tarea);

        return tarea;
    }

    @PutMapping("/{id}")
    public Tarea actualizar(
            @PathVariable Long id,
            @RequestBody Tarea datos) {

        for (int i = 0; i < tareas.size(); i++) {

            if (tareas.get(i).getId().equals(id)) {

                datos.setId(id);
                tareas.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Tarea actualizarParcial(
            @PathVariable Long id,
            @RequestBody Tarea datos) {

        for (Tarea tarea : tareas) {

            if (tarea.getId().equals(id)) {

                if (datos.getTitulo() != null) {
                    tarea.setTitulo(datos.getTitulo());
                }

                if (datos.getDescripcion() != null) {
                    tarea.setDescripcion(datos.getDescripcion());
                }

                if (datos.getPrioridad() != null) {
                    tarea.setPrioridad(datos.getPrioridad());
                }

                if (datos.getCompletada() != null) {
                    tarea.setCompletada(datos.getCompletada());
                }

                return tarea;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < tareas.size(); i++) {

            if (tareas.get(i).getId().equals(id)) {

                tareas.remove(i);

                return "Tarea eliminada correctamente";
            }
        }

        return "Tarea no encontrada";
    }
}