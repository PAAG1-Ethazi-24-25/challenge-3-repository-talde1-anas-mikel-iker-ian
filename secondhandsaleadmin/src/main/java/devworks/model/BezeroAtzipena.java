package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Bezeroak;

public class BezeroAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public BezeroAtzipena(String server, String db, String taula, String user, String pass) {
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

    public Bezeroak searchBezeroak(String herria) {
        String sql = "SELECT * FROM " + taula + " WHERE herria = ?";

        Bezeroak bezeroa = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, herria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroa = new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefona"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroa;
    }

    public List<Bezeroak> getBezeroak() {
        String sql = "SELECT * FROM " + taula;
        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroak.add(new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefona"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroak;
    }
}