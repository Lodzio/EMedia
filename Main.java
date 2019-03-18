import java.io.*;

public class Main {

    public static void main(String[] args) {
        String fileName = "Kwiatek_1533.jpg";
        int buffer;
        System.out.println(fileName);
        try{
            FileInputStream fileReader = new FileInputStream(fileName);
            while((buffer = fileReader.read()) != -1) {
                System.out.println(Integer.toHexString(buffer));
            } 
            System.out.println(buffer);
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