package service;



import dao.interfaces.EscaladorDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;
import model.entity.Escalador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION
public class Escalador_service implements EscaladorDAO {
    /**
     * @param o Objeto que añadiremos a la bd
     * **/
    @Override
    public void inserir(Escalador o) {
        final String SQL = "INSERT INTO escaladors VALUES (?,?,?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try(Connection conn = provider.getConnection()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL); //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o.getId_escalador()); // REPRESENTA CADA "?" aqui indicamos el primer "?"
            ps.setString(2,o.getNom());
            ps.setInt(3,o.getEdat());
            ps.setInt(4,o.getEstil());
            ps.executeUpdate(); // AQUI NO PONEMOS NADA ESTO ES UNA CONFIRMACION DE QUE SE AHGA EL CAMBIO

            System.out.println("SE AÑADIO A LA BD  ");


        }
        catch (SQLException e ){
            System.out.println("Error  no se puedo añadir el registro ");
            e.printStackTrace();
        }
    }

    /**
     * @param o Objeto quemodificaremos a la bd
     * **/
    @Override
    public void modificar(Escalador  o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "UPDATE escalador  SET nom=?,edat=?,estil=? WHERE id_escalador  = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            //DEBEMO DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setString(1,o.getNom());
            ps.setInt(2,o.getEdat());
            ps.setInt(3,o.getEstil());
            ps.setInt(4,o.getId_escalador());

            ps.executeUpdate();
        }

        catch (SQLException e){
            System.out.println("Error  no se pudo modificar el registro ");
            e.printStackTrace();
        }

        System.out.println("SE MODIFICO  A LA BD  ");
    }
    /**
     * @param o Objeto que elimnaremos  a la bd
     * **/
    @Override
    public void eliminar(Integer o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "DELETE FROM  escaladors  WHERE id_escalador = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o);
            ps.executeUpdate();

            System.out.println("FILA ELIMINADA  A LA BD  ");
        }

        catch (SQLException e){
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }


    }
    /**
     * @return devuelve un List
     * **/
    @Override
    public List<Escalador> obtindreTots() {
        List<Escalador> llista = new ArrayList<>();
        String sql = "SELECT * FROM escaladors ";
        // ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try (Connection conn = getCon()) {
            // Resulset  obejto que se encarga leer las filas del resultado de la comanda/consulta de mysql
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) { // Recorremos las filas que  la bd  nos ha dado
                int id =  rs.getInt("id_escalador");
                String nom =  rs.getString("nom");
                int  edat =  rs.getInt("edat");
                int  estil =   rs.getInt("estil");


                // Creamos el objeto con los datos de la fila actual
                Escalador e = new Escalador(id,nom,edat,estil);


                // Lo metemos en nuestra lista
                llista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }

        return llista;
    }
    /**
     * @param id Intger introducimos un numero
     * **/
    @Override
    public Escalador  obtenir(Integer id) {

        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "SELECT * FROM escaladors  WHERE id_escalador= ?  "; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()) {
                //MIRAMOS SI HYA UNA FILA
                if (rs.next()) {
                    int id2 =  rs.getInt("id_escalador");
                    String nom =  rs.getString("nom");
                    int  edat =  rs.getInt("edat");
                    int  estil =   rs.getInt("estil");


                    // Creamos el objeto con los datos de la fila actual
                    Escalador e = new Escalador(id2,nom,edat,estil);
                    return  e;
                }
            }

            System.out.println("FILA ELIMINADA  A LA BD  ");
        }

        catch (SQLException e){
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }
        return null;

    }

    private Connection getCon() throws SQLException {
        return ConnectionFactory.getProvider("mysql").getConnection();
    }

}


