package controller.Menus;
import java.util.*;

import Inputs.Input;
import View.MenusView.*;
import controller.Menus.Admin.AdminMenu;

public class MainMenu {
    public Scanner sc = new Scanner(System.in);
    String opcio = "";
   //OBJETOS MENUS DONDE DE AHI SACAMOS LOS METODOS
    AdminMenu Admin = new AdminMenu();
    public MainMenu() {

    }

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
