package model.entity;

public class Escalador {
    private int id_escalador,edat,estil;
    private String nom;

    public Escalador(String nom, int edat,int id_escalador, int estil){
        this.id_escalador = id_escalador;
        this.nom = nom;
        this.edat = edat;
        this.estil = estil;
    }

    public int getId_escalador() {
        return id_escalador;
    }

    public void setId_escalador(int id_escalador) {
        this.id_escalador = id_escalador;
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
