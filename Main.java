import java.io.*;
import PositionInputStream.PositionInputStream;
import coding.PrimeNumber;
import coding.RSA_alg;
import DataOrganizer.DataOrganizer;
import UserInterface.Menu;
public class Main {

    static final String metroFileName = "Metro.jpg";
    static final String kwiatekFileName = "Kwiatek_1533.jpg";
    public static void main(String[] args) {
        String fileName = metroFileName; 
        PrimeNumber.init(129,255);
        RSA_alg.init();
        try{
            int wybor =1;
            while(wybor != 0)
            {
            PositionInputStream fileReader = new PositionInputStream(fileName);
            DataOrganizer dataOrganizer = new DataOrganizer(fileReader);
             wybor = Menu.handleUserEvents(dataOrganizer);
            //dataOrganizer.run();
            fileReader.close();
            }
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