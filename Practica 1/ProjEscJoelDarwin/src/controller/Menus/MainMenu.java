package controller.Menus;

import View.MenusView.MainMenuView;

import java.util.Scanner;

public class MainMenu {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        int opcio;

        do {

            MainMenuView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:
                    new CrearMenu()
                            .iniciar();
                    break;

                case 2:
                    new BuscarMenu()
                            .iniciar();
                    break;

                case 3:
                    System.out.println("MENU LLISTAR");
                    break;

                case 4:
                    System.out.println("MENU CONSULTES");
                    break;

                case 5:
                    System.out.println("MENU MODIFICAR");
                    break;

                case 6:
                    System.out.println("MENU ELIMINAR");
                    break;

                case 0:
                    System.out.println("Sortint...");
                    break;

                default:
                    System.out.println("Opció incorrecta");
            }

        } while (opcio != 0);
    }
}