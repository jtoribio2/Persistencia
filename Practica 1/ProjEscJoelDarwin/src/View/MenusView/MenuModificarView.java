package View.MenusView;

public class MenuModificarView {

    private static final String MENU_MODIFICAR = """

        ╔══════════════════════════════════════════════╗
        ║                                              ║
        ║               MODIFICAR DADES                ║
        ║                                              ║
        ╠══════════════════════════════════════════════╣
        ║                                              ║
        ║   1. Modificar Escalador                     ║
        ║   2. Modificar Escola                        ║
        ║   3. Modificar Sector                        ║
        ║   4. Modificar Via                           ║
        ║                                              ║
        ║   0. Tornar                                  ║
        ║                                              ║
        ╚══════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_MODIFICAR);
    }
}