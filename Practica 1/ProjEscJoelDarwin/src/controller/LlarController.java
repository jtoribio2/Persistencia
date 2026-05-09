package controller;

import model.entity.Escola;
import model.entity.Llar;
import service.EscolaService;
import service.LlarService;

import java.util.List;

public class LlarController {
    private   final LlarService service2;
    public LlarController(LlarService e ){this.service2= e;}
    /**@param e Llar(Objecte) Afegir dades**/
    public   void addLlar(Llar e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        service2.crearLlar(e);
    }
    /**@param e Modificar Llar**/
    public  void SetLlar(Llar e ) throws  Exception{
        if(e == null) throw new Exception("ERROR DADES BUIDES");
        service2.modificarLlar(e);
    }
    /**@param id Integer Elimina el llar **/
    public  void removeLlar(Integer id  ) throws  Exception{
        if(id < 0 ) throw  new Exception("Error id ");
        service2.eliminarLlar(id);
    }
/**@return List<Llar>**/
    public List<Llar> getList(){
        //Hacer comprovaciones o algo
        List<Llar> e = service2.obtenirTot();
        return e ;
    }
/**@param id Int
 * @return Llar**/
    public  Llar getLlar(Integer id ) {

        return   service2.obtenerLlar(id);
    }
}
