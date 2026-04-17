package CAPA_PERSISTENCIA;
import BD_CONEXIONS.MYSQL;
import DAO.EscolaDAO;
import NEGOCI.MODEL.Escola;

import java.sql.*;
import java.util.List;
import PRESENTACIO.VISTA_1;
public class MYSQL_ESCOLA_DAO implements EscolaDAO {


    @Override
    public void inserir(Escola o) {


    }

    @Override
    public void modificar(Escola o) {

    }

    @Override
    public void eliminar(Escola o) {

    }

    @Override
    public List<Escola> obtindreTots() {
        return List.of();
    }

    @Override
    public Escola obtenir(Integer id) {
        return null;

    }


    @Override
    public void mostrar(Integer id ){

        try {
            Connection conexion = MYSQL.openCon();

            if (conexion != null) {
                System.out.println("CONECTAT A LA BD ");
            }
            //OBJECTE PER EXECUTAR LES SENTENCIES SQL
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM escoles WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.printf("\nTenim que imprimir el format: id:%d , nom:%s ",rs.getInt("id"),rs.getString("nom"));
            }

            conexion.close();
        }
        catch (SQLException e){
            VISTA_1.missatge("ERRROR: " + e);

        }
    }
}
