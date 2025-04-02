package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import devworks.model.base.Langileak;

public class LangileAtzipena {
    private String server;
    private String db;
    private String taula;

    String user;
    String pass;

    public LangileAtzipena(String server, String db, String taula, String user, String pass) {
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

    public Langileak searchLangileak(String herria) {
        String sql = "SELECT * FROM " + taula + " WHERE herria = ?";

        Langileak langilea = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, herria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                langilea = new Langileak(rs.getInt("id_langile"), rs.getString("izena"), rs.getString("kargua"),
                        rs.getString("email"), rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return langilea;
    }

    public List<Langileak> getLangileak() {
        String sql = "SELECT * FROM " + taula;
        List<Langileak> langileak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                langileak.add(new Langileak(rs.getInt("id_langile"), rs.getString("izena"), rs.getString("kargua"),
                        rs.getString("email"), rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return langileak;
    }
}
