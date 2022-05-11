package Test;

import main.BigInt;
import main.PrimeNumbers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class PrimeNumbersTest {
    @Test
    public void checkPrimeNumberTest() {
        BigInt x = new BigInt("7");
        assertTrue(PrimeNumbers.is_prime(x));
    }
}
