package model.entity;

public class Llar {
    private int id_llar,id_via,metres;
    public Llar(int id_llar,int id_via,int metres){
        this.id_llar = id_llar;
        this.id_via = id_via;
        this.metres = metres;
    }

    public Llar() {

    }

    public int getId_via() {
        return id_via;
    }

    public void setId_via(int id_via) {
        this.id_via = id_via;
    }

    public int getId_llar() {
        return id_llar;
    }

    public void setId_llar(int id_llar) {
        this.id_llar = id_llar;
    }

    public int getMetres() {
        return metres;
    }

    public void setMetres(int metres) {
        this.metres = metres;
    }
}
