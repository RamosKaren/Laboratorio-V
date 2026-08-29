package com.example.api_vehiculos;

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
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoController() {

        vehiculos.add(new Vehiculo(
                1L,
                "Toyota",
                "Corolla",
                2020,
                85000.00
        ));

        vehiculos.add(new Vehiculo(
                2L,
                "Honda",
                "Civic",
                2021,
                95000.00
        ));

        vehiculos.add(new Vehiculo(
                3L,
                "Mazda",
                "CX-30",
                2022,
                145000.00
        ));

        vehiculos.add(new Vehiculo(
                4L,
                "Kia",
                "K5",
                2023,
                135000.00
        ));

        vehiculos.add(new Vehiculo(
                5L,
                "Toyota",
                "Camry",
                2022,
                155000.00
        ));
    }

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculos;
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getId().equals(id)) {
                return vehiculo;
            }
        }

        return null;
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {

        Long nuevoId = 1L;

        for (Vehiculo v : vehiculos) {

            if (v.getId() >= nuevoId) {
                nuevoId = v.getId() + 1;
            }
        }

        vehiculo.setId(nuevoId);
        vehiculos.add(vehiculo);

        return vehiculo;
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(
            @PathVariable Long id,
            @RequestBody Vehiculo datos) {

        for (int i = 0; i < vehiculos.size(); i++) {

            if (vehiculos.get(i).getId().equals(id)) {

                datos.setId(id);
                vehiculos.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Vehiculo actualizarParcial(
            @PathVariable Long id,
            @RequestBody Vehiculo datos) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getId().equals(id)) {

                if (datos.getMarca() != null) {
                    vehiculo.setMarca(datos.getMarca());
                }

                if (datos.getModelo() != null) {
                    vehiculo.setModelo(datos.getModelo());
                }

                if (datos.getAnio() != null) {
                    vehiculo.setAnio(datos.getAnio());
                }

                if (datos.getPrecio() != null) {
                    vehiculo.setPrecio(datos.getPrecio());
                }

                return vehiculo;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < vehiculos.size(); i++) {

            if (vehiculos.get(i).getId().equals(id)) {

                vehiculos.remove(i);

                return "Vehiculo eliminado correctamente";
            }
        }

        return "Vehiculo no encontrado";
    }
}