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

  public static String divideByInt(String number, int divider) {
    StringBuilder result = new StringBuilder();

    char[] dividend = number.toCharArray();

    int carry = 0;

    for (int i = 0; i < dividend.length; i++) {
      int x = carry * 10 + Character.getNumericValue(dividend[i]);
      result.append(x / divider);
      carry = x % divider;
    }

    for (int i = 0; i < result.length(); i++) {
      if (result.charAt(i) != '0') {
        return result.substring(i);
      }
    }

    return "";
  }

  public static String multiply(String num1, String num2) {
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

    return s;
  }
  

  // public static BigInt generatePrime(int pow, int accuracy) {
  // // 1. wylosować nieparzystą liczbę pierwszą
  // Bigint bi = new BigInt("2");
  // bi.pow(pow);
  // return;
  // }

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

  public String add(String str2) {
    String str1 = this.getStringVal();

    if (str1.length() > str2.length()) {
      String t = str1;
      str1 = str2;
      str2 = t;
    }

    // Take an empty String for storing result
    String str = "";

    // Calculate length of both String
    int n1 = str1.length(), n2 = str2.length();

    // Reverse both of Strings
    str1 = new StringBuilder(str1).reverse().toString();
    str2 = new StringBuilder(str2).reverse().toString();

    int carry = 0;
    for (int i = 0; i < n1; i++) {
      // Do school mathematics, compute sum of
      // current digits and carry
      int sum = ((int) (str1.charAt(i) - '0') +
          (int) (str2.charAt(i) - '0') + carry);
      str += (char) (sum % 10 + '0');

      // Calculate carry for next step
      carry = sum / 10;
    }

    // Add remaining digits of larger number
    for (int i = n1; i < n2; i++) {
      int sum = ((int) (str2.charAt(i) - '0') + carry);
      str += (char) (sum % 10 + '0');
      carry = sum / 10;
    }

    // Add remaining carry
    if (carry > 0)
      str += (char) (carry + '0');

    // reverse resultant String
    this.bigNumber = BigInt.reverseChars(str.toCharArray());

    return this.getStringVal();
  }

  public String add(BigInt o) {
    return this.add(o.getStringVal());
  }

  public String subtract(String str2) {
    String str1 = this.getStringVal();

    if (this.compareTo(new BigInt(str2)) == -1) {
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
      int sub = ((int) (str1.charAt(i) - '0') - (int) (str2.charAt(i) - '0') - carry);

      if (sub < 0) {
        sub = sub + 10;
        carry = 1;
      } else
        carry = 0;

      str += (char) (sub + '0');
    }

    // subtract remaining digits of larger number
    for (int i = n2; i < n1; i++) {
      int sub = ((int) (str1.charAt(i) - '0') - carry);

      // if the sub value is -ve, then make it
      // positive
      if (sub < 0) {
        sub = sub + 10;
        carry = 1;
      } else
        carry = 0;

      str += (char) (sub + '0');
    }

    char[] reverseChars = BigInt.reverseChars(str.toCharArray());
    String correctNum = this.deleteZeros(String.valueOf(reverseChars));
    this.bigNumber = correctNum.toCharArray();
    return this.getStringVal();
  }

  public String subtract(BigInt o) {
    return this.subtract(o.getStringVal());
  }

  public String multiply(String num2) {
    String num1 = this.getStringVal();

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

  public String multiply(BigInt o) {
    return this.multiply(o.getStringVal());
  }

  public String pow(String num2) {
    String num1 = this.getStringVal();

    BigInt newVal = new BigInt(num1);

    BigInt pow = new BigInt(num2);
    BigInt i = new BigInt("1");

    for (; pow.compareTo(i) > 0; i.add("1")) {
      newVal.multiply(this);
    }

    this.bigNumber = newVal.getValue();
    return this.getStringVal();
  }

  public String pow(BigInt o) {
    return this.pow(o.getStringVal());
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

  public String mod(String str) {
    return this.mod(new BigInt(str));
  }

  public String divide(String b) {
    // a / b = c
    // c = [0; a]
    // g = a / 2 -> g +-= g / 2
    // czy b * g = a
    // jeżeli tak to return g = c

    BigInt bigIntB = new BigInt(b);

    if (this.compareTo(bigIntB) == -1) {
      this.bigNumber = "0".toCharArray();
      return this.getStringVal();
    } else if (bigIntB.compareTo(new BigInt(BigInt.divideByInt(this.getStringVal(), 2))) == 1) {
      this.bigNumber = "1".toCharArray();
      return this.getStringVal();
    }

    String aHalf = BigInt.divideByInt(this.getStringVal(), 2);
    BigInt g = new BigInt(aHalf);
    String step = BigInt.divideByInt(aHalf, 2);

    String multiplyResult = BigInt.multiply(g.getStringVal(), b);
    BigInt multiplyBigInt = new BigInt(multiplyResult);
    int comperator = this.compareTo(multiplyBigInt);

    // while (comperator != 0) {
    //   System.out.println("C - " + comperator);

    //   if (comperator == -1) {
    //     g.subtract(step);
    //   } else if (comperator == 1) {
    //     g.add(step);
    //   }

    //   step = BigInt.divideByInt(step, 2);
    //   multiplyResult = BigInt.multiply(g.getStringVal(), b);
    //   multiplyBigInt.setValue(multiplyResult);
    //   comperator = this.compareTo(multiplyBigInt);
    // }

    this.bigNumber = g.getValue();
    return this.getStringVal();

    // BigInt g = new BigInt(BigInt.divideByInt(this.getStringVal(), 2));
    // String step = BigInt.divideByInt(g.getStringVal(), 2);
    
    // int comp = this.compareTo(new BigInt(BigInt.multiply(g.getStringVal(), b)));
    // for (; comp != 0; comp = this.compareTo(new BigInt(BigInt.multiply(g.getStringVal(), b)))) {
      
    //   System.out.println("step - " + step);
    //   if (step == "0") {
    //     break;
    //   }

    //   if (comp == -1) {
    //     g.subtract(step);
    //   } else if (comp == 1) {
    //     g.add(step);
    //   }

    //   step = BigInt.divideByInt(step, 2);
    // }

    // this.bigNumber = g.getValue();
    // return this.getStringVal();
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
