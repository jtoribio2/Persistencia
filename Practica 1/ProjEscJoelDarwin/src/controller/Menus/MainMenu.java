package controller.Menus;
import java.util.*;

import Inputs.Input;
import View.MenusView.*;
import controller.Menus.Admin.AdminMenu;
//  ENLAZAR MENUS  IGUAL CON LOS SUBMENUS
//EL MENU PRINCIPAL PRIMERO REGUNTARA EL TIPO DE USUARIO
//USUARIO(SOLO CONSULTARA COSAS NO CREARA NI NADA) Y EL ADMIN(ES EL QUE ADMINISTRARA LOS USUARIOS ESCALADOR,ESCOLES...)
public class MainMenu {
    String opcio = "";
   //OBJETOS MENUS DONDE DE AHI SACAMOS LOS METODOS LO HICE A OBJETO PORQUE A FUTURO SE PODRIA CONFIURARLO EN HACER COSAS
    AdminMenu Admin = new AdminMenu();
    public MainMenu() {

    }
//IMRIMR MENU
    public void PrintMenuMain() {
        do {
            MainMenuView.PrintMainMenu();
            opcio = Input.ReadString();
            switch (opcio) {
                case "1":
                    MainMenuView.print("HAS ENTRADO COMO ADMIN");
                    Admin.PrintMenuAdmin();
                    break;
                case "2":
                    MainMenuView.print("HAS ENTRADO COMO USUARIO");
                    break;

                case "3":
                    MainMenuView.print("ADEU");
                    break;
            }

        }  while (!opcio.equals("3")) ;
    }
}
