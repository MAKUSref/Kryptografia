import java.io.File;
import java.io.IOException;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class App {
    public static void main(String args[]) throws IOException {
        // p i q są to dwie duze liczby pierwsze

//        File f = new File("ok");
//        byte xd[] =Files.toByteArray(f);
        int p, q, n, z, privateKey = 0, publicKey, i;
        double c;
        BigInteger msgback;

        // The number to be encrypted and decrypted
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj wiadomosc:");
        int msg = Integer.parseInt(input.nextLine());
        // 1st prime number p
        //funkcja losujaca trzeba dac
        p = 7;
        // 2nd prime number q
        //funkcja losujaca trzeba dac
        q = 11;
        // n to wartosc mnozenia p i q
        n = p * q;
        System.out.println("The value of n= " + n);
        //z to tocjent - ilosc liczb ktore sa wzglednie pierwsze z wartoscia n
        z = (p - 1) * (q - 1);
        System.out.println("the value of z= " + z);
        //Deklaruje liste kluczy publicznych
        List<Integer> listOfPublicKeys = new ArrayList<>();
        int length = 0;
        // 1 < e < n oraz e musi byc wzglednie pierwsza z tocjentem z
        for (publicKey = 2; publicKey < z; publicKey++) {
            // e jest to klucz publiczny
            if (gcd(publicKey, z) == 1) {
                listOfPublicKeys.add(publicKey);
                length ++;
            }
        }
        //losujemy randomowo liczbe e spelniajace wymogi
        Random random = new Random();
        int position = random.nextInt(length);
        publicKey = listOfPublicKeys.get(position);
        System.out.println("the value of public key = " + publicKey);











        //Wylicamy wartosc klucza prywatnego
        for (i = 0; i <= n; i++) {
            int value = 1 + (i * z);
            // d is for private key exponent
            if (value % publicKey == 0) {
                privateKey = value / publicKey;
                break;
            }
        }
        System.out.println("the value of private Key = " + privateKey);





        //zimplementowac big intgera gdy za duza liczba
        c = (Math.pow(msg, publicKey)) % n;
        System.out.println("Encrypted message is : " + c);



        // converting int value of n to BigInteger
        BigInteger N = BigInteger.valueOf(n);

        // converting float value of c to BigInteger
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        msgback = (C.pow(privateKey)).mod(N);
        System.out.println("Decrypted message is : "
                + msgback);
    }

    static int gcd(int publicKey, int z)
    {
        if (publicKey == 0)
            return z;
        else
            return gcd(z % publicKey, publicKey);
    }
}
