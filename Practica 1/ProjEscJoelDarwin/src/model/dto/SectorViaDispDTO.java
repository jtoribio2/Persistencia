package model.dto;

public class SectorViaDispDTO {
    private String nom;
    private int count;

    public SectorViaDispDTO(String nom, int count) {
        this.nom = nom;
        this.count = count;
    }

    @Override
    public String toString() {
        return "──────── SECTOR ────────\n" +
                "nom            : " + nom + "\n" +
                "Vías disponibles  : " + count + "\n" +
                "────────────────────────";
    }
}
