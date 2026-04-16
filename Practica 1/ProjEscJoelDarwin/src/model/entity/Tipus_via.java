package model.entity;

public class Tipus_via {
    private int id;
    private String tipus;

    public Tipus_via(int id, String tipus) {
        this.id = id;
        this.tipus = tipus;
    }

    public int getId() {
        return id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
