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

  public String add(BigInt o) {
    String str1 = this.getStringVal();
    String str2 = o.getStringVal();

    if (str1.length() > str2.length()){
      String t = str1;
      str1 = str2;
      str2 = t;
    }

    // Take an empty String for storing result
    String str = "";
 
    // Calculate length of both String
    int n1 = str1.length(), n2 = str2.length();
 
    // Reverse both of Strings
    str1=new StringBuilder(str1).reverse().toString();
    str2=new StringBuilder(str2).reverse().toString();
 
    int carry = 0;
    for (int i = 0; i < n1; i++)
    {
      // Do school mathematics, compute sum of
      // current digits and carry
      int sum = ((int)(str1.charAt(i) - '0') +
                  (int)(str2.charAt(i) - '0') + carry);
      str += (char)(sum % 10 + '0');

      // Calculate carry for next step
      carry = sum / 10;
    }
 
    // Add remaining digits of larger number
    for (int i = n1; i < n2; i++)
    {
      int sum = ((int)(str2.charAt(i) - '0') + carry);
      str += (char)(sum % 10 + '0');
      carry = sum / 10;
    }
 
    // Add remaining carry
    if (carry > 0)
      str += (char)(carry + '0');
 
    // reverse resultant String
    this.bigNumber = BigInt.reverseChars(str.toCharArray());
 
    return this.getStringVal();
  }

  public String subtract(BigInt o) {
    String str1 = this.getStringVal();
    String str2 = o.getStringVal();

    if (this.compareTo(o) == -1) {
      String t = str1;
      str1 = str2;
      str2 = t;
    }

    String str = "";
    int n1 = str1.length(), n2 = str2.length();

    str1 = new StringBuilder(str1).reverse().toString();
    str2 = new StringBuilder(str2).reverse().toString();

    int carry = 0;

    for (int i = 0; i < n2; i++) {
      int sub = ((int)(str1.charAt(i) - '0') - (int)(str2.charAt(i) - '0') - carry);

      if (sub < 0) {
        sub = sub + 10;
        carry = 1;
      }
      else
        carry = 0;

      str += (char)(sub + '0');
    }

    // subtract remaining digits of larger number
    for (int i = n2; i < n1; i++) {
      int sub = ((int)(str1.charAt(i) - '0') - carry);

      // if the sub value is -ve, then make it
      // positive
      if (sub < 0) {
        sub = sub + 10;
        carry = 1;
      }
      else
        carry = 0;

      str += (char)(sub + '0');
    }

    char[] reverseChars = BigInt.reverseChars(str.toCharArray());
    String correctNum = this.deleteZeros(String.valueOf(reverseChars));
    this.bigNumber = correctNum.toCharArray();
    return this.getStringVal();
  }

  public String multiply(BigInt o) {
    String num1 = this.getStringVal();
    String num2 = o.getStringVal();

    int len1 = num1.length();
    int len2 = num2.length();

    if (len1 == 0 || len2 == 0)
      return "0";

    int result[] = new int[len1 + len2];

    int i_n1 = 0;
    int i_n2 = 0;

    for (int i = len1 - 1; i >= 0; i--) {
      int carry = 0;
      int n1 = num1.charAt(i) - '0';

      i_n2 = 0;

      for (int j = len2 - 1; j >= 0; j--) {
        int n2 = num2.charAt(j) - '0';
        int sum = n1 * n2 + result[i_n1 + i_n2] + carry;

        carry = sum / 10;

        result[i_n1 + i_n2] = sum % 10;

        i_n2++;
      }

      if (carry > 0)
        result[i_n1 + i_n2] += carry;

      i_n1++;
    }

    int i = result.length - 1;
    while (i >= 0 && result[i] == 0)
      i--;

    if (i == -1)
      return "0";

    String s = "";

    while (i >= 0)
      s += (result[i--]);

    this.bigNumber = s.toCharArray();
    return s;
  }

  public String pow(BigInt pow) {
    String num1 = this.getStringVal();
    String num2 = pow.getStringVal();

    BigInt newVal = new BigInt(num1);

    BigInt i = new BigInt("1");
    BigInt step = new BigInt("1");

    for (; pow.compareTo(i) > 0; i.add(step)) {
      newVal.multiply(this);
    }

    this.bigNumber = newVal.getValue();
    return this.getStringVal();
  }

  public String mod(BigInt o) {
    if (this.compareTo(o) == -1) {
      return this.getStringVal();
    } else if (this.compareTo(o) == 0) {
      this.setValue("0");
      return this.getStringVal();
    }

    BigInt copy = new BigInt(this.getStringVal());

    while (copy.compareTo(o) >= 0) {
      copy.subtract(o);
    }

    this.bigNumber = copy.getValue();
    return this.getStringVal();
  }

  private String deleteZeros(String num) {
    String newVal = "";
    boolean zeros = true;
    for (int i = 0; i < num.length(); i++) {
      if (num.charAt(i) != '0') {
        zeros = false;
      }

      if (!zeros) {
        newVal += num.charAt(i);
      } else if (zeros && i == num.length() - 1) {
        newVal = "0";
      }
    }

    return newVal;
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
