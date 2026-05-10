package controller.Menus;

import View.MenusView.MenuBuscarView;
import config.AppConfig;

import java.util.Scanner;

public class BuscarMenu {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MenuBuscarView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:

                    System.out.println("Introdueix ID Escalador:");

                    String dni = sc.nextLine();
                    sc.nextLine();

                    AppConfig
                            .getEscaladorController()
                            .buscarPerDni(dni);

                    break;

                case 2:

                    System.out.println("Introdueix nom Escola:");

                    String nomEscola = sc.nextLine();

                    AppConfig
                            .getEscolaController()
                            .buscarPorNombre(nomEscola);
                    break;

                case 3:

                    System.out.println("Introdueix nom Sector:");

                    String nomSector = sc.nextLine();

                    AppConfig
                            .getSectorController()
                            .buscarPorNombre(nomSector);

                    break;

                case 4:
                    System.out.println("Introdueix nom Via:");

                    String nomVia = sc.nextLine();

                    AppConfig
                            .getViaController()
                            .buscarPorNombre(nomVia);

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