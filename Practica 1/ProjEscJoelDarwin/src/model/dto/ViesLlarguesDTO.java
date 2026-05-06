package model.dto;

public class ViesLlarguesDTO {
        String Via;
        int llargada;
        public ViesLlarguesDTO(String Via, int llargada) {
            this.llargada= llargada ;
            this.Via = Via;
        }

        @Override
        public String toString() {
            return "──────────VIES LLARGUES───────────\n" +
                    "Via     :" + Via + "\n" +
                    "Llargada: " + llargada + "\n" +
                    "─────────────────────────────────";
        }




}
