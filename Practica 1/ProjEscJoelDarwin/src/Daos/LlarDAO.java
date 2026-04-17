package Daos;
import model.entity.Llar;

import java.util.List;

public interface LlarDAO extends Dao<Llar,Integer>{
    //METODOS GENERICPS
    @Override
    void inserir(Llar o);

    @Override
    void modificar(Llar o);

    @Override
    void eliminar(Llar o);

    @Override
    List<Llar> obtindreTots();

    Llar obtenir (Integer id);
    //METODOS  PROPIOS


}
