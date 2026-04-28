package dao.impl.mysql;

import dao.interfaces.SectorDAO;
import db.ConnectionProvider;
import model.entity.Escola;
import model.entity.Sector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorMySQLDAO implements SectorDAO {

    private final ConnectionProvider provider;

    public SectorMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }

    // inserta un Objeto sector a la base de datos
    @Override
    public void inserir(Sector s) {

        String sql = """
        INSERT INTO sectors 
        (id_escola, nom, latitut, longitut, aproximacio, popularitat)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getId_escoles());
            ps.setString(2, s.getNom());
            ps.setFloat(3, s.getLatitut());
            ps.setFloat(4, s.getLongitut());
            ps.setString(5, s.getAproximacio());
            ps.setInt(6, s.getPopularitat());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando sector", e);
        }
    }

    // le pasamos un sector con los campos modificado lo encuentra atraves de su id y lo devuelve a la base de datos cambiado, si no lo encuientra informa de que no se ha modificado
    @Override
    public void modificar(Sector s) {

        String sql = """
        UPDATE sectors 
        SET id_escola = ?, 
            nom = ?, 
            latitut = ?, 
            longitut = ?, 
            aproximacio = ?, 
            popularitat = ?
        WHERE id_sector = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getId_escoles());
            ps.setString(2, s.getNom());
            ps.setFloat(3, s.getLatitut());
            ps.setFloat(4, s.getLongitut());
            ps.setString(5, s.getAproximacio());
            ps.setInt(6, s.getPopularitat());
            ps.setInt(7, s.getId_sector());

            int filas = ps.executeUpdate(); // si devuelve 0 filas quiere decir que no lo ha encontrado


            if (filas == 0) {
                throw new RuntimeException("No se encontró el sector con id " + s.getId_sector());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar sector", e);
        }
    }

    // Elimina de la bd un vetor a traves de su id
    @Override
    public void eliminar(Integer id) {

        String sql = "DELETE FROM sectors WHERE id_sector = ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando sector", e);
        }
    }

    // decuelve una lista con todos los objetos que hay en la tabla sector
    @Override
    public List<Sector> obtindreTots() {

        List<Sector> lista = new ArrayList<>();

        String sql = "SELECT * FROM sectors";

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
    public Sector obtenir(Integer id) {

        String sql = "SELECT * FROM sectors WHERE id_sector = ?";

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
    public List<Sector> buscarPorNombre(String nombre) {

        List<Sector> lista = new ArrayList<>();

        String sql = "SELECT * FROM sectors WHERE nom LIKE ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando por nombre", e);
        }

        return lista;
    }

    // metodo para cojer una fila de nuestra tabla y transformarlo en la clase sector
    private Sector map(ResultSet rs) throws SQLException {

        Sector s = new Sector();

        s.setId_sector(rs.getInt("id_sector"));
        s.setId_escoles(rs.getInt("id_escola"));
        s.setNom(rs.getString("nom"));

        return s;
    }

    @Override
    public Escola buscarEscola(int idSector) {

        String sql = """
        SELECT e.*
        FROM sectors s
        JOIN escoles e ON s.id_escola = e.id_escola
        WHERE s.id_sector = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSector);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return new Escola(
                            rs.getInt("id_escola"),
                            rs.getString("nom"),
                            rs.getString("lloc"),
                            rs.getString("aproximacio"),
                            rs.getInt("popularitat")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo escola del sector", e);
        }

        return null;
    }
}