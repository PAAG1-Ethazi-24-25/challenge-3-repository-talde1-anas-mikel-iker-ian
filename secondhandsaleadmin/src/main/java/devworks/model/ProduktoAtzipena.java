package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Produktoak;

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

    public Produktoak searchProduktoa(int idKategoria) {
        String sql = "SELECT * FROM " + taula + " WHERE id_kategoria = ?";

        Produktoak produktoa = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idKategoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produktoa = new Produktoak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getString("egoera"),
                        rs.getInt("id_saltzaile"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produktoa;
    }

    public List<Produktoak> getProduktoak() {
        String sql = "SELECT * FROM " + taula;
        List<Produktoak> produktuak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produktuak.add(new Produktoak(rs.getInt("id_produktu"), rs.getString("izena"),
                        rs.getString("deskribapena"),
                        rs.getInt("prezioa"), rs.getInt("id_kategoria"), rs.getString("egoera"),
                        rs.getInt("id_saltzaile")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produktuak;
    }
}