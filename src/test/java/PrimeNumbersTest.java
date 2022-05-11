import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class PrimeNumbersTest {
  @Test
  public void getPrimeNumberTest() {
    assertEquals(7, PrimeNumbers.getPrimeNumber(10));
    assertEquals(9973, PrimeNumbers.getPrimeNumber(10000));
  }
}
