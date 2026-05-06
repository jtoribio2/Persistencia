package model.dto;

public class ViesPerEstatApteDTO {

    private String nom,dificultat,orientacio,ancoratge,troca;
    private int llargada;
    public ViesPerEstatApteDTO(String nom, int llargada, String dificultat, String orientacio, String ancoratge, String troca  ){

        this.nom = nom;
        this.llargada= llargada;
        this.dificultat = dificultat;
        this.orientacio = orientacio;
        this.ancoratge = ancoratge;
        this.troca = troca;
    }

    @Override
    public String toString() {
        return "──────────── VIA ────────────\n" +
                "Nombre     : " + nom + "\n" +
                "Llargada   : " + llargada + "\n" +
                "Dificultat : " + dificultat + "\n" +
                "Orientcio  :" + orientacio + "\n" +
                "Ancoratge  :" +  ancoratge + "\n" +
                "Tipus de roca : " +  troca + "\n" +
                "─────────────────────────────";
    }
}
