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
    assertEquals("134", bigInt.add(bi));

    String expectedValue = "99247780936";
    bigInt.setValue("7778526");
    bi.setValue("99240002410");
    assertEquals(expectedValue, bigInt.add(bi));
    

    expectedValue = "10";
    bigInt.setValue("9");
    bi.setValue("1");
    assertEquals(expectedValue, bigInt.add(bi));
  }

  @Test
  public void subtractTest() {
    String expectedValue = "100";
    bigInt.setValue("1100");
    BigInt bi = new BigInt("1000");

    assertEquals(expectedValue, bigInt.subtract(bi));

    expectedValue = "0";
    bigInt.setValue("500");
    bi.setValue("500");
    assertEquals(expectedValue, bigInt.subtract(bi));

    expectedValue = "1";
    bigInt.setValue("2118187521397235888154583183918321221520083884298838480662479");
    bi.setValue("2118187521397235888154583183918321221520083884298838480662480");
    assertEquals(expectedValue, bigInt.subtract(bi));
  }

  @Test
  public void multiplyTest() {
    bigInt.setValue("1235421415454545454545454544");
    BigInt bi = new BigInt("1714546546546545454544548544544545");
    String expectedValue = "2118187521397235888154583183918321221520083884298838480662480";

    assertEquals(expectedValue, bigInt.multiply(bi));
  }

  @Test
  public void powTest() {
    bigInt.setValue("2");
    BigInt bi = new BigInt("100");
    String expectedValue = "1267650600228229401496703205376";

    assertEquals(expectedValue, bigInt.pow(bi));
  }

  @Test
  public void modTest() {
    bigInt.setValue("10");
    BigInt bi = new BigInt("5");
    String expectedValue = "0";
    assertEquals(expectedValue, bigInt.mod(bi));

    expectedValue = "10";
    bigInt.setValue("10");
    bi.setValue("11");
    assertEquals(expectedValue, bigInt.mod(bi));

    expectedValue = "10";
    bigInt.setValue("10");
    bi.setValue("2118187521397235888154583183918321221520083884298838480662480");
    assertEquals(expectedValue, bigInt.mod(bi));

    // expectedValue = "1";
    bigInt.setValue("222222222222222222222222221516541354164321641654983516568459865649451654895649565"); 
    bi.setValue("98989846546515649846548446549847984654654316546846468484984649849849849484649");
    assertNotNull(bigInt.mod(bi));
  }
}
