package controller.Menus;

import View.MenusView.MenuConsultesView;
import config.AppConfig;

import java.util.Scanner;

public class ConsultarMenu {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MenuConsultesView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:
                    AppConfig.getEscolaController().viaDisponibles();
                    break;

                case 2:
                    AppConfig.getViaController().viesPerDificultat();

                    break;
                case 3:
                     new  PerEstatMenu()
                             .iniciar();
                    break;
                case 4:
                    AppConfig.getEscolaController().escolesDisponibles();
                    break;
                case 5:
                    AppConfig.getSectorController().sectorViesDisponibles();
                    break;

                case 6:
                    AppConfig.getEscaladorController().buscarPorNivel();
                    break;

                case 7 :
                    AppConfig.getViaController().viesAptesRecent();
                    break;
                case 8:
                    AppConfig.getViaController().mostrarViesLlargues();
                    break;

                default:
                    System.out.println("Opció incorrecta");
            }

        } while (opcio != 0);
    }
}
