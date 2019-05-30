package RSA_alg;

import java.math.BigInteger;

public class RSA_alg{
    
            // https://www.di-mgt.com.au/rsa_alg.html#note4
            static int sizePrimes;
            public static long e;
            public static long d;
            public static long n;
            public static long p;
            public static long q;

            public static void init()
            {
                sizePrimes = PrimeNumber.primes.size();
                 q = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes - 1));
                 p = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes - 1));
                n = p*q;
                long fi = (p - 1)*(q - 1);
                e = findE(fi);
                d = modInverse(e, fi);
            }

            private static long findE(long fi){
                BigInteger bfi = BigInteger.valueOf(fi);
                long[] possibleE = {3, 5, 17, 257, 65537};
                long result = 0;
                for (int i = 0; i < possibleE.length; i++){
                    BigInteger be = BigInteger.valueOf(possibleE[i]);
                    if (be.gcd(bfi).equals(BigInteger.ONE)){
                        result = be.intValue();
                        return result;
                    }
                }
                return result;
            }

            public static long RSA_alg_encode( long data)
            {           
                BigInteger bData = BigInteger.valueOf(data);
                bData = bData.pow((int)e).mod(BigInteger.valueOf(n));
                return bData.intValue();
            }

            public static long RSA_alg_decode( long data)
            {
                BigInteger bData = BigInteger.valueOf(data);
                bData = bData.pow((int)d).mod(BigInteger.valueOf(n));
                return bData.intValue();
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