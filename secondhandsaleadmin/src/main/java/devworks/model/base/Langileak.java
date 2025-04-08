package devworks.model.base;

public class Langileak {
    private int id;
    private String izena;
    private String kargua;
    private String email;
    private int telefonoa;
    private String herria;
    private String postaKodea;
    private String helbidea;
    private String altaData;
    private String erabiltzailea;
    private String pasahitza;

    public Langileak(int id, String izena, String kargua, String email, int telefonoa, String herria,
            String postaKodea, String helbidea, String erregistroData, String erabiltzailea, String pasahitza) {
        this.id = id;
        this.izena = izena;
        this.kargua = kargua;
        this.email = email;
        this.telefonoa = telefonoa;
        this.herria = herria;
        this.postaKodea = postaKodea;
        this.helbidea = helbidea;
        this.altaData = erregistroData;
        this.erabiltzailea= erabiltzailea;
        this.pasahitza = pasahitza;
    }

    public int getId() {
        return id;
    }

    public String getIzena() {
        return izena;
    }

    public String getKargua() {
        return kargua;
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

    public String getErabiltzailea() {
        return erabiltzailea;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setKargua(String kargua) {
        this.kargua = kargua;
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

    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    @Override
    public String toString() {
        return "Langileak {id=" + id + ", izena=" + izena + ", kargua=" + kargua + ", email=" + email + ", telefonoa="
                + telefonoa + ", herria=" + herria + ", postaKodea=" + postaKodea + ", helbidea=" + helbidea
                + ", erregistroData=" + altaData + "}";
    }
}
