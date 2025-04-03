package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Kategoria;
import devworks.model.base.Produktuak;

public class ProduktoAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public ProduktoAtzipena(String server, String db, String taula, String user, String pass) {
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.db = db;
        this.taula = taula;
    }

    public Connection konektatu() {
        String url = "jdbc:mariadb://" + server + "/" + db;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            // System.out.println(server + " zerbidoreko " + db + " datu-basera konektatu
            // zara.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1045)
                System.out.println("Erabiltzaile edo pasahitz okerrak");
            else if (e.getErrorCode() == 0)
                System.out.println("Ezin zerbitzariarekin konektatu");
            else
                System.out.println(e.getErrorCode() + "-" + e.getMessage());
        }
        return conn;
    }

    public List<Produktuak> searchProduktoa(int idKategoria) {
        String sql = "SELECT produktuak.id_produktu, produktuak.izena, produktuak.deskribapena, produktuak.prezioa, produktuak.id_kategoria, produktuak.egoera, bezeroak.email, produktuak.salduta FROM "
                + taula + " INNER JOIN bezeroak ON bezeroak.id_bezero = produktuak.id_produktu WHERE id_kategoria = ?";

        List<Produktuak> produktuak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idKategoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produktuak.add(new Produktuak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getString("egoera"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produktuak;
    }

    public List<Produktuak> getProduktoak() {
        String sql = "SELECT produktuak.id_produktu, produktuak.izena, produktuak.deskribapena, produktuak.prezioa, produktuak.id_kategoria, produktuak.egoera, bezeroak.email, produktuak.salduta FROM "
                + taula + " INNER JOIN bezeroak ON bezeroak.id_bezero = produktuak.id_produktu";
        List<Produktuak> produktuak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produktuak.add(new Produktuak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getString("egoera"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produktuak;
    }

    public List<Kategoria> getAllKategoriak() {
        String sql = "SELECT * FROM kategoriak";
        List<Kategoria> kategoriak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                kategoriak.add(
                        new Kategoria(rs.getInt("id_kategoria"), rs.getString("izena"), rs.getString("deskribapena")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return kategoriak;
    }
}