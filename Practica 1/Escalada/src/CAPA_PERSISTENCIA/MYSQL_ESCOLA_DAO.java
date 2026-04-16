package CAPA_PERSISTENCIA;
import DAO.ESCOLADAO;
import NEGOCI.MODEL.escola;
import BD_CONEXIONS.MYSQL;
import java.sql.*;
import java.util.List;
import PRESENTACIO.VISTA_1;
public class MYSQL_ESCOLA_DAO implements ESCOLADAO {


    @Override
    public void inserir(escola o) {


    }

    @Override
    public void modificar(escola o) {

    }

    @Override
    public void eliminar(escola o) {

    }

    @Override
    public List<escola> obtindreTots() {
        return List.of();
    }

    @Override
    public escola obtenir(Integer id) {
        return null;

    }


    @Override
    public void mostrar(Integer id ){
        final String URL = "jdbc:mysql://localhost:3306/escalada";
        final String USER = "root";
        final String PASS = "pepe@123";
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);

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
