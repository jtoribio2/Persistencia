package Daos;

import model.entity.Escalador;
import model.entity.Sector;
import java.util.List;

public interface SectorDAO extends Dao<Sector,Integer> {
   //METODOS GENERICOS
    @Override
    void inserir(Sector  o);

    @Override
    void modificar(Sector o);

    @Override
    void eliminar(Sector  o);

    @Override
    List<Sector> obtindreTots();

    Sector  obtenir (Integer id);
    //METODOS  PROPIOS


}
