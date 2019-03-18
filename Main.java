import java.io.*;
import DataOrganizer.DataOrganizer;

public class Main {


    public static void main(String[] args) {
        String fileName = "Kwiatek_1533.jpg";

        try{
            FileInputStream fileReader = new FileInputStream(fileName);
            DataOrganizer dataOrganizer = new DataOrganizer(fileReader);

            dataOrganizer.run();

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