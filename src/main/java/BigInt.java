import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class BigInt implements Comparable<BigInt> {
  private char[] bigNumber;

  public BigInt(String val) {
    this.bigNumber = val.toCharArray();
  }

  // static
  public static char[] reverseChars(char[] chars) {
    List<Character> nums = BigInt.stringToCharList(String.valueOf(chars));
    char[] reversedArr = new char[chars.length];
    Collections.reverse(nums);

    for (int i = 0; i < chars.length; i++) {
      reversedArr[i] = nums.get(i);
    }

    return reversedArr;
  }

  public static List<Character> stringToCharList(String str) {
    List<Character> chars = new ArrayList<>();
    for (char ch : str.toCharArray()) {
      chars.add(ch);
    }

    return chars;
  }

  public static char[] listToCharsArr(List<Character> chars) {
    char[] arr = new char[chars.size()];

    for (int i = 0; i < chars.size(); i++) {
      arr[i] = chars.get(i);
    }

    return arr;
  }

  // public
  public char[] getValue() {
    return bigNumber;
  }

  public String getStringVal() {
    return String.valueOf(this.bigNumber);
  }

  public int length() {
    return this.bigNumber.length;
  }

  public void setValue(String num) {
    this.bigNumber = num.toCharArray();
  } 

  public void add(BigInt o) {
    List<Character> sum = new ArrayList<>();
    
    int buffor = 0;

    int i = this.length() - 1;
    int j = o.length() - 1;
    for (int k = 0; i >= 0 || j >= 0; i--, j--, k++) {
      char firstCharNumber = i >= 0 ? this.bigNumber[i] : '0';
      char secCharNumber = j >= 0 ? o.bigNumber[j] : '0';

      int firstIntNumber = Character.getNumericValue(firstCharNumber);
      int secIntNumber = Character.getNumericValue(secCharNumber); 

      int sumResult = firstIntNumber + secIntNumber + buffor;
      buffor = sumResult / 10;
      char sumResultChar = Character.forDigit(sumResult % 10, 10);
      sum.add(sumResultChar);
    }

    Collections.reverse(sum);
    this.bigNumber = BigInt.listToCharsArr(sum);
  }

  @Override
  public int compareTo(BigInt o) {
    int firstLen = bigNumber.length;
    int secLen = o.getValue().length;
    
    // sprawdzanie długości tablic
    if (firstLen > secLen) {
      return 1;
    } else if (firstLen < secLen) {
      return -1;
    }

    // gdy długości są takie same
    for (int i = 0; i < firstLen; i++) {
      int firstInt = bigNumber[i];
      int secInt = o.getValue()[i];

      if (firstInt > secInt) {
        return 1;
      } else if (firstInt < secInt) {
        return -1;
      }
    }
    
    return 0;
  }
}
