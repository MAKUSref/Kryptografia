package main;

import java.math.BigInteger;

public class BigInt implements Comparable<BigInt> {
    private int[] mag;

    public BigInt(String val) {
        final int radix = 10;
        final int len = val.length();
        int cursor = 0;

        if (len == 0) {
            throw new NumberFormatException("Zero length BigInteger");
        }

        while (cursor < len && Character.digit(val.charAt(cursor), radix) == 0) {
            cursor++;
        }

        int[] magnitude = new int[len - cursor];
        int j = 0;
        for (int i = cursor; i < len; i++) {
            magnitude[j] = (int) (val.charAt(i) - '0');
            j++;
        }

        if (cursor == len) {
            int[] zeroMag = {0};
            this.mag = zeroMag;
            return;
        }


        this.mag = magnitude;
    }

    public static BigInt gcd(BigInt a, BigInt b) {
        BigInt aCopy = new BigInt(a.get());
        BigInt bCopy = new BigInt(b.get());
        BigInt zero = new BigInt("0");

        if (aCopy.compareTo(zero) == 0) {
            return bCopy;
        } else {
            return gcd(bCopy.mod(aCopy), aCopy);
        }
    }

    public BigInt add(String val) {
        return this.add(new BigInt(val));
    }

    public BigInt add(BigInt val) {
        BigInteger bi = new BigInteger(this.get());
        BigInteger add = new BigInteger(val.get());

        BigInt res = new BigInt(bi.add(add).toString());
        return res;
    }

    public BigInt diff(String val) {
        return this.diff(new BigInt(val));
    }

    public BigInt diff(BigInt val) {
        String str1 = this.get();
        String str2 = val.get();

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

        for (int i = n2; i < n1; i++) {
            int sub = ((int) (str1.charAt(i) - '0') - carry);

            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            } else
                carry = 0;

            str += (char) (sub + '0');
        }

        String result = new StringBuilder(str).reverse().toString();
        return new BigInt(result);
    }

    public BigInt multiply(String val) {
        return multiply(new BigInt(val));
    }

    public BigInt multiply(BigInt o) {
        final int len1 = this.length();
        final int len2 = o.length();

        int[] tempResult = new int[len1 + len2];

        int i_n1 = 0;
        int i_n2 = 0;

        for (int i = len1 - 1; i >= 0; i--) {
            int carry = 0; // 0 - 8
            int n1 = this.getBytes()[i]; // 0 - 9

            i_n2 = 0;

            for (int j = len2 - 1; j >= 0; j--) {
                int n2 = o.getBytes()[j]; // 0 - 9
                int sum = n1 * n2 + tempResult[i_n1 + i_n2] + carry;
                carry = sum / 10;
                tempResult[i_n1 + i_n2] = sum % 10;

                i_n2++;
            }

            if (carry > 0) {
                tempResult[i_n1 + i_n2] += carry;
            }

            i_n1++;
        }

        int i = tempResult.length - 1;
        while (i >= 0 && tempResult[i] == 0) {
            i--;
        }

        if (i == -1) {
            this.set("0");
        }

        int[] result = new int[i + 1];
        int j = 0;
        while (i >= 0) {
            result[j] = tempResult[i--];
            j++;
        }

        // this.set(result);
        return new BigInt(this.intArr2String(result));
    }

    public BigInt moduloPower(BigInt power, BigInt modulo) {
        BigInteger pow = new BigInteger(power.get());
        String binPow = pow.toString(2);

        BigInt currentValue = new BigInt(this.get());
        BigInteger tempMod = new BigInteger(currentValue.get());
        BigInteger biModulo = new BigInteger(modulo.get()); // n
        BigInteger currentMod = tempMod.mod(biModulo);
        currentValue.set(currentMod.toString());

        for (int i = 1; i < binPow.length(); i++) {
            char currentChar = binPow.charAt(i);

            // v = v * v
            currentValue = currentValue.multiply(currentValue);

            // m = v mod n
            tempMod = new BigInteger(currentValue.get());
            currentMod = tempMod.mod(biModulo);

            // v = m
            currentValue.set(currentMod.toString());

            if (currentChar == '1') {
                // v = v * x
                currentValue = currentValue.multiply(this);

                // m = v mod n
                tempMod = new BigInteger(currentValue.get());
                currentMod = tempMod.mod(biModulo);

                // v = m
                currentValue.set(currentMod.toString());
            }

        }

        return new BigInt(currentMod.toString());
    }

    public BigInt divide(String val) {
        return this.divide(new BigInt(val));
    }

    public BigInt divide(BigInt val) {
        BigInteger bi = new BigInteger(this.get());
        BigInteger divider = new BigInteger(val.get());

        BigInt res = new BigInt(bi.divide(divider).toString());
        return res;
    }

    public BigInt mod(String val) {
        return this.mod(new BigInt(val));
    }

    public BigInt mod(BigInt val) {
        BigInteger bi = new BigInteger(this.get());
        BigInteger mod = new BigInteger(val.get());

        BigInt res = new BigInt(bi.mod(mod).toString());
        return res;
    }

    public int length() {
        return mag.length;
    }

    public String get() {
        return this.intArr2String(this.getBytes());
    }

    public int[] getBytes() {
        return this.mag;
    }

    public void set(String val) {
        BigInt bi = new BigInt(val);
        this.mag = bi.getBytes();
    }

    public void set(int[] val) {
        // powinny być checki
        this.mag = val;
    }

    @Override
    public int compareTo(BigInt o) {
        int firstLen = this.length();
        int secLen = o.length();

        // sprawdzanie długości tablic
        if (firstLen > secLen) {
            return 1;
        } else if (firstLen < secLen) {
            return -1;
        }

        // gdy długości są takie same
        for (int i = 0; i < firstLen; i++) {
            int firstInt = this.getBytes()[i];
            int secInt = o.getBytes()[i];

            if (firstInt > secInt) {
                return 1;
            } else if (firstInt < secInt) {
                return -1;
            }
        }

        return 0;
    }

    @Override
    public String toString() {
        return this.get();
    }

    private String intArr2String(int[] val) {
        String res = "";
        for (int i = 0; i < val.length; i++) {
            res += val[i];
        }

        return res;
    }
}