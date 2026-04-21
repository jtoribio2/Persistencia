package model.entity;

public class Escalador {
    private int id_escalador,edat,estil;
    private String nom,dni;

    public Escalador(int id_escalador,String dni,String nom, int edat, int estil){
        this.id_escalador = id_escalador;
        this.dni = dni;
        this.nom = nom;
        this.edat = edat;
        this.estil = estil;
    }

    public Escalador() {

    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
