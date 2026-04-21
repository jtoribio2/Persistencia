package controller;

import model.entity.Escalador;
 import service.EscaladorService;


import java.util.List;

public class EscaladorController {

   private  EscaladorService dao ;

public EscaladorController(EscaladorService s){this.dao = s;}
    public  void addEscalador(Escalador e ) throws  Exception{
        dao.crearEscalador(e);
    }

    public   void SetEscalador(Escalador  e ) throws  Exception{
        dao.modificarEscalador(e);
    }

    public  void removeEscalador(Integer id ) throws  Exception{
        dao.eliminarEscalador(id);
    }

    public Escalador getEscaldorDni(String dni){
        return dao.obtenerPorDni(dni);
    }

    public  void removedni(String dni){
        dao.eliminarPerDni(dni);
    }
    public  List<Escalador> getList(){
        //Hacer comprovaciones o algo
        List<Escalador> e = dao.obtenerTodos();
        return e;
    }

    public  Escalador getEscalador(Integer id  ) throws Exception{
        return  dao.obtenerPorId(id);
    }
}
