package coding;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class PrimeNumber {
   static List<BigInteger> primes = new ArrayList<BigInteger>();

    public static void init(BigInteger startPrimeNumber, BigInteger lastPrimeNumber) {
        if(MillerRabin(startPrimeNumber,lastPrimeNumber))
        {
        primes.add(startPrimeNumber);
       System.out.println("Liczba " + startPrimeNumber.toString() + "jest liczba pierwszą ");
        }
        else
        System.out.println("Liczba " + startPrimeNumber.toString() + " nie jest liczba pierwszą ");
    }

    public static boolean MillerRabin(BigInteger n, BigInteger lastPrimeNumber)
    {
        int iteration = n.subtract(lastPrimeNumber).intValue();
        /** base case **/
        if (n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0)
            return false;
        /** base case - 2 is prime **/
        if (n.compareTo(BigInteger.valueOf(2)) == 0)
            return true;
        /** an even number other than 2 is composite **/
        if (n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0)
            return false;
 
        BigInteger s = n.subtract(BigInteger.ONE);
        while (s.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0)
            s = s.divide(BigInteger.valueOf(2));
 
        Random rand = new Random();
        for (int i = 0; i < iteration; i++)
        {
            BigInteger r = BigInteger.valueOf(rand.nextLong()).abs();            
            BigInteger a = r.mod(n.subtract(BigInteger.ONE)).add(BigInteger.ONE), temp = s;
            BigInteger mod = modPow(a, temp, n);
            while (temp.compareTo(n.subtract(BigInteger.ONE)) != 0 && mod.compareTo(BigInteger.ONE) != 0 && mod.compareTo(n.subtract(BigInteger.ONE)) != 0)
            {
                mod = mulMod(mod, mod, n);
                temp = temp.multiply(BigInteger.valueOf(2));
            }
            if (mod.compareTo(n.subtract(BigInteger.ONE)) != 0 && temp.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0)
                return false;
        }
        return true;        
    }
    /** Function to calculate (a ^ b) % c **/
    public static BigInteger modPow(BigInteger a, BigInteger b, BigInteger c)
    {
        BigInteger res = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(b)<0; i.add(BigInteger.ONE))
        {
            res = res.multiply(a);
            res = res.mod(c); 
        }
        return res.mod(c);
    }
    /** Function to calculate (a * b) % c **/
    public static BigInteger mulMod(BigInteger a, BigInteger b, BigInteger mod) 
    {
        return a.multiply(b).mod(mod);
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
