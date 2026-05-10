package controller;

import com.mysql.cj.util.EscapeTokenizer;
import model.dto.EscolaDisponibleDTO;
import model.dto.EscolesRestricDTO;
import model.entity.Escola;
import model.entity.Sector;
import model.entity.Via;
import service.EscolaService;
import service.SectorService;


import java.util.List;
import java.util.Scanner;

public class EscolaController {
private static Scanner sc = new Scanner(System.in);
    private   final EscolaService service;
    private final SectorService sectorService;
    public EscolaController(EscolaService e , SectorService s ){
        this.service = e;
        this.sectorService = s;
    }

/**Eliminar**/
    public  void removeEscola( ) {
       try {
           List<Escola> es = service.obtenerTodos();
           System.out.println("Selecciona una escola per esborrar");
           for(int i = 0; i < es.size(); i++){
               System.out.println(es.get(i).getId_escola() + " " + es.get(i).getNom());
           }
           int id = sc.nextInt();
           sc.nextLine();
            //Primero elimnar vias y sector
           sectorService.ElimnarSVC(id);
           //Despues escola
           service.eliminarEscola(id);

       }
       catch (Exception err){
           System.out.println(err);
       }
    }
    public void mostrarTots() {

        try {

            List<Escola> lista =
                    service.obtenerTodos();

            for (Escola e : lista) {

                System.out.println(e);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
/**@param o Escola @return Retorna un boolea si es gel**/
public boolean isGel(Escola o ) {
try {
    return service.isGel(o);
}
catch (Exception e ){
    System.out.println(e);
    return false;
}

}
/**Mostra les escoles disponibles**/
public void escolesDisponibles(){
        List<EscolesRestricDTO> llista = service.escolesDisponibles();
        if (llista.isEmpty()){
            System.out.println("No hi han Escoles amb restriccions disponimbles actualment");
        }
        else {
            for (int i = 0; i < llista.size(); i++) {
                System.out.println(llista.get(i));
            }
        }
}
/**Escola Mostrar vies de esocla especifica**/
    public void viaDisponibles(  ){
        try {
            List<Escola> esc = service.obtenerTodos();
            System.out.println("Selecciona la escola");
            for(Escola es : esc){
                System.out.println(es.getId_escola() + " " + es.getNom());
            }
            int id = sc.nextInt();
            sc.nextLine();
            Escola obtEscola = service.obtenerPorId(id);
            List<EscolaDisponibleDTO> via = service.viasDisponibles(obtEscola);
            for(EscolaDisponibleDTO v : via){
                System.out.println(v);
            }
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
    /**Crear Escola **/
    public void crearESV(){

        try {

            // Escola
            System.out.println("NOM ESCOLA");
            String nomEscola = sc.nextLine();

            System.out.println("LLOC");
            String Escolalloc = sc.nextLine();

            System.out.println("APROXIMACIO");
            String Escolaprox = sc.nextLine();

            System.out.println("""
                POPULARITAT
                1.Baixa
                2.Mitjana
                3.Alta
                """);

            int popularitatEscola = sc.nextInt();
            sc.nextLine();

            Escola es = new Escola(
                    0,
                    nomEscola,
                    Escolalloc,
                    Escolaprox,
                    popularitatEscola
            );

            // Sector
            System.out.println("NOM SECTOR");
            String Nomsector = sc.nextLine();

            System.out.println("Latitud (x,x...)");
            float latitutsector = sc.nextFloat();
            sc.nextLine();

            System.out.println("Longitud (x,x...)");
            float longitutsector = sc.nextFloat();
            sc.nextLine();

            System.out.println("Aproximacio");
            String aproximacioSector = sc.nextLine();

            System.out.println("""
                POPULARITAT
                1.Baixa
                2.Mitjana
                3.Alta
                """);

            int popularitatSector = sc.nextInt();
            sc.nextLine();

            // Via
            System.out.println("""
                Tipus via
                1.Esportiva
                2.Classica
                3.Gel
                """);

            int tipusVia = sc.nextInt();
            sc.nextLine();

            System.out.println("NOM VIA");
            String nomVia = sc.nextLine();

            System.out.println("LLARGADA");
            int llargadavia = sc.nextInt();
            sc.nextLine();

            System.out.println("""
                Dificultat:
                4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,
                7a,7a+,7b,7b+,7c,7c+,
                8a,8a+,8b
                """);

            String dificultatvia = sc.nextLine();

            System.out.println("Orientacio (N,S,E,O)");
            String orientaciovia = sc.nextLine();

            System.out.println("Ancoratge");
            String ancoratgevia = sc.nextLine();

            System.out.println("""
                Troca:
                conglomerat
                granit
                calcaria
                arenisca
                altres
                """);

            String troca = sc.nextLine();

            // CREAR ESCOLA
            service.crearEscolaId(es);

            // CREAR SECTOR
            Sector s = new Sector(
                    0,
                    es.getId_escola(),
                    Nomsector,
                    latitutsector,
                    longitutsector,
                    aproximacioSector,
                    popularitatSector
            );

            // CREAR VIA
            Via v = new Via(
                    0,
                    tipusVia,
                    nomVia,
                    llargadavia,
                    dificultatvia,
                    orientaciovia,
                    ancoratgevia,
                    troca
            );

            // CREAR SECTOR + VIA
            sectorService.crearSectorConVia(s, v);

            System.out.println(
                    "Escola, sector i via creats correctament"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
/**@param nombre String **/
    public void buscarPorNombre(String nombre) {

        try {

            List<Escola> lista =
                    service.buscarPorNombre(nombre);

            for (Escola e : lista) {

                System.out.println(e);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
/**Modificar**/
    public void modificar() {

        Scanner sc = new Scanner(System.in);

        try {

            mostrarTots();

            System.out.println(
                    "Introdueix ID escola a modificar:"
            );

            int id = sc.nextInt();
            sc.nextLine();

            Escola actual =
                    service.obtenerPorId(id);

            if (actual == null) {

                System.out.println(
                        "No existeix aquesta escola"
                );

                return;
            }

            System.out.println("\nESCOLA ACTUAL:");
            System.out.println(actual);

            System.out.println("\nNou nom:");
            String nom = sc.nextLine();

            System.out.println("Nou lloc:");
            String lloc = sc.nextLine();

            System.out.println("Nova aproximacio:");
            String aproximacio = sc.nextLine();

            System.out.println("""

        Nova popularitat:
        1. Baixa
        2. Mitjana
        3. Alta
        """);

            int popularitat = sc.nextInt();

            Escola modificada =
                    new Escola(

                            id,
                            nom,
                            lloc,
                            aproximacio,
                            popularitat
                    );

            service.modificarEscola(modificada);

            System.out.println(
                    "Escola modificada correctament"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
