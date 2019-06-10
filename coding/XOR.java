package coding;
import java.math.BigInteger;
import java.util.function.*;
 
public class XOR {
    private long key = 100;
    BigInteger codeKey;

    public byte xor(byte input){
        return (byte)(key ^ input);
    }

    public void encodeKey(Function<Long,BigInteger> encode){
        codeKey=encode.apply(key);
    }
    public void deodeKey(Function<BigInteger,Long> decode){
        key=decode.apply(codeKey);
    }
}