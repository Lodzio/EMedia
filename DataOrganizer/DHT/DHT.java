package DataOrganizer.DHT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PositionInputStream.PositionInputStream;



public class DHT {
    // Defines a tuple of length and code, for use in the Huffman maps
    PositionInputStream inputStream;
    int id;

    // The array of Huffman maps: (length, code) -> value
    Map<Key,Integer> huffData = new HashMap<Key,Integer>();

    public DHT(PositionInputStream _inputStream) {
        inputStream = _inputStream;
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
        try{
            Integer code = 0;
            id = inputStream.read();
            System.out.format("Huffman table #%02X:\n", id);
            int[] countsByt = new int[16];
            inputStream.skip(2);
            for(int i=0;i<16;i++)
            {
                countsByt[i] = inputStream.read();
                    System.out.println(i+"."+countsByt[i]);
            }
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < countsByt[i]; j++) {
                    Key tmp = new Key(1+i,code);
                            huffData.put(tmp,  inputStream.read());
                            code++;
                        }
                        code =(code*2);
                    }
                    System.out.println();
                    
                    for(Key key: huffData.keySet())
                    {
                        System.out.format("    %04X at length %d = %02X\n", 
                        key.y,key.x,huffData.get(key));

                    }
        } catch(IOException e){
            System.out.println("error while reading DHT." + e.getMessage());
        }
    }
};

