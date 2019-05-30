package coding;
import java.util.function.*;
 
public class XOR {
    private long key = 100;

    public byte xor(byte input){
        return (byte)(key ^ input);
    }

    public void encodeKey(Function<Long,Long> encode){
        key=encode.apply(key);
    }
    public void deodeKey(Function<Long,Long> decode){
        key=decode.apply(key);
    }
}