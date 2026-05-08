package View.MenusView;

public class MainMenuView {

    private static final String MENU_PRINCIPAL = """
            
            ╔══════════════════════════════════════════════╗
            ║                                              ║
            ║                 APP ESCALADA                 ║
            ║                                              ║
            ║            Creat per Joel i Darwin           ║
            ║                                              ║
            ╠══════════════════════════════════════════════╣
            ║                                              ║
            ║   1. Crear                                   ║
            ║   2. Buscar                                  ║
            ║   3. Llistar                                 ║
            ║   4. Consultes                               ║
            ║   5. Modificar                               ║
            ║   6. Eliminar                                ║
            ║                                              ║
            ║   0. Sortir                                  ║
            ║                                              ║
            ╚══════════════════════════════════════════════╝
            
            Selecciona una opció:
            """;

    public static void mostrarMenu() {
        System.out.println(MENU_PRINCIPAL);
    }
}