package Test;

import org.junit.jupiter.api.Test;

import main.RSA;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {
    @Test
    public void constructorTest() {
        String msg = "Hello there!";
        RSA rsa = new RSA(msg);
        rsa.encode();
        rsa.decode();

        assertEquals(msg, rsa.getDecodedMsg());

        RSA rsa2 = new RSA("dd");
    }

    @Test
    public void byteArrayTest() {
        byte[] bytes = {100, 80, 11, 22, 36, 66, 101};
    }
}
