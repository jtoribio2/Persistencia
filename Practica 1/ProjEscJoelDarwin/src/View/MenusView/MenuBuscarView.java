package View.MenusView;

public class MenuBuscarView {

    private static final String MENU_BUSCAR ="""

        ╔══════════════════════════════════════════════╗
        ║                                              ║
        ║                 BUSCAR DADES                 ║
        ║                                              ║
        ╠══════════════════════════════════════════════╣
        ║                                              ║
        ║   1. Buscar Escalador                        ║
        ║   2. Buscar Escola                           ║
        ║   3. Buscar Sector                           ║
        ║   4. Buscar Via                              ║
        ║                                              ║
        ║   0. Tornar                                  ║
        ║                                              ║
        ╚══════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_BUSCAR);
    }
}