public class MainD {
    public static void main(String[] args) {
        System.out.println("SE EJECUTA");
        /*AppConfig.getViaController().viesPerEstatTancada();
        AppConfig.getViaController().viesPerEstatApte();
        AppConfig.getEscolaController().viaDisponibles(AppConfig.getEscolaController().getEscola(1));
        AppConfig.getViaController().mostrarViesLlargues(1);*/

        MainMenu menuPrincipal = new MainMenu();

        menuPrincipal.PrintMenuMain();
    }
}
