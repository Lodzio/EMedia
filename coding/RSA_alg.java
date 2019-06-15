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
                int tmp  =getRandomIntegerBetweenRange(0,sizePrimes - 1);
                 q = PrimeNumber.primes.get(tmp);
                 PrimeNumber.primes.remove(tmp);
              //   q = new BigInteger("10910616967349110231723734078614922645337060882141748968209834225138976011179993394299810159736904468554021708289824396553412180514827996444845438176099728");
                 p = PrimeNumber.primes.get(getRandomIntegerBetweenRange(0,sizePrimes - 2));
              //   p= new BigInteger("10933766183632575817611517034730668287155799984632223454138745671121273456287670008290843302875521274970245314593222946129064538358581018615539828479146468");
                n = p.multiply(q);
                BigInteger fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
                e = findE(fi);
                d = e.modInverse(fi);// modInverse(e, fi);
            }

            private static BigInteger findE(BigInteger fi){
                BigInteger bfi = fi;
                long[] possibleE = {3, 5, 17, 257, 65537};
                BigInteger result = new BigInteger("0");
                for (int i = 0; i < possibleE.length; i++){
                    BigInteger be = BigInteger.valueOf(possibleE[i]);
                    if (be.gcd(bfi).compareTo(BigInteger.ONE) == 0){
                        result = be;
                        return result;
                    }
                }
                return result;
            }

            public static BigInteger RSA_alg_encode( long data)
            {           
                BigInteger bData = BigInteger.valueOf(data);
                bData = bData.modPow(e,n);
                return bData;
            }

            public static long RSA_alg_decode( BigInteger data)
            {
                BigInteger bData = data;
                bData = bData.modPow(d,n);
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