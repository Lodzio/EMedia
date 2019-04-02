package DataOrganizer.DHT;

import PositionInputStream.PositionInputStream;
import java.io.*;
import java.util.HashMap;
import java.util.Map;



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

	public void run() {
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
                        key.x,key.y,huffData.get(key));

                    }
                    inputStream.skip(ctr);
        } catch(IOException e){
            System.out.println("error while reading DHT." + e.getMessage());
        }
    }
};

