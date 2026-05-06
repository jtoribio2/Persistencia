package model.dto;

public class EscolaDisponibleDTO {
String Escola,Via;
    public EscolaDisponibleDTO(String Escola, String Via) {
        this.Escola = Escola ;
        this.Via = Via;
    }

    @Override
    public String toString() {
        return "────────" + Escola  +" Vies Disponibles"+"────────\n" +
                "Via       : " + Via + "\n" +
                "─────────────────────────────────";
    }
}
