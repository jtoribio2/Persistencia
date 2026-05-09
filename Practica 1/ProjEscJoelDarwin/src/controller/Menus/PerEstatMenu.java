package controller.Menus;

import View.MenusView.MenuConsultesView;
import View.MenusView.MenuPerEstatView;
import config.AppConfig;

import java.util.Scanner;

public class PerEstatMenu {

    private final Scanner sc = new Scanner(System.in);
    public  void iniciar() {

        int opcio;

        do {

            MenuPerEstatView.mostrarMenu();

            while (!sc.hasNextInt()) {

                System.out.println("Introdueix un número vàlid");
                sc.nextLine();
            }

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {

                case 1:
                    AppConfig.getViaController().viesPerEstatApte();
                    break;

                case 2:
                    AppConfig.getViaController().viesPerEstatTancada();
                    break;

                default:
                    System.out.println("Opció incorrecta");
            }

        } while (opcio != 0);
    }
}
