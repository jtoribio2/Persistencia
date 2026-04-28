package controller;

import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import service.ViaService;

import java.util.List;

public class ViaController {

    private final ViaService service;

    public ViaController(ViaService service) {
        this.service = service;
    }

    // LISTAR TODAS
    public void listar() {
        try {
            List<Via> lista = service.obtenerTodos();
            lista.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // BUSCAR POR NOMBRE
    public void buscarPorNombre(String nombre) {
        try {
            List<Via> lista = service.buscarPorNombre(nombre);
            lista.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // CREAR
    public void crear(Via v) {

        try {
            service.crear(v);
            System.out.println("Vía creada correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // ELIMINAR
    public void eliminar(int id) {
        try {
            service.eliminar(id);
            System.out.println("Vía eliminada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // MODIFICAR
    public void modificar(Via v) {
        try {
            service.modificar(v);
            System.out.println("Vía modificada correctamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // BUSCAR POR ID
    public void obtenerPorId(int id) {
        try {
            Via v = service.obtenerPorId(id);
            System.out.println(v);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    //LISTAR VIAS DISPONIBLES
    public String viesDisponibles(Escola e ){
        try {
            String missatge = service.viesDisponibles(e);
            return missatge;
        }
        catch (Exception err ){
            System.out.println(err);
            return null;
        }
    }

    public Sector mostrarSector(Via via) {
        Sector s = service.buscarSector(via);
        return s;
    }

    public Escola mostrarEscola(Via via) {
        Escola e = service.buscarEscola(via);
        return e;
    }
}