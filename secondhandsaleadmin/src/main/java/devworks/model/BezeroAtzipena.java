package devworks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
            // Si la conexión es exitosa, puedes imprimir un mensaje en la consola
            // System.out.println("Conexión exitosa a la base de datos " + db + " en el
            // servidor " + server);
        } catch (SQLException e) {
            // Crear el Alert para mostrar el error
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo establecer la conexión");

            // Dependiendo del error, puedes dar diferentes detalles
            if (e.getErrorCode() == 1045) {
                alert.setContentText("Error: Usuario o contraseña incorrectos.");
            } else if (e.getErrorCode() == 0) {
                alert.setContentText("Error: No se pudo conectar con el servidor.");
            } else {
                alert.setContentText("Código de error: " + e.getErrorCode() + "\n" + e.getMessage());
            }

            // Mostrar el Alert
            alert.showAndWait();
        }
        return conn;
    }

    public Bezeroak bilatuBezeroa(int id) {
        String sql = "SELECT * FROM " + taula + " WHERE id_bezero = ?";
        Bezeroak bezeroa = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bezeroa = new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroa;
    }

    public boolean bezeroBaDago(int id) {
        String sql = "SELECT * FROM " + taula + " WHERE id_bezero = ?";
        boolean exists = false;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return exists;
    }

    public Bezeroak searchBezeroak(int id) {
        String sql = "SELECT * FROM " + taula + " WHERE id_bezero = ?";
        Bezeroak bezeroa = null;

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bezeroa = new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroa;
    }

    public List<Bezeroak> filterBezeroak(String herria) {
        String sql = "SELECT * FROM " + taula + " WHERE herria = ?";

        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, herria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroak.add(new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroak;
    }

    public List<Bezeroak> getBezeroak() {
        String sql = "SELECT * FROM " + taula;
        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bezeroak.add(new Bezeroak(rs.getInt("id_bezero"), rs.getString("izena"), rs.getString("email"),
                        rs.getInt("telefonoa"), rs.getString("herria"),
                        rs.getString("posta_kodea"), rs.getString("helbidea"), rs.getString("alta_data"),
                        rs.getString("erablitzaile_izena"), rs.getString("pasahitza")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bezeroak;
    }

    public List<String> getAllHerriak() {
        String sql = "SELECT herria FROM " + taula + " GROUP BY herria";
        List<String> herriak = new ArrayList<>();

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                herriak.add(rs.getString("herria"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return herriak;
    }

    public boolean bezeroaTxertatu(Bezeroak bezeroa) {
        String sql = "INSERT INTO " + taula
                + " (izena, email, telefonoa, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bezeroak(int id, String izena, String email, int telefonoa, String herria,
            // String postaKodea,
            // String helbidea, String erregistroData, String erabiltzalea, String pasahitza
            // )

            pstmt.setString(1, bezeroa.getIzena());
            pstmt.setString(2, bezeroa.getEmail());
            pstmt.setInt(3, bezeroa.getTelefonoa());
            pstmt.setString(4, bezeroa.getHelbidea());
            pstmt.setString(5, bezeroa.getHerria());
            pstmt.setString(6, bezeroa.getPostaKodea());
            pstmt.setString(7, bezeroa.getErabiltzalea());
            pstmt.setString(8, bezeroa.getPasahitza());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Errorea produktua txertatzean: " + e.getMessage());
            return false;
        }
    }

    public int handleAldatu(Bezeroak bezeroa) {
        String sql = "UPDATE " + taula
                + " SET izena = ?, email = ?, telefonoa = ?, helbidea = ?, herria = ?, posta_kodea = ?, erablitzaile_izena = ?, pasahitza = ? "
                + "WHERE id_bezero = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bezeroa.getIzena());
            pstmt.setString(2, bezeroa.getEmail());
            pstmt.setInt(3, bezeroa.getTelefonoa());
            pstmt.setString(4, bezeroa.getHelbidea());
            pstmt.setString(5, bezeroa.getHerria());
            pstmt.setString(6, bezeroa.getPostaKodea());
            pstmt.setString(7, bezeroa.getErabiltzalea());
            pstmt.setString(8, bezeroa.getPasahitza());
            pstmt.setInt(9, bezeroa.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return 0;
            }

            return 1;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return -1062;
            }
            return -1;
        }
    }

    public boolean deleteBezeroa(String izena) {

        if (taula == null || taula.isEmpty()) { // Para verificar que la taula no este vacia antes de empezar
            System.out.println("Errorea: taula ez da definitu");
            return false;
        }

        String sql = "DELETE FROM " + taula + " WHERE izena = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, izena.trim()); // Simplemente por si el usuario meter el nombre con espacios en blanco
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(izena + " bezeroa ezabatu egin da.");
                return true;
            } else {
                System.out.println(izena + " bezeroa ez da existitzen.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Errorea ezabatzerakoan: " + e.getMessage());
            return false;
        }
    }
}