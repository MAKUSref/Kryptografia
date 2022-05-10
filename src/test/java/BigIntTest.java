import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class BigIntTest {
  BigInt bigInt;
  String val1 = "123456";

  @BeforeEach
  public void setUp() {
    try {
      bigInt = new BigInt(val1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void constructorTest() {
    try {
      BigInt bigInt = new BigInt("111b");
    } catch (Exception e) {
      assertEquals("Wrong number!", e.getMessage());
    }
  }

  @Test
  public void comparableTest() {
    BigInt bigInt2;
    String val2 = "123436";
    String val3 = "123496";
    String val4 = "123456";
    String val5 = "11";
    String val6 = "111111111";

    try {
      bigInt2 = new BigInt(val2);
      assertEquals(1, bigInt.compareTo(bigInt2));

      bigInt2.setValue(val3);
      assertEquals(-1, bigInt.compareTo(bigInt2));

      bigInt2.setValue(val4);
      assertEquals(0, bigInt.compareTo(bigInt2));

      bigInt2.setValue(val5);
      assertEquals(1, bigInt.compareTo(bigInt2));

      bigInt2.setValue(val6);
      assertEquals(-1, bigInt.compareTo(bigInt2));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void stringToCharListTest() {
    List<Character> chars = BigInt.stringToCharList("123");
    assertEquals('1', chars.get(0));
  }

  @Test
  public void listToCharsArrTest() {
    List<Character> charsList = BigInt.stringToCharList("123");
    char[] chars = BigInt.listToCharsArr(charsList);

    assertEquals(charsList.get(0), chars[0]);
    assertEquals(charsList.size(), chars.length);
  }

  @Test
  public void reverseArrTest() {
    char[] chars = BigInt.reverseChars("123".toCharArray());
    assertEquals("321", String.valueOf(chars));
  }

  @Test
  public void addTest() {
    bigInt.setValue("123");
    BigInt bi = new BigInt("11");
    bigInt.add(bi);
    assertEquals("134", bigInt.getStringVal());

    String expectedValue = "99247780936";
    bigInt.setValue("7778526");
    bi.setValue("99240002410");
    bigInt.add(bi);
    assertEquals(expectedValue, bigInt.getStringVal());
  }
}
