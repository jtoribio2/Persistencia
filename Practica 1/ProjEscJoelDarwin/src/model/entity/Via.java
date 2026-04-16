package model.entity;

public class Via {
    private int id;
    private int id_sector;
    private int id_tipus_via;
    private String nom;
    private int llargada;
    private String dificultat;
    private String orientacio;
    private String ancoratge;
    private String troca;

    public Via(int id, int id_sector, int id_tipus_via, String nom, int llargada, String dificultat, String orientacio, String ancoratge, String troca) {
        this.id = id;
        this.id_sector = id_sector;
        this.id_tipus_via = id_tipus_via;
        this.nom = nom;
        this.llargada = llargada;
        this.dificultat = dificultat;
        this.orientacio = orientacio;
        this.ancoratge = ancoratge;
        this.troca = troca;
    }

    public int getId() {
        return id;
    }

    public int getId_sector() {
        return id_sector;
    }

    public int getId_tipus_via() {
        return id_tipus_via;
    }

    public String getNom() {
        return nom;
    }

    public int getLlargada() {
        return llargada;
    }

    public String getDificultat() {
        return dificultat;
    }

    public String getOrientacio() {
        return orientacio;
    }

    public String getAncoratge() {
        return ancoratge;
    }

    public String getTroca() {
        return troca;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_sector(int id_sector) {
        this.id_sector = id_sector;
    }

    public void setId_tipus_via(int id_tipus_via) {
        this.id_tipus_via = id_tipus_via;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLlargada(int llargada) {
        this.llargada = llargada;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }

    public void setOrientacio(String orientacio) {
        this.orientacio = orientacio;
    }

    public void setAncoratge(String ancoratge) {
        this.ancoratge = ancoratge;
    }

    public void setTroca(String troca) {
        this.troca = troca;
    }
}
