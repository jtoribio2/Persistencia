package dao.impl.mysql;
import dao.interfaces.*;
import db.ConnectionProvider;
import model.entity.Escalador;
import model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscaladorMySQLDAO implements EscaladorDAO  {

    private final ConnectionProvider provider;

    public EscaladorMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }

    @Override
    public void inserir(Escalador e) {

        String sql =
        """
        INSERT INTO escaladors
        (dni,nom, edat, estil)
        VALUES (?,?, ?, ?)
        """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNom());
            ps.setInt(3, e.getEdat());
            ps.setInt(4, e.getEstil());

            ps.executeUpdate();

        } catch (SQLException x) {
            throw new RuntimeException("Error insertando sector", x);
        }
    }



    @Override
    public void modificar(Escalador e ) {

        String sql = """
        UPDATE escaladors
        SET   dni=?, nom = ? , edat = ? , estil= ? 
        WHERE id_escalador = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNom());
            ps.setInt(3, e.getEdat());
            ps.setInt(4, e.getEstil());
            ps.setInt(5, e.getEstil());


            int filas = ps.executeUpdate(); // si devuelve 0 filas quiere decir que no lo ha encontrado


            if (filas == 0) {
                throw new RuntimeException("No se encontró el Escalador  con id " + e.getId_escalador());
            }

        } catch (SQLException x) {
            throw new RuntimeException("Error al modificar Esclador ", x);
        }
    }

    @Override
    public void eliminar(Integer id) {

        String sql = "DELETE FROM escaladors  WHERE id_escalador= ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando sector", e);
        }
    }

    @Override
    public List<Escalador> obtindreTots() {

        List<Escalador> lista = new ArrayList<>();

        String sql = "SELECT * FROM escaladors";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sectores", e);
        }

        return lista;
    }

    // implementacion de obtenir bector le pasas un id y te devuelve el objeto sector que le has pedido si no lo encuentra devuelve null
    @Override
    public Escalador obtenir(Integer id)  {

        String sql = "SELECT * FROM escaladors WHERE id_escalador = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sector por id", e);
        }

        return null;
    }

    @Override
    public void eliminarDni(String dni){

        String sql = "DELETE FROM escaladors  WHERE dni = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,dni);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando sector", e);
        }
    }

    @Override
    public Escalador obtenirPerDni(String dni){
        String sql = "SELECT * FROM escaladors WHERE dni = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sector por id", e);
        }

        return null;
    }
    // metodo para cojer una fila de nuestra tabla y transformarlo en la clase sector
    private Escalador map(ResultSet rs) throws SQLException {

        Escalador e  = new Escalador();
        e.setId_escalador(rs.getInt("id_escalador"));
        e.setDni(rs.getString("dni"));
        e.setNom(rs.getString("nom"));
        e.setEdat(rs.getInt("edat"));
        e.setEstil(rs.getInt("estil"));

        return e;
    }




}
