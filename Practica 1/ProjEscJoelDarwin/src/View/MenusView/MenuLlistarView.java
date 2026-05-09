package View.MenusView;

public class MenuLlistarView {

    private static final String MENU_LLISTAR = """

        ╔══════════════════════════════════════════════╗
        ║                                              ║
        ║                LLISTAR DADES                 ║
        ║                                              ║
        ╠══════════════════════════════════════════════╣
        ║                                              ║
        ║   1. Llistar Escaladors                      ║
        ║   2. Llistar Escoles                         ║
        ║   3. Llistar Sectors                         ║
        ║   4. Llistar Vies                            ║
        ║                                              ║
        ║   0. Tornar                                  ║
        ║                                              ║
        ╚══════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_LLISTAR);
    }
}