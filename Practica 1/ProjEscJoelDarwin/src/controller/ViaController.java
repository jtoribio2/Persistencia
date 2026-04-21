package controller;

import model.entity.Via;
import service.ViaService;

import java.util.List;

public class ViaController {

    private final ViaService service;

    public ViaController(ViaService service) {
        this.service = service;
    }

    // 🔹 LISTAR TODAS
    public void listar() {

        List<Via> lista = service.obtenerTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay vías registradas");
            return;
        }

        lista.forEach(System.out::println);
    }

    // 🔹 BUSCAR POR NOMBRE
    public void buscarPorNombre(String nombre) {

        List<Via> lista = service.buscarPorNombre(nombre);

        if (lista.isEmpty()) {
            System.out.println("No se encontraron vías con ese nombre");
            return;
        }

        lista.forEach(System.out::println);
    }

    // 🔹 CREAR
    public void crear(Via v) {

        service.crear(v);
        System.out.println("Vía creada correctamente");
    }

    // 🔹 ELIMINAR
    public void eliminar(int id) {

        service.eliminar(id);
        System.out.println("Vía eliminada correctamente");
    }

    // 🔹 MODIFICAR
    public void modificar(Via v) {

        service.modificar(v);
        System.out.println("Vía modificada correctamente");
    }

    // 🔹 BUSCAR POR ID
    public void obtenerPorId(int id) {

        Via v = service.obtenerPorId(id);
        System.out.println(v);
    }
}