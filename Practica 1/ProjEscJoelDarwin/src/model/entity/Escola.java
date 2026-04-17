package model.entity;

public class Escola {
private int id,popularitat;
private String nom,lloc,aproximacio;
    public Escola(int id, String nom,String lloc, String aproximacio, int popularitat){
    this.id = id;
    this.nom = nom;
    this.lloc = lloc;
    this.aproximacio = aproximacio;
    this.popularitat = popularitat;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(int popularitat) {
        this.popularitat = popularitat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }
}
