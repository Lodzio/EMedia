package coding;

import java.math.BigInteger;
import java.util.Random;
import java.util.function.*;
 
public class XOR {
    private static long key = 100;
    static BigInteger codeKey;

    public static void generateKey(){
        Random generator = new Random();
        key = 1 + (int)(generator.nextDouble()*254);
        System.out.println("Orgin key: " + key);
    }

    public byte xor(byte input){
        return (byte)(key ^ input);
    }

    public static void encodeKey(Function<Long,BigInteger> encode){
        codeKey=encode.apply(key);
        System.out.println("codeKey: " + codeKey.toString());

    }
    public static void deodeKey(Function<BigInteger,Long> decode){
        key=decode.apply(codeKey);
        System.out.println("Key: " + key);
    }
}