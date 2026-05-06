package model.dto;

public class EscolesRestricDTO {
    private String nom;
    private String via;
    private String rao;

    public EscolesRestricDTO(String nom, String via, String rao) {
        this.nom = nom;
        this.via = via;
        this.rao = rao;
    }

    @Override
    public String toString() {
        return "────── RESTRICCIÓN ──────\n" +
                "Escola : " + nom + "\n" +
                "Vía    : " + via + "\n" +
                "Razón  : " + rao + "\n" +
                "─────────────────────────";
    }


}
