package model.dto;

public class ViesPerEstatTancatDTO {
    private String nom,motiu,dataI, dataF;
    public ViesPerEstatTancatDTO(String nom, String motiu, String dataI, String dataF  ){

        this.nom = nom;
        this.motiu = motiu;
        this.dataI = dataI;
        this.dataF = dataF;
    }


    @Override
    public String toString() {
        return "──────────── VIA ────────────\n" +
                "Nombre     : " + nom + "\n" +
                "Motiu      : " + motiu + "\n" +
                "data inici : " + dataI + "\n" +
                "data final :" + dataF + "\n" +
                "─────────────────────────────";
    }

}
