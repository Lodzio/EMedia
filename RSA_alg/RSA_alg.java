package RSA_alg;

import java.io.*;
import java.math.*;

public class RSA_alg{
    
            // https://www.di-mgt.com.au/rsa_alg.html#note4
            static int sizePrimes;
            static long e = 3;
            private static long d;
            private static long n;

            public RSA_alg()
            {
                sizePrimes = PrimeNumber.primes.size();
                long q = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes));
                long p = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes));
                n = p*q;
                long fi = (p - 1)*(q - 1);
                d = modInverse(e, fi);
            }

            public static long RSA_alg_encode( int data)
            {           
                return (long)Math.pow(data, e) % n;
            }

            public static long RSA_alg_decode( int data)
            {
                return (long)Math.pow(data, d) % n;
            }
            
    
    public static int getRandomIntegerBetweenRange(int min, int max)
    {
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    static long modInverse(long a, long m) 
    { 
        a = a % m; 
        for (long x = 1; x < m; x++) 
           if ((a * x) % m == 1) 
              return x; 
        return 1; 
    } 


}; 