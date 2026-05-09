package service;

import dao.interfaces.LlarDAO;
import db.ConnectionProvider;
import model.entity.Llar;

import java.util.List;

public class LlarService {
private LlarDAO llardao;

public LlarService(LlarDAO ld){
    this.llardao = ld;
}
/**@param o Llar **/
public void   crearLlar(Llar o){
     llardao.inserir(o);
}

/**@param o Llar**/
public void modificarLlar(Llar o){
    llardao.modificar(o);
}
/**@param id Integer**/
public void eliminarLlar(Integer id ){
    llardao.eliminar(id );
}

/**@param id Integer **/
public Llar obtenerLlar(Integer id){
   return  llardao.obtenir(id);
}
/**@return List Llar**/
public List<Llar> obtenirTot(){
    List<Llar> ls = llardao.obtindreTots();
    return ls;
}


}
