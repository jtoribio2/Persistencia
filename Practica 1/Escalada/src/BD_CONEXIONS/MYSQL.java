package BD_CONEXIONS;
import java.sql.*;
public class MYSQL {

    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/escalada?serverTimezone=UTC+1";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static Connection conexion = null;
    // OBTENIR REFERENCIA DE COMNEXIO PER GESTIONAR
    public static Connection getConexio(){
        return conexion;
    }
    //OBRIR CONEXIO
    public static  void OnBd() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            if (conexion != null) {
                System.out.println("CONECTAT A LA BD ");
            }
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR : " + e.getMessage());
        }
    }
    //TANCAR CONEXIO
    public static void offBd() {
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
