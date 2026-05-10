package controller.Menus;

import View.MenusView.MenuCrearView;
import config.AppConfig;

import java.util.Scanner;

public class CrearMenu {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MenuCrearView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:
                    AppConfig.getEscaladorController().crear();
                    break;

                case 2:
                    AppConfig.getEscolaController().crearESV();
                    break;
                case 3:
                    AppConfig.getSectorController().crearSectorConVia();
                    break;
                case 4:
                    AppConfig.getViaController().crear();
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