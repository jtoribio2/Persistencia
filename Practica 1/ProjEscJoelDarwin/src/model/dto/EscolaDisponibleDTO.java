package model.dto;

public class EscolaDisponibleDTO {
String Escola,Via;
    public EscolaDisponibleDTO(String Escola, String Via) {
        this.Escola = Escola ;
        this.Via = Via;
    }

    @Override
    public String toString() {
        return "───────────────────────────────────\n" +
                "Escola     :" + Escola + "\n" +
                "Via        : " + Via + "\n" +
                "─────────────────────────────────";
    }
}
