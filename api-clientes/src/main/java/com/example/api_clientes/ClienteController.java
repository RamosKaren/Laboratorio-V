package com.example.api_clientes;

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
@RequestMapping("/api/clientes")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {

        clientes.add(new Cliente(
                1L,
                "Sofia",
                "Ramos",
                "sofia@gmail.com",
                "5555-1001"
        ));

        clientes.add(new Cliente(
                2L,
                "Karen",
                "Lopez",
                "karen@gmail.com",
                "5555-1002"
        ));

        clientes.add(new Cliente(
                3L,
                "Ricardo",
                "Hernandez",
                "ricardo@gmail.com",
                "5555-1003"
        ));

        clientes.add(new Cliente(
                4L,
                "Jose",
                "Martinez",
                "jose@gmail.com",
                "5555-1004"
        ));

        clientes.add(new Cliente(
                5L,
                "Karla",
                "Gomez",
                "karla@gmail.com",
                "5555-1005"
        ));
    }

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        return null;
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {

        Long nuevoId = 1L;

        for (Cliente c : clientes) {

            if (c.getId() >= nuevoId) {
                nuevoId = c.getId() + 1;
            }
        }

        cliente.setId(nuevoId);
        clientes.add(cliente);

        return cliente;
    }

    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody Cliente datos) {

        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getId().equals(id)) {

                datos.setId(id);
                clientes.set(i, datos);

                return datos;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcial(
            @PathVariable Long id,
            @RequestBody Cliente datos) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    cliente.setNombre(datos.getNombre());
                }

                if (datos.getApellido() != null) {
                    cliente.setApellido(datos.getApellido());
                }

                if (datos.getCorreo() != null) {
                    cliente.setCorreo(datos.getCorreo());
                }

                if (datos.getTelefono() != null) {
                    cliente.setTelefono(datos.getTelefono());
                }

                return cliente;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getId().equals(id)) {

                clientes.remove(i);

                return "Cliente eliminado correctamente";
            }
        }

        return "Cliente no encontrado";
    }
}