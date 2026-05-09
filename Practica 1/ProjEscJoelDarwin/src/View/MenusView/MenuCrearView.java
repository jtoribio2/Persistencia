package View.MenusView;

public class MenuCrearView {

    private static final String MENU_CREAR ="""

        ╔══════════════════════════════════════════════╗
        ║                                              ║
        ║                 CREAR DADES                  ║
        ║                                              ║
        ╠══════════════════════════════════════════════╣
        ║                                              ║
        ║   1. Crear Escalador                         ║
        ║   2. Crear Escola                            ║
        ║   3. Crear Sector                            ║
        ║   4. Crear Via                               ║
        ║                                              ║
        ║   0. Tornar                                  ║
        ║                                              ║
        ╚══════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_CREAR);
    }
}