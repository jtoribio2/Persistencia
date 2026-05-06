package controller.Menus.Admin;
import Inputs.Input;
import View.MenusView.*;
public class AdminMenu {
    public AdminMenu(){

    }
//MENU DONDE PODRA GESTIONAR(CREAR,MODIFICAR,ELIMINAR)
    public void PrintMenuAdmin(){
            String opcio = "";
        do {
            AdminMenusView.PrintAdminMenu();
            opcio = Input.ReadString();
            switch (opcio) {
                case "1":
                    //CODIGO
                    break;
                case "2":
                    //CODIGO
                    break;
                //CODIGO
                case "3":
                    //CODIGO
                    break;
                case "4":
                    //CODIGO

                    break;

                case "5":
                    //SALIR
                    return;
            }

        }  while (!opcio.equals("0")) ;
    }

}

