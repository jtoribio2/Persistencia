package model.entity.controlador;

import model.entity.Escola;
import resources.properties.services.MySqlEscolaDao;

public class ControladorEscola {
 public static MySqlEscolaDao dao = new MySqlEscolaDao(); // CREAMOS EL OBJETO  DAO PARA SACAR LOS METODOS
    public static  void Inserir(Escola e ){
        //LLAMOS A LA FUNCION
        dao.inserir(e);
    }


//OTROS.... CONFIRMAMELO
}
