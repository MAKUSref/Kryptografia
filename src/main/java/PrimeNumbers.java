package main;

public class PrimeNumbers {


    public static boolean is_prime(BigInt n){
        if (n.compareTo(new BigInt("2")) == 0 || n.compareTo(new BigInt("3")) == 0 ) {
            return true;
        }

        if (n.compareTo(new BigInt("1")) == 0 || n.compareTo(new BigInt("1")) == -1 ||
                n.mod(new BigInt("2")) == "0" || n.mod(new BigInt("3")) == "0") {
            return false;
        }
        BigInt i = new BigInt("5");
        BigInt six = new BigInt("6");
        BigInt i2 = new BigInt("5");
        i2.multiply(i);
        for (; new BigInt(i2.getStringVal()).compareTo(n) == -1 ||new BigInt(i2.getStringVal()).compareTo(n) == 0; i.add(six) ){
            if (n.mod(i) == "0" || n.mod(new BigInt(i.add(new BigInt("2")))) == "0") {
                return false;
            }
        }

        return true;
    }
}
