package UserInterface;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import DataOrganizer.DataOrganizer;
import PositionInputStream.PositionInputStream;
import coding.PrimeNumber;
import coding.RSA_alg;
import coding.XOR;

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

    public static int menu_rsa_byte(Scanner in) {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *            MENU_RSA_byte             *");
        System.out.println("     ****************************************");
        System.out.println("     1. 8 bit");
        System.out.println("     2. 128 bit");
        System.out.println("     0. Koniec");

        //Scanner in = new Scanner(System.in);
        int w =Integer.parseInt(in.nextLine());
        
        return w;
    }

    public static int handleUserEventsRSA()
    {
        int wybor = menu_rsa_byte(in);
        while (wybor != 0) {
            switch (wybor) {
            case 1:
                PrimeNumber.init(new BigInteger("129"),new BigInteger("255"));
                RSA_alg.init();
                XOR.encodeKey((Long x) -> RSA_alg.RSA_alg_encode(x));
                XOR.deodeKey((BigInteger x) -> RSA_alg.RSA_alg_decode(x));
                break;
            case 2:
            PrimeNumber.init(new BigInteger("170141183460469231731687303715884105729"),new BigInteger("340282366920938463463374607431768211455"));
                RSA_alg.init();
                    break;
            }
            break;
        }
        return wybor;
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