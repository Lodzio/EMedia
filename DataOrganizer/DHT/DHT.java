package DataOrganizer.DHT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PositionInputStream.PositionInputStream;
import RSA_alg.RSA_alg;



public class DHT {
    // Defines a tuple of length and code, for use in the Huffman maps
    PositionInputStream inputStream;
    int id;
   public  static Option option = Option.NOMRAL;

    // The array of Huffman maps: (length, code) -> value
    Map<Key,Integer> huffData = new HashMap<Key,Integer>();

    public DHT(PositionInputStream _inputStream) {
        inputStream = _inputStream;
    }
    public static enum Option {
        NOMRAL, ENCODE, DECODE;
    }

        public class Key {

            private final int x;
            private final int y;
        
            public Key(int x, int y) {
                this.x = x;
                this.y = y;
            }
        
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Key)) return false;
                Key key = (Key) o;
                return x == key.x && y == key.y;
            }
        
            @Override
            public int hashCode() {
                int result = x;
                result = 31 * result + y;
                return result;
            }
        
        }

    public void run()
        {
            
            switch (option) {
                case NOMRAL: 
                    normal();
                    break;
    
                case ENCODE: 
                    encode();
                    break;
    
                case DECODE:
                    decode();
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
	public void normal() {
        try{
            int ctr = 0;
            Integer code = 0;
            id = inputStream.read();
            System.out.format("Huffman table #%02X:\n", id);

            ctr++;
            int[] countsByt = new int[16];
            inputStream.skip(2);
            for(int i=0;i<16;i++)
            {
                countsByt[i] = inputStream.read();
                    System.out.println(i+"."+countsByt[i]);
                    ctr++;
            }
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < countsByt[i]; j++) {
                    Key tmp = new Key(1+i,code);
                            huffData.put(tmp,  inputStream.read());
                            code++;
                            ctr++;
                        }
                        code =(code*2);
                    }
                    System.out.println();
                    
                    for(Key key: huffData.keySet())
                    {
                        System.out.format("    %04X at length %d = %02X\n", 
                        key.y,key.x,huffData.get(key));

                    }
                    // inputStream.skip(ctr);
        } catch(IOException e){
            System.out.println("error while reading DHT." + e.getMessage());
        }
    }
    public void encode() {
        try{
            int ctr = 0;
            id = inputStream.read();
            System.out.format("Huffman table #%02X:\n", id);

            ctr++;
            int[] countsByt = new int[16];
            inputStream.skip(2);
            for(int i=0;i<16;i++)
            {
                countsByt[i] = inputStream.read();
                    System.out.println(i+"."+countsByt[i]);
                    ctr++;
            }
            List<String>  str = new ArrayList<String>();

            long posStart =0;
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < countsByt[i]; j++) 
                    {    
                    posStart = inputStream.getPos();
                    str.add(Long.toString(RSA_alg.RSA_alg_encode(inputStream.read())));
                    }
                }
                changeFile(posStart, str);
        } catch(IOException e){
            System.out.println("error while reading DHT." + e.getMessage());
        }
    }

    public void decode() {
        try{
            int ctr = 0;

            id = inputStream.read();
            System.out.format("Huffman table #%02X:\n", id);

            ctr++;
            int[] countsByt = new int[16];
            inputStream.skip(2);

            for(int i=0;i<16;i++)
            {
                countsByt[i] = inputStream.read();
                    System.out.println(i+"."+countsByt[i]);
                    ctr++;
            }
            List<String>  str = new ArrayList<String>();
            int pos =0;
            long posStart =0;
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < countsByt[i]; j++) 
                    {    
                    posStart = inputStream.getPos();
                    str.add(Long.toString(RSA_alg.RSA_alg_decode(inputStream.read())));
                    }
                }
                changeFile(posStart, str);
        } catch(IOException e){
            System.out.println("error while reading DHT." + e.getMessage());
        }
    }

    void changeFile(long posStart, List<String> str) {
        try {
            PositionInputStream is = new PositionInputStream("../Metro.jpg");
            FileWriter fstream = new FileWriter("../Metro.jpg");
            BufferedWriter out = new BufferedWriter(fstream);
            int inputLine;
            while ((inputLine = is.read()) != -1) {
                if (inputLine == 0)
                    is.skip(posStart);
                    for(String s : str)
                        out.write(s);
                }
            out.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
};

