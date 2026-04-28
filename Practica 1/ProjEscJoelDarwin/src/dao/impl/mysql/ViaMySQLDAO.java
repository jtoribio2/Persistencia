package dao.impl.mysql;

import dao.interfaces.ViaDAO;
import db.ConnectionProvider;
import model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaMySQLDAO implements ViaDAO {

    private final ConnectionProvider provider;

    public ViaMySQLDAO(ConnectionProvider provider) {
        this.provider = provider;
    }

    // INSERT
    @Override
    public void inserir(Via v) {

        String sql = """
            INSERT INTO vies 
            (id_sector, id_tipus_via, nom, llargada, dificultat, orientacio, ancoratge, troca)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getId_sector());
            ps.setInt(2, v.getId_tipus_via());
            ps.setString(3, v.getNom());
            ps.setInt(4, v.getLlargada());
            ps.setString(5, v.getDificultat());
            ps.setString(6, v.getOrientacio());
            ps.setString(7, v.getAncoratge());
            ps.setString(8, v.getTroca());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando via", e);
        }
    }

    // UPDATE
    @Override
    public void modificar(Via v) {

        String sql = """
            UPDATE vies SET
            id_sector=?, id_tipus_via=?, nom=?, llargada=?,
            dificultat=?, orientacio=?, ancoratge=?, troca=?
            WHERE id_via=?
        """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getId_sector());
            ps.setInt(2, v.getId_tipus_via());
            ps.setString(3, v.getNom());
            ps.setInt(4, v.getLlargada());
            ps.setString(5, v.getDificultat());
            ps.setString(6, v.getOrientacio());
            ps.setString(7, v.getAncoratge());
            ps.setString(8, v.getTroca());
            ps.setInt(9, v.getId_via());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró la vía");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error modificando via", e);
        }
    }

    //  DELETE
    @Override
    public void eliminar(Integer id) {

        String sql = "DELETE FROM vies WHERE id_via=?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando via", e);
        }
    }

    //  FIND ALL
    @Override
    public List<Via> obtindreTots() {

        List<Via> lista = new ArrayList<>();

        String sql = "SELECT * FROM vies";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo vias", e);
        }

        return lista;
    }

    //  buscar por id
    @Override
    public Via obtenir(Integer id) {

        String sql = "SELECT * FROM vies WHERE id_via=?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo via", e);
        }

        return null;
    }

    // Buscar por nombre
    @Override
    public List<Via> buscarPorNombre(String nombre) {

        List<Via> lista = new ArrayList<>();

        String sql = "SELECT * FROM vies WHERE nom LIKE ?";

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando vias", e);
        }

        return lista;
    }

    // MAP
    private Via map(ResultSet rs) throws SQLException {

        return new Via(
                rs.getInt("id_via"),
                rs.getInt("id_sector"),
                rs.getInt("id_tipus_via"),
                rs.getString("nom"),
                rs.getInt("llargada"),
                rs.getString("dificultat"),
                rs.getString("orientacio"),
                rs.getString("ancoratge"),
                rs.getString("troca")
        );
    }

    @Override
    public String viesDisponibles(Escola es ) {
        String resultat = "";
        final String SQL = """
                SELECT s.nom ,v.nom 
                FROM vies v
                LEFT JOIN disponibilitats d ON v.id_via = d.id_via
                LEFT JOIN sectors s ON s.id_sector = v.id_sector
                LEFT JOIN escoles es ON es.id_escola = s.id_escola
                WHERE s.id_escola = ? AND d.id_via IS NULL;
                """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL);
             ) {
            ps.setInt(1, es.getId_escola());

                //ALMACENAR LOS VALORES EN LAS VARIABLES
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultat += rs.getString("s.nom") + " " +
                            rs.getString("v.nom") + "\n";
                }
            }
            if(resultat.isBlank()) return "VIES DE LA ESCOLA NO DISPONIBLES";
            return resultat;
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo vias", e);
        }
    }
    @Override
    public Sector buscarSector(Via via) {

        if (via == null || via.getId_via() <= 0) {
            throw new RuntimeException("Via inválida");
        }

        String sql = """
        SELECT s.*
        FROM vies v
        JOIN sectors s ON v.id_sector = s.id_sector
        WHERE v.id_via = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, via.getId_via());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return new Sector(
                            rs.getInt("id_sector"),
                            rs.getInt("id_escola"),
                            rs.getString("nom"),
                            rs.getFloat("latitut"),
                            rs.getFloat("longitut"),
                            rs.getString("aproximacio"),
                            rs.getInt("popularitat")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo sector", e);
        }

        return null;
    }

    @Override
    public Escola buscarEscola(Via via) {

        if (via == null || via.getId_via() <= 0) {
            throw new RuntimeException("Via inválida");
        }

        String sql = """
        SELECT e.*
        FROM vies v
        JOIN sectors s ON v.id_sector = s.id_sector
        JOIN escoles e ON s.id_escola = e.id_escola
        WHERE v.id_via = ?
    """;

        try (Connection conn = provider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, via.getId_via());

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
            throw new RuntimeException("Error obteniendo escola", e);
        }

        return null;
    }

    /*
    @Override
    public List<Via>viesPerDificultat(String dades){
        List <Via> v
    }*/

}