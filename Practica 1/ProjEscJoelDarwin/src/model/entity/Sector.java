package model.entity;

public class Sector {
    private int id;
    private int id_escoles;
    private String nom;
    private float latitut;
    private float longitut;
    private String aproximacio;
    private int popularitat;

    public Sector(int id, int id_escoles, String nom, float latitut, float longitut, String aproximacio, int popularitat) {
        this.id = id;
        this.id_escoles = id_escoles;
        this.nom = nom;
        this.latitut = latitut;
        this.longitut = longitut;
        this.aproximacio = aproximacio;
        this.popularitat = popularitat;
    }

    public int getId() {
        return id;
    }

    public int getId_escoles() {
        return id_escoles;
    }

    public String getNom() {
        return nom;
    }

    public float getLatitut() {
        return latitut;
    }

    public float getLongitut() {
        return longitut;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public int getPopularitat() {
        return popularitat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_escoles(int id_escoles) {
        this.id_escoles = id_escoles;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLatitut(float latitut) {
        this.latitut = latitut;
    }

    public void setLongitut(float longitut) {
        this.longitut = longitut;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public void setPopularitat(int popularitat) {
        this.popularitat = popularitat;
    }
}
