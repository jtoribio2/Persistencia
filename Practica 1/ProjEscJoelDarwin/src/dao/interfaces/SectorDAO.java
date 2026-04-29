package dao.interfaces;

import model.entity.Escola;
import model.entity.Sector;
import java.util.List;

public interface SectorDAO extends Dao<Sector,Integer> {
   //METODOS GENERICOS
    @Override
    void inserir(Sector  o);

    @Override
    void modificar(Sector o);

    @Override
    void eliminar(Integer o);

    @Override
    List<Sector> obtindreTots();

    List<Sector> buscarPorNombre(String nombre);

    Sector  obtenir (Integer id);
    //METODOS  PROPIOS

    Escola buscarEscola(int idSector);


}
