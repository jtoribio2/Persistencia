package DAO;
import NEGOCI.MODEL.escola;

import java.util.List;

public interface ESCOLADAO extends DAO_GENERIC<escola,Integer>{
    @Override
    void inserir(escola o);

    @Override
    void modificar(escola o);

    @Override
    void eliminar(escola o);

    @Override
    List<escola> obtindreTots();

    @Override
    escola obtenir(Integer id);

    void mostrar(Integer id );
}
