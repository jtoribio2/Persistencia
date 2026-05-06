package dao.interfaces;
import model.dto.ViaPerDifDTO;
import model.entity.Sector;
import model.entity.Via;
import java.util.List;
import model.entity.Escola;
import model.dto.*;
public interface ViaDAO extends Dao<Via,Integer>{
    // METODOS GENERICOS
    @Override
    void inserir(Via o);

    @Override
    void modificar(Via o);

    @Override
    void eliminar(Integer o);

    @Override
    List<Via> obtindreTots();
    Via obtenir (Integer id);
    //METODOS  PROPIOS
    List<Via> buscarPorNombre(String nombre);

    //AQUI PONDRE EL METODO QUE MUETRAS LAS VIAS DISPONIBLES
   // String viesDisponibles(Escola es);
    //TODO: Pensar si ha que crar una tabla
    List<ViaPerDifDTO>viesPerDificultat(String d);


    List<ViesPerEstatApteDTO> viesPerEstatApte();
    List<ViesPerEstatTancatDTO> viesPerEstatTancat();
    List<Via> mostrarViesLlargues(int Escola);
    Sector buscarSector(Via via);

    List<Via> viasAptesRecent();
    Escola buscarEscola(Via via);
}
