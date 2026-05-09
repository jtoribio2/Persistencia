package View.MenusView;

public class MenuEliminarView {

    private static final String MENU_DEL ="""

        ╔══════════════════════════════════════════════╗
        ║                                              ║
        ║                 ELIMINAR DADES               ║
        ║                                              ║
        ╠══════════════════════════════════════════════╣
        ║                                              ║
        ║   1. Eliminar Escalador                      ║
        ║   2. Eliminar Escola                         ║
        ║   3. Eliminar Sector                         ║
        ║   4. Eliminar Via                            ║
        ║                                              ║
        ║   0. Tornar                                  ║
        ║                                              ║
        ╚══════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_DEL);
    }
}

