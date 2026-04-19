package services;

import daos.LlarDAO;
import db.ConnectionFactory;
import db.ConnectionProvider;

import model.entity.Llar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Llar_service implements LlarDAO {
   /**
    * @param o Objeto que añadiremos a la bd
      **/
    @Override
    public void inserir(Llar o) {
        final String SQL = "INSERT INTO llars VALUES (?,?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try(Connection conn = provider.getConnection()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL); //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o.getId_llar()); // REPRESENTA CADA "?" aqui indicamos el primer "?"
            ps.setInt(2,o.getId_via());
            ps.setInt(3,o.getMetres());
            ps.executeUpdate(); // AQUI NO PONEMOS NADA ESTO ES UNA CONFIRMACION DE QUE SE AHGA EL CAMBIO

            System.out.println("SE AÑADIO A LA BD  ");


        }
        catch (SQLException e ){
            System.out.println("Error  no se puedo añadir el registro ");
            e.printStackTrace();
        }
    }

    /**
     * @param o Llar  Objeto que  modificaremos a la bd
     * **/
    @Override
    public void modificar(Llar  o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "UPDATE llars  SET id_via=? , metres WHERE id_escola = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            //DEBEMO DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1,o.getId_via());
            ps.setInt(2,o.getMetres());
            ps.setInt(3,o.getId_llar());

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
        final String SQL = "DELETE FROM  llars  WHERE id_escola = ?"; // EL ? representa un parametro
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
    public List<Llar> obtindreTots() {
        List<Llar> llista = new ArrayList<>();
        String sql = "SELECT * FROM llars ";
        // ConnectionProvider provider = ConnectionFactory.getProvider("mysql");
        try (Connection conn = getCon()) {
            // Resulset  obejto que se encarga leer las filas del resultado de la comanda/consulta de mysql
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) { // Recorremos las filas que  la bd  nos ha dado

                int id_llar = rs.getInt("id_llar");
                int via =  rs.getInt("id_via");
                int metres = rs.getInt("metres");

                // Creamos el objeto con los datos de la fila actual
                Llar e = new Llar(id_llar,via,metres);


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
    public Llar  obtenir(Integer id) {

        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "SELECT * FROM llars  WHERE id_escola = ?  "; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = getCon()){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()) {
                //MIRAMOS SI HAY UNA FILA
                if (rs.next()) {
                    int id_llar = rs.getInt("id_llar");
                    int via =  rs.getInt("id_via");
                    int metres = rs.getInt("metres");

                    // Creamos el objeto con los datos de la fila actual
                    Llar e = new Llar(id_llar,via,metres);
                    return e ;
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
