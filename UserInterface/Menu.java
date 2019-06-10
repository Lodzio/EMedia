package UserInterface;

import java.io.FileNotFoundException;
import java.util.Scanner;
import DataOrganizer.DataOrganizer;
import PositionInputStream.PositionInputStream;

public class Menu {

    public static enum Option {
        NORMAL, ENCODE, DECODE
    }

    public static Option option = Option.NORMAL;
    public static long settingMinPositionToCheck;
    static Scanner in = new Scanner(System.in);

    public static void informacja() {
        System.out.println("Program do szyfrowania obrazów" + " jpeg z zastosowaniem instrukji switch.");
    }

    public static int menu(Scanner in) {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Encode");
        System.out.println("     2. Decode");
        System.out.println("     3. Informaja");
        System.out.println("     0. Koniec");

        //Scanner in = new Scanner(System.in);
        int w =Integer.parseInt(in.nextLine());
        
        return w;
    }

    public static int handleUserEvents(DataOrganizer dataOrganizer) {
        
        int wybor = menu(in);
        while (wybor != 0) {
            switch (wybor) {
            case 1:
                option = Option.ENCODE;
                dataOrganizer.run();
                break;
            case 2:
                option = Option.DECODE;
               /* PositionInputStream fileReader;
                try {
                    fileReader = new PositionInputStream("Setting.txt");
                    settingMinPositionToCheck = fileReader.getsettingMinPositionToCheck();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
                  
                    dataOrganizer.run();
                    break;
                case 3:
                    informacja();
                    break;
            }
            break;
        }
        return wybor;
       // System.out.println("     ****************************************");
       // System.out.println("\n     Koniec programu\n\n");
    }
}