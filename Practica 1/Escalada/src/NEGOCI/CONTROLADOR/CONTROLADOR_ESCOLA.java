package NEGOCI.CONTROLADOR;
import CAPA_PERSISTENCIA.MYSQL_ESCOLA_DAO;
public class CONTROLADOR_ESCOLA {
private MYSQL_ESCOLA_DAO E = new MYSQL_ESCOLA_DAO();
    public void  Afegir(Integer id ){
        E.mostrar(id);
    }

}
