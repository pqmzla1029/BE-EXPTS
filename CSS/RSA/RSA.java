//RSA
package CSS;

/**
 *
 * @author Igor
 */

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;
 
public class RSA
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int        bitlength = 1024;
    private SecureRandom     r;
 
    public RSA()
    {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        r = new SecureRandom();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
        
        currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }
 
    public RSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }
 
 
    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
 
    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        //System.out.println("p ->"+p);
        //System.out.println("q ->"+q);
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
    
    public static void main(String[] args)
    {
        RSA rsa = new RSA();
        Scanner sc=new Scanner(System.in);
        String string;
        System.out.println("Enter the plain text:");
        string = sc.nextLine();
        
        
        // encrypt
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        byte[] encrypted = rsa.encrypt(string.getBytes());
        currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        System.out.println("Encrypting String: " + bytesToString(encrypted));
        // decrypt
        currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted String: " + new String(decrypted));
        currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }
}