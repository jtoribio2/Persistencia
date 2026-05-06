package model.dto;

public class ViesAptRecentDTO {
    private String nom;
    private int dies;

    public ViesAptRecentDTO(String nom, int dies) {
        this.nom = nom;
        this.dies = dies;
    }

    @Override
    public String toString() {
        return "──────── VIA DISPONIBLE ────────\n" +
                "Nom       : " + nom + "\n" +
                "fa (días)   : " + dies + "\n" +
                "─────────────────────────────────";
    }
}
