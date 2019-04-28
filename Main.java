import java.io.*;
import PositionInputStream.PositionInputStream;
import DataOrganizer.DataOrganizer;

public class Main {

    static final String metroFileName = "Metro.jpg";
    static final String kwiatekFileName = "Kwiatek_1533.jpg";

    public static void main(String[] args) {
        String fileName = metroFileName;

        try{
            PositionInputStream fileReader = new PositionInputStream(fileName);
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