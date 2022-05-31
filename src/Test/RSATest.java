package Test;

import main.BigInt;
import org.junit.jupiter.api.Test;

import main.RSA;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {
    @Test
    public void constructorTest() {
        BigInt bi = new BigInt("12345648135164351646");
        RSA rsa = new RSA(bi);

        System.out.println("Pub: " + rsa.getPublicKey().get());
        System.out.println("Priv: " + rsa.getPrivateKey().get());

        rsa.encode();
        rsa.decode();

        System.out.println("N: " + rsa.getN().get());
        System.out.println("En: " + rsa.getEncryptedNumber().get());
        System.out.println("De: " + rsa.getDecryptedNumber().get());

        assertEquals(bi.get(), rsa.getDecryptedNumber().get());
    }
}
