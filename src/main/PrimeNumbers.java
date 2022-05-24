package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeNumbers {
    public static long getPrimeNumber(long limit) {
        long num = limit;
        while (!PrimeNumbers.isPrime(num)) {
            num--;
        }

        return num;
    }

    public static boolean isPrime(long num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}

//public class PrimeNumbers {
//
//
//    public static boolean isPrime(BigInt n){
//
//        if (n.compareTo(new BigInt("2")) == 0 || n.compareTo(new BigInt("3")) == 0 ) {
//            return true;
//        }
//
//        if (n.compareTo(new BigInt("1")) == 0 || n.compareTo(new BigInt("1")) == -1 ||
//                n.mod(new BigInt("2")).get() == "0" || n.mod(new BigInt("3")).get() == "0") {
//            return false;
//        }
//        BigInt i = new BigInt("5");
//        BigInt six = new BigInt("6");
//        BigInt i2 = new BigInt("5");
//        i2.multiply(i);
//        for (; new BigInt(i2.get()).compareTo(n) == -1 ||new BigInt(i2.get()).compareTo(n) == 0; i.add(six) ){
//            if (n.mod(i).get() == "0" || n.mod(new BigInt( i.add(new BigInt("2")).get() )).get() == "0") {
//                return false;
//            }
//        }
//
//        return true;
//    }
//    public static BigInt randomPrimeNumber(BigInt n){
//        boolean t;
//
//
//        BigInt length = new BigInt("0");
//        BigInt i = new BigInt("2");
//        BigInt one = new BigInt("1");
//        BigInt primeNumber = new BigInt("2");
//        BigInt primeNumberCopy = new BigInt("2");
//
//        List<BigInt> listOfPrimeNumbers = new ArrayList<>();
//        while(length.compareTo(n) == -1) {
//            t = true;
//            for(; i.compareTo(primeNumber) == -1; i.add(one)){
//                if(primeNumberCopy.mod(i).get() == "0")
//                {
//                    t = false;
//                    break;
//                }
//            }
//
//
//            if(t)
//            {
//                listOfPrimeNumbers.add(primeNumber);
//                length.add(one);
//            }
//            primeNumber.add(one);
//            primeNumberCopy.set(primeNumber.get());
//        }
//        Random random = new Random();
//        int position = 3;
//        BigInt chosenPrimeNumber = listOfPrimeNumbers.get(position);
//
//
//        return chosenPrimeNumber;
//    }
////    public static boolean mulletTest(BigInt number){
////
////    }
//}