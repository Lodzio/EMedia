package DataOrganizer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;



public class DHT {
    // Defines a tuple of length and code, for use in the Huffman maps
    InputStream inputStream;
    int id;

    // The array of Huffman maps: (length, code) -> value
    Map<Key,Integer> huffData = new HashMap<Key,Integer>();

    public DHT(InputStream _inputStream) {
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

	public void run() throws IOException {
        int i=0, j=0;
        int ctr = 0;
        Integer code = 0;
        int table;
        table   =  inputStream.read();
        System.out.format("Huffman table #%02X:\n", table);

        ctr++;
        int[] countsByt = new int[16];
        inputStream.skip(2);
        for(i=0;i<16;i++)
        {
            countsByt[i] = inputStream.read();
                System.out.println(i+"."+countsByt[i]);
                ctr++;
         }
         i=0;
        for (i = 0; i < 16; i++) {
            for (j = 0; j < countsByt[i]; j++) {
                Key tmp = new Key(1+i,code);
                int test = inputStream.read();
                        huffData.put(tmp,  test);
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
    }
    
};

