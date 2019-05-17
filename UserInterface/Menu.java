package UserInterface;
import java.util.Scanner;
import DataOrganizer.DHT.DHT;
 
public class Menu {
 
 
    public static void informacja(){
        System.out.println("Program do szyfrowania obrazów"
                                + "jpeg z zastosowaniem instrukji switch.");
    }
 
    public static int menu(){
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Encode");
        System.out.println("     2. Decode");
        System.out.println("     3. Informaja");
        System.out.println("     0. Koniec");
 
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
 
        return w;
    }
 
    public static void handleUserEvents()
    {
        Scanner in = new Scanner(System.in);
        int wybor = menu();
        while(wybor!=0){
            switch(wybor){
                case 1:
                    DHT.option= DHT.Option.ENCODE;
                    break;
                case 2:
                    DHT.option= DHT.Option.DECODE;
                    break;
                case 3:
                    informacja();
                    break;
            }
            wybor = menu();
        }
        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");
    }
}