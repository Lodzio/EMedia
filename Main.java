import java.io.*;
import java.math.*;

import PositionInputStream.PositionInputStream;
import DataOrganizer.DataOrganizer;

public class Main {

    static final String metroFileName = "Metro.jpg";
    static final String kwiatekFileName = "Kwiatek_1533.jpg";
    static int modInverse(int a, int m) 
    { 
        a = a % m; 
        for (int x = 1; x < m; x++) 
           if ((a * x) % m == 1) 
              return x; 
        return 1; 
    } 
    public static void main(String[] args) {
        String fileName = metroFileName;
        // https://www.di-mgt.com.au/rsa_alg.html#note4
        int data = 26;
        int q = 11;
        int p = 3;
        int n = p*q;
        int fi = (p - 1)*(q - 1);
        int e = 3;
        int d = modInverse(e, fi);
        int c = (int)Math.pow(data, e) % n;
        int m = (int)Math.pow(c, d) % n;
        System.out.println(data + " " + c + " " + m);
        // try{
            // PositionInputStream fileReader = new PositionInputStream(fileName);
            // DataOrganizer dataOrganizer = new DataOrganizer(fileReader);

            // dataOrganizer.run();

            // fileReader.close();
        // } catch(FileNotFoundException ex) {
        //     System.out.println(
        //         "Unable to open file '" + 
        //         fileName + "'");                
        // } catch(IOException ex) {
        //     System.out.println(
        //         "Error reading file '" 
        //         + fileName + "'"); 
        // }
    }

}