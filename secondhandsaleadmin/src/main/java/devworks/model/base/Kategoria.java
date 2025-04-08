package devworks.model.base;

/**
 * Kategoria is used to searck for a category in the database.
 */

public class Kategoria {
    private int id;
    private String izena;
    private String deskribapena;

    public Kategoria(int id, String izena, String deskribapena) {
        this.id = id;
        this.izena = izena;
        this.deskribapena = deskribapena;
    }

    public int getId() {
        return id;
    }

    public String getIzena() {
        return izena;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    @Override
    public String toString() {
        return izena;
    }
}
