package View.MenusView;

public class MenuConsultesView {

    private static final String MENU_CONSULTES = """

        ╔════════════════════════════════════════════════════════════════════╗
        ║                                                                    ║
        ║                           CONSULTES                                ║
        ║                                                                    ║
        ╠════════════════════════════════════════════════════════════════════╣
        ║                                                                    ║
        ║   1. Vies disponibles d'una escola                                 ║
        ║   2. Cercar vies per dificultat                                    ║
        ║   3. Cercar vies segons estat                                      ║
        ║   4. Escoles amb restriccions actives                              ║
        ║   5. Sectors amb més de X vies disponibles                         ║
        ║   6. Escaladors amb el mateix nivell màxim                         ║
        ║   7. Vies que han passat a "Apte" recentment                       ║
        ║   8. Vies més llargues d’una escola                                ║
        ║                                                                    ║
        ║   0. Tornar                                                        ║
        ║                                                                    ║
        ╚════════════════════════════════════════════════════════════════════╝

        Selecciona una opció:
        """;

    public static void mostrarMenu() {
        System.out.println(MENU_CONSULTES);
    }
}