package controller.Menus;

import View.MenusView.MenuModificarView;
import config.AppConfig;

import java.util.Scanner;

public class ModificarMenu {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MenuModificarView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:

                    AppConfig
                            .getEscaladorController()
                            .modificar();

                    break;

                case 2:
                    AppConfig
                            .getEscolaController()
                            .modificar();

                    break;

                case 3:

                    AppConfig
                            .getSectorController()
                            .modificar();

                    break;

                case 4:

                    AppConfig
                            .getViaController()
                            .modificar();

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