package dao.impl.mysql;

import dao.interfaces.LlarDAO;
import db.ConnectionProvider;

import model.entity.Llar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LlarMySQLDAO  implements LlarDAO {
    private final ConnectionProvider provider;
    public LlarMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }
    /**
     * @param o Objeto que añadiremos a la bd
     * **/
    @Override
    public void inserir(Llar o) {
        final String SQL = "INSERT INTO llars (id_via,metres) VALUES (?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD

        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)
        ){  //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            // REPRESENTA CADA "?" aqui indicamos el primer "?"

            ps.setInt(1,o.getId_via());
            ps.setInt(2,o.getMetres());

            //CONFIRMAR UPDATE
            ps.executeUpdate();

            System.out.println("SE AÑADIO A LA BD ");


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
    public void modificar(Llar o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "UPDATE llars SET id_via=?,metres = ?  WHERE id_llar = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
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
        final String SQL = "DELETE FROM  llars  WHERE id_llar = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

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
        String SQL = "SELECT * FROM llars ";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            // Resulset  obejto que se encarga leer las filas del resultado de la comanda/consulta de mysql


            while (rs.next()) {
                llista.add(map(rs));
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
    public Llar obtenir(Integer id) {

        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "SELECT * FROM llars  WHERE id_llar = ?  "; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
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

    private Llar map(ResultSet rs) throws SQLException {

        Llar l = new Llar();

        l.setId_llar(rs.getInt("id_llar"));
        l.setMetres(rs.getInt("metres"));
        l.setId_via(rs.getInt("id_via"));


        return l;
    }



}
