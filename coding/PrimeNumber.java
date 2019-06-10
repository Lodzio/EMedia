package coding;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
   static List<BigInteger> primes = new ArrayList<BigInteger>();

    public static void init(BigInteger startPrimeNumber, BigInteger lastPrimeNumber) {

        boolean isPrimeNumber = true;


        BigInteger halfPrimeNumber=startPrimeNumber.divide(new BigInteger("2"));
        while(startPrimeNumber.compareTo(lastPrimeNumber)<0)
        {
            isPrimeNumber = true;
            for(BigInteger i = BigInteger.valueOf(2);i.compareTo(halfPrimeNumber) <=0; i=i.add(BigInteger.ONE))
            { 
                   
                if(startPrimeNumber.mod(i).compareTo(BigInteger.ZERO) <=0){      
                    isPrimeNumber = false;
                    break;      
                }
            }
            if(isPrimeNumber) 
            {
                primes.add(startPrimeNumber);
            }     
            startPrimeNumber = startPrimeNumber.add(BigInteger.ONE);
        }  
    }
}