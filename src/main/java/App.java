package main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {



    public static void main(String[] args) {

//        int p = RSA.generatePN();
//        int q = RSA.generatePN();
        Scanner input = new Scanner(System.in);
//        int p = Integer.parseInt(input.nextLine());
//        int q = Integer.parseInt(input.nextLine());
//        System.out.println(p);
//        System.out.println(q);
//        RSA.test(p,q,msg);
//        int n,z,publicKey,privateKey = 0;

//        String n,z;
//        BigInt one = new BigInt("1");
//        BigInt publicKey = new BigInt("2");
//        BigInt i = new BigInt("0");

        System.out.println("Podaj wiadomosc:");
        String msg = input.nextLine();
        BigInt bigmsg = new BigInt(msg);
        long n,z,publicKey,privateKey = 0;
        BigInt c;
//        long p = RSA.generatePN();
//        long q = RSA.generatePN();
        long p = PrimeNumbers.getPrimeNumber(200);
        long q = PrimeNumbers.getPrimeNumber(100);
//        int p = 7;
//        int q = 3;
        System.out.println(p);
        System.out.println(q);

        n = p*q;

        System.out.println("The value of n= " + n);
        //z to tocjent - ilosc liczb ktore sa wzglednie pierwsze z wartoscia n
        z = (p - 1) * (q - 1);
        System.out.println("the value of z= " + z);

        List<Long> listOfPublicKeys = new ArrayList<>();
        int length = 0;
        // 1 < e < n oraz e musi byc wzglednie pierwsza z tocjentem z
        for (publicKey = 2; publicKey < n; publicKey++) {
            // e jest to klucz publiczny
            if (RSA.gcd(publicKey, z) == 1) {
                listOfPublicKeys.add(publicKey);
                length ++;
            }
        }
        //losujemy randomowo liczbe e spelniajace wymogi
//        Random random = new Random();
//        int position = random.nextInt(length);
        publicKey = listOfPublicKeys.get(2);
        System.out.println("the value of public key = " + publicKey);


        //Wylicamy wartosc klucza prywatnego
        for (long i = 0; i <= n; i++) {
            long value = 1 + (i * z);
            // d is for private key exponent
            if (value % publicKey == 0) {
                privateKey = value / publicKey;
                break;
            }
        }
        System.out.println("the value of private Key = " + privateKey);

        BigInt bigpublickey = new BigInt(String.valueOf(publicKey));
        BigInt N = new BigInt(String.valueOf(n));
        //zimplementowac big intgera gdy za duza liczba
        c =new BigInt(bigmsg.pow(bigpublickey));
        System.out.println("Encrypted message is : " + c.getStringVal());
        c.mod(N);
        System.out.println("Encrypted message is : " + c.getStringVal());



        BigInt bigpriavtekey = new BigInt(String.valueOf(privateKey));
        // converting float value of c to BigInteger
        BigInt msgback = new BigInt(c.pow(bigpriavtekey));
        System.out.println(msgback.getStringVal());
        msgback.mod(N);
        System.out.println("Decrypted message is : " + msgback.getStringVal());





















//        BigInt p = new BigInt("13");
//        BigInt q = new BigInt("7");
//        BigInt p2 = new BigInt("13");
//
//        // n = p*q
//        n = p.multiply(q);
//        System.out.println("The value of n= " + n);
//
//        //        z = (p - 1) * (q - 1);
//        p2.subtract(one);
//        q.subtract(one);
//        z = p2.multiply(q);
//        System.out.println("the value of z= " + z);
//
//        BigInt bigIntZ = new BigInt(z);
//        BigInt bigIntN = new BigInt(n);
//        //Deklaruje liste kluczy publicznych
//        List<BigInt> listOfPublicKeys = new ArrayList<>();
//
//        int length = 0;
//
//
//
//
//        // 1 < e < n oraz e musi byc wzglednie pierwsza z tocjentem z
//        for (; publicKey.compareTo(bigIntN) == -1; publicKey.add(one)) {
//            // e jest to klucz publiczny
//            BigInt x = gcd(publicKey, bigIntZ);
//            if (x.compareTo(new BigInt("1")) == 1 )
//            {
//                listOfPublicKeys.add(publicKey);
//                System.out.println(publicKey.getStringVal());
//                length ++;
//            }
//        }
//        //losujemy randomowo liczbe e spelniajace wymogi
//        Random random = new Random();
//        int position = random.nextInt(length);
//
//
//        publicKey = listOfPublicKeys.get(position);
//        System.out.println("the value of public key = " + publicKey.getStringVal());


    }

    static BigInt gcd(BigInt publicKey, BigInt z)
    {
        if (publicKey.compareTo(new BigInt("0")) == 0) {
            return z;
        }
        else {
            return gcd(new BigInt(z.mod(publicKey)), publicKey);
        }

    }

}
