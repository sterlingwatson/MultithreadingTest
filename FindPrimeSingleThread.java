import java.util.concurrent.TimeUnit;
import java.math.BigInteger;

public class FindPrimeSingleThread {
    
    public static boolean isPrime(BigInteger start, BigInteger end) {
        for (BigInteger n = start; (n.compareTo(end)<= 0); n = n.add(BigInteger.ONE)) {
            BigInteger sqrt_n = n.sqrt();
            for (BigInteger i = BigInteger.TWO;  (sqrt_n.compareTo(i) >= 1); i = i.add(BigInteger.ONE)) {
                if (n.mod(i).equals(0)) {
                    return false;
                }            
            }
        }   
        return true;
    }
        
    public static void main(String[] args) {
        String input = "2135729";
        BigInteger two = BigInteger.TWO;
        BigInteger n = new BigInteger(input);

        boolean result = false;
        
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            
            result = isPrime(two, n);
            
            if (result) {
                System.out.println(n + " is a prime number");
            } else {
                System.out.println(n + " is not a prime number");
            }
            
            double timeTaken = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
            System.out.printf("Time: %.2f seconds\n", timeTaken);
        }
    }
}

