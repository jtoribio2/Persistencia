package controller.Menus;

import View.MenusView.MenuLlistarView;
import config.AppConfig;

import java.util.Scanner;

public class LlistarMenu {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MenuLlistarView.mostrarMenu();

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
                            .mostrarTots();

                    break;

                case 2:

                    AppConfig
                            .getEscolaController()
                            .mostrarTots();

                    break;

                case 3:

                    AppConfig
                            .getSectorController()
                            .mostrarTots();

                    break;

                case 4:

                    AppConfig
                            .getViaController()
                            .mostrarTots();

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