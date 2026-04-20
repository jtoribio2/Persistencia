package model.entity;

public class Escola {
private int id_escola,popularitat;
private String nom,lloc,aproximacio;
    public Escola(int id_escola, String nom,String lloc, String aproximacio, int popularitat){
    this.id_escola = id_escola;
    this.nom = nom;
    this.lloc = lloc;
    this.aproximacio = aproximacio;
    this.popularitat = popularitat;
}

    public Escola() {

    }

    public int getId_escola() {
        return id_escola;
    }

    public void setId_escola(int id_escola) {
        this.id_escola = id_escola;
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
