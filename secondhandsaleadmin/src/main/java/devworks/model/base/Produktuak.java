package devworks.model.base;

public class Produktuak {
    private int id;
    private String izena;
    private String deskribapena;
    private int prezioa;
    private int idKategoria;
    private String egoera;
    private String email;
    

    public Produktuak(int id, String izena, String deskribapena, int prezioa, int idKategoria, String egoera,
            String email) {
        this.id = id;
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.idKategoria = idKategoria;
        this.egoera = egoera;
        this.email = email;
        
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

    public int getPrezioa() {
        return prezioa;
    }

    public int getIdKategoria() {
        return idKategoria;
    }

    public String getEgoera() {
        return egoera;
    }

    public String getEmail() {
        return email;
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

    public void setPrezioa(int prezioa) {
        this.prezioa = prezioa;
    }

    public void setIdKategoria(int idKategoria) {
        this.idKategoria = idKategoria;
    }

    public void setEgoera(String egoera) {
        this.egoera = egoera;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return id +"_ "+izena;
    }
}