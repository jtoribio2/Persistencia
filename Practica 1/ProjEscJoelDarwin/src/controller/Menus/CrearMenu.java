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
                    //todo cuando pide la popularitat poner entre parentesis que es el 1 2 3 entre parentesis al lado (X)
                    //todo cuando te pide el tipo de via el tipo gel sale un cuadro en vez de 3 (X)
                    AppConfig.getEscolaController().crearESV();
                    break;
                case 3:
                    //todo cuando pregunta la popularitat hay que poner que es cada numero 1 2 3
                    //todo poner entre parentesis ejemplos de lo que deberia ponerse
                    //todo permite meter una via de hielo en una escola esportiva o clasica y al reves (Jou)
                    AppConfig.getSectorController().crearSectorConVia();
                    break;
                case 4:
                    //todo cuando pide el tipo de via falta un parentesis al final
                    //todo deja crear vias de hielo en vias clasicas y al reves (Jou)
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