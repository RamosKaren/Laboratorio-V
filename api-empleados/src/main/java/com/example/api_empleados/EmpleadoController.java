package com.example.api_empleados;

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
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoController() {

        empleados.add(new Empleado(
                1L,
                "Sofia",
                "Analista de Datos",
                6500.00,
                "Tecnologia"
        ));

        empleados.add(new Empleado(
                2L,
                "Karen",
                "Ciberseguridad",
                7000.00,
                "Seguridad Informatica"
        ));

        empleados.add(new Empleado(
                3L,
                "Ricardo",
                "Soporte Tecnico",
                4500.00,
                "Tecnologia"
        ));

        empleados.add(new Empleado(
                4L,
                "Jose",
                "Desarrollador de Software",
                6000.00,
                "Desarrollo"
        ));

        empleados.add(new Empleado(
                5L,
                "Karla",
                "Administradora de Sistemas",
                5800.00,
                "Tecnologia"
        ));

        empleados.add(new Empleado(
                6L,
                "Juan",
                "Analista de Redes",
                5200.00,
                "Infraestructura"
        ));
    }

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {

        for (Empleado empleado : empleados) {

            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }

        return null;
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {

        Long nuevoId = 1L;

        for (Empleado e : empleados) {

            if (e.getId() >= nuevoId) {
                nuevoId = e.getId() + 1;
            }
        }

        empleado.setId(nuevoId);
        empleados.add(empleado);

        return empleado;
    }

    @PutMapping("/{id}")
    public Empleado actualizar(
            @PathVariable Long id,
            @RequestBody Empleado datos) {

        for (int i = 0; i < empleados.size(); i++) {

            if (empleados.get(i).getId().equals(id)) {

                datos.setId(id);
                empleados.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Empleado actualizarParcial(
            @PathVariable Long id,
            @RequestBody Empleado datos) {

        for (Empleado empleado : empleados) {

            if (empleado.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    empleado.setNombre(datos.getNombre());
                }

                if (datos.getPuesto() != null) {
                    empleado.setPuesto(datos.getPuesto());
                }

                if (datos.getSalario() != null) {
                    empleado.setSalario(datos.getSalario());
                }

                if (datos.getDepartamento() != null) {
                    empleado.setDepartamento(datos.getDepartamento());
                }

                return empleado;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < empleados.size(); i++) {

            if (empleados.get(i).getId().equals(id)) {

                empleados.remove(i);

                return "Empleado eliminado correctamente";
            }
        }

        return "Empleado no encontrado";
    }
}