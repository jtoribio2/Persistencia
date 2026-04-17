package model.entity;

public class Llar {
    private int id,id_via,metres;
    public Llar(int id,int id_via,int metres){
        this.id = id;
        this.id_via = id_via;
        this.metres = metres;
    }

    public int getId_via() {
        return id_via;
    }

    public void setId_via(int id_via) {
        this.id_via = id_via;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetres() {
        return metres;
    }

    public void setMetres(int metres) {
        this.metres = metres;
    }
}
