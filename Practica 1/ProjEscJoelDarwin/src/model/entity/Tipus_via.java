package model.entity;

public class Tipus_via {
    private int id_tipus_via;
    private String tipus;

    public Tipus_via(int id_tipus_via, String tipus) {
        this.id_tipus_via = id_tipus_via;
        this.tipus = tipus;
    }

    public int getId() {
        return id_tipus_via;
    }

    public String getTipus() {
        return tipus;
    }

    public void setId(int id) {
        this.id_tipus_via = id;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
