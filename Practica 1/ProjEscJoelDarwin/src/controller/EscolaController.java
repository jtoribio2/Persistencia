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
/**@param e Escola Objeto**/
    public   void addEscola(Escola e ) {

        try{
           service.crearEscola(e);
        }
        catch (Exception err ){
            System.out.println(err);
        }
    }
/**@param e Escola objeto**/
    public  void SetEscola(Escola e )  {

        try {
            service.modificarSector(e);
        }
        catch(Exception err){
            System.out.println(err);
        }
    }
/**Eliminar**/
    public  void removeEscola( ) {
       try {
           List<Escola> es = service.obtenerTodos();
           System.out.println("Selecciona una escola per esborrar");
           for(int i = 0; i < es.size(); i++){
               System.out.println((i+1) + " " + es.get(i).getNom());
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
/**@param id Integer @return Escola(Obj)**/
    public  Escola getEscola(Integer id ) {
        try {
            return service.obtenerPorId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
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

/**@param es Escola Mostrar vies de esocla especifica**/
    public void viaDisponibles(Escola es  ){
        try {
            List<EscolaDisponibleDTO> via = service.viasDisponibles(es);
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
            //Escola
            System.out.println("NOM ESCOLA");
            String nomEscola = sc.nextLine();

            System.out.println("LLOC");
            String Escolalloc = sc.nextLine();

            System.out.println("APROXIMACIO");
            String Escolaprox = sc.nextLine();

            System.out.println("POPULARITAT 1-3\n1.Baixa\n2.Mitjana\n3.Alta");
            int  popularitatEscola = sc.nextInt();
            sc.nextLine();
            Escola es = new Escola(0,nomEscola,Escolalloc,Escolaprox,popularitatEscola);



            //Sectors
            System.out.println("Nom Sectors");
            String Nomsector = sc.nextLine();

            System.out.println("Latitud (x,x...):");
            float latitutsector = sc.nextFloat();
            sc.nextLine();
            System.out.println("Longitud(x,x...):");
            float longitutsector = sc.nextFloat();
            sc.nextLine();
            System.out.println("Aproximacio(x min...):");
            String aproximacioSector = sc.nextLine();

            System.out.println("POPULARITAT 1-3\n1.Baixa\n2.Mitjana\n3.Alta" );
            int  popularitatSector = sc.nextInt();
            sc.nextLine();


            //Via
            System.out.println("Tipus de vies\n1.Esportiva\n2.Classica\n 3.Gel");
            int tipusVia = sc.nextInt();
            sc.nextLine();

            System.out.println("NOM VIA");
            String nomVia = sc.nextLine();

            System.out.println("Llargada(x metres ):");
            int llargadavia = sc.nextInt();

            sc.nextLine();

            System.out.println("Dificultat:\n Nivell: 4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b");
            String dificultatvia  = sc.nextLine();

            System.out.println("Orientacio(N,S,W,E): ");
            String orientaciovia = sc.nextLine();

            System.out.println("Ancoratge");
            String ancoratgevia = sc.nextLine();

            System.out.println("Troca:  [conglomerat, granit, calcaria, arenisca, altres]");
            String troca = sc.nextLine();


            Sector s = null;
            Via v = null;

            try {
                service.crearEscolaId(es);
                //Aqui porque en mi metodo modifico el id Escola y inserto a la bd
                 s = new Sector(0,es.getId_escola(),Nomsector,latitutsector,longitutsector,aproximacioSector,popularitatSector);
                v = new Via(s.getId_sector(),tipusVia,nomVia,llargadavia,dificultatvia,orientaciovia,ancoratgevia,troca);

                sectorService.crearSectorConVia(s, v);


                System.out.println("Escola y Sector, vía creados correctamente");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

                try {

                        service.eliminarEscola(es.getId_escola()); // deshacer escola

                } catch (Exception rollbackEx) {
                    System.out.println("Error en rollback: " + rollbackEx.getMessage());
                }
            }

        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

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
