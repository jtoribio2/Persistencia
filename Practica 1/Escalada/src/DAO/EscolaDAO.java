package DAO;
import NEGOCI.MODEL.Escola;

import java.util.List;

public interface EscolaDAO extends DAO_GENERIC<Escola,Integer>{
    @Override
    void inserir(Escola o);

    @Override
    void modificar(Escola o);

    @Override
    void eliminar(Escola o);

    @Override
    List<Escola> obtindreTots();

    @Override
    Escola obtenir(Integer id);

    void mostrar(Integer id );
}
