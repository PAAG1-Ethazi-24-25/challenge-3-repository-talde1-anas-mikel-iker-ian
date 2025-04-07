package devworks.model.base;

public class Erosleak {
    private int id;
    private String email;
    private String izena;

    public Erosleak(int id, String email, String izena) {
        this.id = id;
        this.email = email;
        this.izena = izena;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getIzena() {
        return izena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    @Override
    public String toString() {
        return "Erosleak {id=" + id + ", email=" + email + ", izena=" + izena + "}";
    }
}
