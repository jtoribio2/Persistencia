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

public void   crearLlar(Llar o){
     llardao.inserir(o);
}


public void modificarLlar(Llar o){
    llardao.modificar(o);
}

public void eliminarLlar(Integer id ){
    llardao.eliminar(id );
}


public Llar obtenerLlar(Integer id){
   return  llardao.obtenir(id);
}

public List<Llar> obtenirTot(){
    List<Llar> ls = llardao.obtindreTots();
    return ls;
}


}
