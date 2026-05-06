package model.dto;

public class ViaPerDifDTO {
    private String via;
    private String dif;
    private String sector;
    private String escola;

    public ViaPerDifDTO(String via, String dif, String sector, String escola) {
        this.via = via;
        this.dif = dif;
        this.sector = sector;
        this.escola = escola;
    }

    @Override
    public String toString() {
        return "──────────── VIA ────────────\n" +
                "Nombre     : " + via + "\n" +
                "Dificultad : " + dif + "\n" +
                "Sector     : " + sector + "\n" +
                "Escola     : " + escola + "\n" +
                "─────────────────────────────";
    }

}
