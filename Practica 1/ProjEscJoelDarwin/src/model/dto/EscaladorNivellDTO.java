package model.dto;

public class EscaladorNivellDTO {

    private String nom;
    private int edad;
    private String nivellMaxim;

    public EscaladorNivellDTO(String nom, int edad, String nivellMaxim) {
        this.nom = nom;
        this.edad = edad;
        this.nivellMaxim = nivellMaxim;
    }

    @Override
    public String toString() {
        return "──────── ESCALADOR ────────\n" +
                "Nom        : " + nom + "\n" +
                "Cognoms    : " + edad + "\n" +
                "Nivell max : " + nivellMaxim + "\n" +
                "──────────────────────────";
    }
}