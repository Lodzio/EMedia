import java.io.*;
import PositionInputStream.PositionInputStream;
import coding.PrimeNumber;
import coding.RSA_alg;
import DataOrganizer.DataOrganizer;
import UserInterface.Menu;
public class Main {


 
    public static void main(String[] args) {
        
        try{
            int wybor =1;
            Menu.handleUserEventsRSA();
            while(wybor != 0)
            {
            PositionInputStream fileReader = new PositionInputStream(Menu.fileName);
            DataOrganizer dataOrganizer = new DataOrganizer(fileReader);
             wybor = Menu.handleUserEvents(dataOrganizer);
            //dataOrganizer.run();
            fileReader.close();
            }
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                Menu.fileName + "'");                
        } catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + Menu.fileName + "'"); 
        }
    }

}