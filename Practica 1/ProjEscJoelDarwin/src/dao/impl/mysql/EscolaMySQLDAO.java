package dao.impl.mysql;


import dao.interfaces.EscolaDAO;

import db.ConnectionProvider;
import model.entity.Escola;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// AQUI IMPLEMENTAREMOS  LOS METODOS DE LA INTERFICIE DESDE EL CONTROLADOR LLAMAREMOS ESOS METODOS
// TODOS LOS METODOS ABRIRAN LA CONEXION
public class EscolaMySQLDAO implements EscolaDAO {
    private final ConnectionProvider provider;
    public EscolaMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }
    /**
     * @param o Objeto que añadiremos a la bd
     * **/
    @Override
    public void inserir(Escola o) {
        final String SQL = "INSERT INTO escoles (nom,lloc,aproximacio,popularitat) VALUES (?,?,?,?)"; // EL ? representa un parametro
        //CONECTAMOS A LA BD

        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)
        ){  //  LE MANDA A LA BD COM EL FORMATO DEL SQL NO HACE NADA DE MOMENTO
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            // REPRESENTA CADA "?" aqui indicamos el primer "?"

            ps.setString(1,o.getNom());
            ps.setString(2,o.getLloc());
            ps.setString(3,o.getAproximacio());
            ps.setInt(4,o.getPopularitat());
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
    public void modificar(Escola o) {
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "UPDATE escoles  SET nom=?,lloc=?,aproximacio=?,popularitat=? WHERE id_escola = ?"; // EL ? representa un parametro
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setString(1,o.getNom());
            ps.setString(2,o.getLloc());
            ps.setString(3,o.getAproximacio());
            ps.setInt(4,o.getPopularitat());
            ps.setInt(5, o.getId_escola());
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
        final String SQL = "DELETE FROM  escoles  WHERE id_escola = ?"; // EL ? representa un parametro
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
    public List<Escola> obtindreTots() {
        List<Escola> llista = new ArrayList<>();
        String SQL = "SELECT * FROM escoles ";

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
    public Escola obtenir(Integer id) {

        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = "SELECT * FROM escoles  WHERE id_escola = ?  "; // EL ? representa un parametro
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

    @Override
    public boolean isGel(Escola o ){
        //MODIFICAREMOS TODAS LAS PROPIEDADES MENOS LA ID
        final String SQL = """
                SELECT *
                FROM escoles e
                INNER JOIN sectors s ON s.id_escola=e.id_escola
                INNER JOIN vies v ON v.id_sector=s.id_sector
                WHERE v.id_tipus_via = 3 AND e.id_escola = ?
                
                """;
        //CONECTAMOS A LA BD
        try(Connection conn = provider.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            /*PREPARED STATMENT ES MEJOR QUE  STATMENT NO LE AFECTA ATAQUES SQL INJECTION */

            //DEBEMOS DE INDICAR EN CADA ? EL TIPO DE DATO QUE REEMPLEZARA EL ?
            ps.setInt(1, o.getId_escola());
            try (ResultSet rs = ps.executeQuery()) {
              boolean tf = rs.next();
                //DEVOLVERA TRUE O FALSE
                return tf;
            }


        }

        catch (SQLException e){
            System.out.println("Error no se pudo modificar el registro ");
            e.printStackTrace();
        }

        return false;
    }
    private Escola map(ResultSet rs) throws SQLException {

        Escola s = new Escola();

        s.setId_escola(rs.getInt("id_escola"));
        s.setNom(rs.getString("nom"));
        s.setLloc(rs.getString("lloc"));
        s.setAproximacio(rs.getString("aproximacio"));
        s.setPopularitat(rs.getInt("popularitat"));

        return s;
    }


}


