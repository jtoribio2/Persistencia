package controller.Menus;

import View.MenusView.MenuEliminarView;
import config.AppConfig;

import java.util.Scanner;

public class EliminarMenu {
    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MenuEliminarView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:
                    AppConfig.getEscaladorController().removedni();
                    break;

                case 2:
                    AppConfig.getEscolaController().removeEscola();
                    break;
                case 3:
                    AppConfig.getSectorController().eliminarSector();
                    break;
                case 4:
                    AppConfig.getViaController().eliminar();
                    break;

                case 0:
                    System.out.println("Tornant...");
                    break;

                default:
                    System.out.println("Opció incorrecta");
            }

        } while (opcio != 0);
    }

}
