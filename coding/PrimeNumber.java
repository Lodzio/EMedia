package coding;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class PrimeNumber {
   static List<BigInteger> primes = new ArrayList<BigInteger>();

    public static void init(BigInteger startPrimeNumber) {
        if(MillerRabin(startPrimeNumber))
        {
        primes.add(startPrimeNumber);
       System.out.println("Liczba " + startPrimeNumber.toString() + "jest liczba pierwszą ");
        }
        else
        System.out.println("Liczba " + startPrimeNumber.toString() + " nie jest liczba pierwszą ");
    }

    public static boolean MillerRabin(BigInteger n, int precision) {
 
        if (n.compareTo(new BigInteger("341550071728321")) >= 0) {
            return n.isProbablePrime(precision);
        }
 
        int intN = n.intValue();
        if (intN == 1 || intN == 4 || intN == 6 || intN == 8) return false;
        if (intN == 2 || intN == 3 || intN == 5 || intN == 7) return true;
 
        int[] primesToTest = getPrimesToTest(n);
        if (n.equals(new BigInteger("3215031751"))) {
            return false;
        }
        BigInteger d = n.subtract(BigInteger.ONE);
        BigInteger s = BigInteger.ZERO;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.shiftRight(1);
            s = s.add(BigInteger.ONE);
        }
        for (int a : primesToTest) {
            if (try_composite(a, d, n, s)) {
                return false;
            }
        }
        return true;
    }

    private static int[] getPrimesToTest(BigInteger n) {
        if (n.compareTo(new BigInteger("3474749660383")) >= 0) {
            return new int[]{2, 3, 5, 7, 11, 13, 17};
        }
        if (n.compareTo(new BigInteger("2152302898747")) >= 0) {
            return new int[]{2, 3, 5, 7, 11, 13};
        }
        if (n.compareTo(new BigInteger("118670087467")) >= 0) {
            return new int[]{2, 3, 5, 7, 11};
        }
        if (n.compareTo(new BigInteger("25326001")) >= 0) {
            return new int[]{2, 3, 5, 7};
        }
        if (n.compareTo(new BigInteger("1373653")) >= 0) {
            return new int[]{2, 3, 5};
        }
        return new int[]{2, 3};
    }
 
 
    public static boolean MillerRabin(BigInteger n) {
        return MillerRabin(n, 100);
    }
    private static boolean try_composite(int a, BigInteger d, BigInteger n, BigInteger s) {
        BigInteger aB = BigInteger.valueOf(a);
        if (aB.modPow(d, n).equals(BigInteger.ONE)) {
            return false;
        }
        for (int i = 0; BigInteger.valueOf(i).compareTo(s) < 0; i++) {
            // if pow(a, 2**i * d, n) == n-1
            if (aB.modPow(BigInteger.valueOf(2).pow(i).multiply(d), n).equals(n.subtract(BigInteger.ONE))) {
                return false;
            }
        }
        return true;
    }

    void Sieve_of_Atkin(BigInteger startPrimeNumber, BigInteger lastPrimeNumber){
                HashSet<BigInteger> sieve = new HashSet<BigInteger>(); 
                for (BigInteger x=BigInteger.ONE; x.multiply(x).compareTo(lastPrimeNumber) <0; x = x.add(BigInteger.ONE)) { 
                    for (BigInteger y = BigInteger.ONE;y.multiply(y).compareTo(lastPrimeNumber) <0; y = y.add(BigInteger.ONE)) { 
          
                        BigInteger n = x.multiply(BigInteger.valueOf(3)).multiply(x).subtract(y.multiply(y));  
                        if(n.compareTo(startPrimeNumber) > 0)
                        if (x.compareTo(y) > 0 && n.compareTo(lastPrimeNumber) <= 0 &&  (n.mod(BigInteger.valueOf(12)).compareTo(BigInteger.valueOf(11)) == 0)) 
                            sieve.add(n);
                           
    
                        n =x.multiply(BigInteger.valueOf(4)).multiply(x).add(y.multiply(y));
                        if(n.compareTo(startPrimeNumber) > 0) 
                        if (n.compareTo(lastPrimeNumber) <= 0 && (n.mod(BigInteger.valueOf(12)).compareTo(BigInteger.ONE) ==0 || n.mod(BigInteger.valueOf(12)).compareTo(BigInteger.valueOf(5)) == 0)) 
                            sieve.add(n); 
          
                        n = x.multiply(BigInteger.valueOf(3)).multiply(x).add(y.multiply(y));
                        if(n.compareTo(startPrimeNumber) > 0)  
                        if (n.compareTo(lastPrimeNumber) <= 0 &&  (n.mod(BigInteger.valueOf(12)).compareTo(BigInteger.valueOf(7))) ==0 ) 
                            sieve.add(n); 
          
    
                    } 
                } 
          
                // Mark all multiples of squares as 
                // non-prime 
                for (BigInteger r = BigInteger.valueOf(5); r.multiply(r).compareTo(lastPrimeNumber) < 0; r = r.add(BigInteger.ONE)) { 
                    if (sieve.contains(r)) { 
                        for (BigInteger i = r.multiply(r); i.compareTo(lastPrimeNumber) < 0; 
                             i = i.add(r.multiply(r))) 
                             sieve.remove(i);  
                    } 
                } 
                System.out.println(""); 
                System.out.println("Liczby Pierwsze z zakresu "+startPrimeNumber+":"+lastPrimeNumber); 
                // Print primes using sieve[] 
                for (BigInteger a = BigInteger.valueOf(5); a.compareTo(lastPrimeNumber) < 0; a= a.add(BigInteger.ONE)) 
                    if (sieve.contains(a) && a.compareTo(startPrimeNumber) > 0) 
                    {
                        primes.add(a);
                       // System.out.print(a + " "); 
                    }
                    System.out.println(" ");
            }

            public static void mod_alg(BigInteger startPrimeNumber, BigInteger lastPrimeNumber)
            {
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
                        for(BigInteger prime : primes)
                        {
                            System.out.println(prime);
                        }
                    }
                }
