package RSA_alg;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
   static List<Long> primes = new ArrayList<Long>();

    public PrimeNumber(long startPrimeNumber, long lastPrimeNumber) {

        int isPrimeNumber = 1;

        long halfPrimeNumber=startPrimeNumber/2;
        while(startPrimeNumber !=lastPrimeNumber)
        {
        isPrimeNumber = 1;
        for(long i=2;i<=halfPrimeNumber;i++){      
            if(startPrimeNumber%i==0){      
                isPrimeNumber = 0;
                break;      
            }
        }
            if(isPrimeNumber == 1) 
            {
                primes.add(startPrimeNumber++);
            }     
        }  
    }
}