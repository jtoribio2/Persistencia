package Inputs;
import java.util.*;
public class Input {
    private static Scanner sc = new Scanner(System.in);
    //AQUI ES DONDE PONEMOS LSO INPUTS
    public static  String ReadString(){
       try {
           String txt = sc.nextLine();
           return txt;
       }
       catch (Exception e ){
           System.out.println(e);
           return null;
       }

    }
}
