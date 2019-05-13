import java.io.*;

import PositionInputStream.PositionInputStream;
import RSA_alg.PrimeNumber;
import RSA_alg.RSA_alg;
import DataOrganizer.DataOrganizer;

public class Main {

    static final String metroFileName = "Metro.jpg";
    static final String kwiatekFileName = "Kwiatek_1533.jpg";
    public static void main(String[] args) {
        String fileName = metroFileName;
        new PrimeNumber(129,255);
        new RSA_alg();
         try{
            PositionInputStream fileReader = new PositionInputStream(fileName);
            DataOrganizer dataOrganizer = new DataOrganizer(fileReader);

            dataOrganizer.run();
            new Menu();
            fileReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        } catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'"); 
        }
    }

}