package View.MenusView;

public class MenuPerEstatView {

    private static final String MENU_BUSCAR_PERESTAT ="""

        ╔══════════════════════════════════════════════╗
        ║                                              ║
        ║                 BUCAR PER ESTAT              ║
        ║                                              ║
        ╠══════════════════════════════════════════════╣
        ║                                              ║
        ║   1. Buscar Estat apte                       ║
        ║   2. Buscar Estat tancat                     ║
        ║                                              ║
        ║   0. Tornar                                  ║
        ║                                              ║
        ╚══════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_BUSCAR_PERESTAT);
    }
}
