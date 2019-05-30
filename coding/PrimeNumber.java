package coding;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
   static List<Long> primes = new ArrayList<Long>();

    public static void init(long startPrimeNumber, long lastPrimeNumber) {

        boolean isPrimeNumber = true;

        long halfPrimeNumber=startPrimeNumber/2;
        while(startPrimeNumber !=lastPrimeNumber)
        {
            isPrimeNumber = true;
            for(long i=2;i<=halfPrimeNumber;i++){      
                if(startPrimeNumber%i==0){      
                    isPrimeNumber = false;
                    break;      
                }
            }
            if(isPrimeNumber) 
            {
                primes.add(startPrimeNumber);
            }     
            startPrimeNumber++;
        }  
    }
}