package model.entity;

public class Escalador {
    private int id,edat,estil;
    private String nom;

    public Escalador(String nom, int edat,int id, int estil){
        this.id = id;
        this.nom = nom;
        this.edat = edat;
        this.estil = estil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public int getEstil() {
        return estil;
    }

    public void setEstil(int estil) {
        this.estil = estil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
