package devworks.model.base;

public class Saltzaileak {
    public int id;
    public String email;
    public String izena;

    public Saltzaileak(int id, String email, String izena) {
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
        return izena;
    }
}
