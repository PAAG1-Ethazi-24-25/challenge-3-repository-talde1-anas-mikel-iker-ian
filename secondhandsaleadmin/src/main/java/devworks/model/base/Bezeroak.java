package devworks.model.base;

public class Bezeroak {

    private int id;
    private String izena;
    private String email;
    private int telefonoa;
    private String herria;
    private String postaKodea;
    private String helbidea;
    private String altaData;
    private String erabiltzalea;
    private String pasahitza;

    public Bezeroak(int id, String izena, String email, int telefonoa, String herria, String postaKodea,
            String helbidea, String erregistroData, String erabiltzalea, String pasahitza ) {
        this.id = id;
        this.izena = izena;
        this.email = email;
        this.telefonoa = telefonoa;
        this.herria = herria;
        this.postaKodea = postaKodea;
        this.helbidea = helbidea;
        this.altaData = erregistroData;
        this.erabiltzalea= erabiltzalea;
        this.pasahitza = pasahitza;
    }

    public int getId() {
        return id;
    }

    

    public String getHerria() {
        return herria;
    }

    public String getErabiltzalea() {
        return erabiltzalea;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public String getIzena() {
        return izena;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefonoa() {
        return telefonoa;
    }

    public String getHerriIzena() {
        return herria;
    }

    public String getPostaKodea() {
        return postaKodea;
    }

    public String getHelbidea() {
        return helbidea;
    }

    public String getAltaData() {
        return altaData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefonoa(int telefonoa) {
        this.telefonoa = telefonoa;
    }

    public void setHerriIzena(String herria) {
        this.herria = herria;
    }

    public void setPostaKodea(String postaKodea) {
        this.postaKodea = postaKodea;
    }

    public void setHelbidea(String helbidea) {
        this.helbidea = helbidea;
    }

    public void setAltaData(String erregistroData) {
        this.altaData = erregistroData;
    }
    public void setHerria(String herria) {
        this.herria = herria;
    }

    public void setErabiltzalea(String erabiltzalea) {
        this.erabiltzalea = erabiltzalea;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    @Override
    public String toString() {
        return "Bezeroak{" +
                "id='" + id + '\'' +
                ", izena='" + izena + '\'' +
                ", email='" + email + '\'' +
                ", telefonoa=" + telefonoa +
                ", herria='" + herria + '\'' +
                ", postaKodea='" + postaKodea + '\'' +
                ", helbidea='" + helbidea + '\'' +
                ", erregistroData='" + altaData + '\'' +
                '}';
    }
}
