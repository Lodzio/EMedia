package coding;

import java.math.BigInteger;

public class RSA_alg{
    
            // https://www.di-mgt.com.au/rsa_alg.html#note4
            static int sizePrimes;
            public static BigInteger e;
            public static BigInteger d;
            public static BigInteger n;
            public static BigInteger p;
            public static BigInteger q;

            public static void init()
            {
                sizePrimes = PrimeNumber.primes.size();
                 q = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes - 1));
                 p = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes - 1));
                n = p.multiply(q);
                BigInteger fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
                e = findE(fi);
                d = modInverse(e, fi);
            }

            private static BigInteger findE(BigInteger fi){
                BigInteger bfi = fi;
                long[] possibleE = {3, 5, 17, 257, 65537};
                BigInteger result = new BigInteger("0");
                for (int i = 0; i < possibleE.length; i++){
                    BigInteger be = BigInteger.valueOf(possibleE[i]);
                    if (be.gcd(bfi).equals(BigInteger.ONE)){
                        result = be;
                        return result;
                    }
                }
                return result;
            }

            public static BigInteger RSA_alg_encode( long data)
            {           
                BigInteger bData = BigInteger.valueOf(data);
                bData = bData.pow(e.intValue()).mod(n);
                return bData;
            }

            public static long RSA_alg_decode( BigInteger data)
            {
                BigInteger bData = data;
                bData = bData.pow(d.intValue()).mod(n);
                return bData.longValue();
            }
            
    
    public static int getRandomIntegerBetweenRange(int min, int max)
    {
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    static BigInteger modInverse(BigInteger a, BigInteger m) 
    { 
        a = a.mod(m); 
        for (BigInteger x = BigInteger.ONE; x.compareTo(m) < 0;x= x.add(BigInteger.ONE)) 
           if ((a.multiply(x)).mod(m).compareTo(BigInteger.ONE) == 0) 
              return x; 
        return BigInteger.ONE; 
    } 


}; 