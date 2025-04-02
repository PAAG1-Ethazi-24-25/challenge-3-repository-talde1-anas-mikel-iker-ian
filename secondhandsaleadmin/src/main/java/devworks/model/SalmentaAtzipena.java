package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Salmentak;

public class SalmentaAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public SalmentaAtzipena(String server, String db, String taula, String user, String pass) {
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

    public Salmentak searchSalmentak(String idProduktu) {
        String sql = "SELECT * FROM " + taula + " WHERE id_produktu = ?";

        Salmentak salmenta = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idProduktu);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salmenta = new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_bezero"), rs.getDate("data"), rs.getDouble("salmenta_prezioa"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmenta;
    }

    public List<Salmentak> getSalmentak() {
        String sql = "SELECT * FROM " + taula;
        List<Salmentak> salmentak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salmentak.add(new Salmentak(rs.getInt("id_salmenta"), rs.getInt("id_produktu"),
                        rs.getInt("id_bezero"), rs.getDate("data"), rs.getDouble("salmenta_prezioa")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salmentak;
    }
}
