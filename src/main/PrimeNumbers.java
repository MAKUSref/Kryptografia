package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PrimeNumbers {
    public static BigInt bigRolloNumber(int minLength, int maxLength){
        Random random = new Random();

        int randomLength = random.nextInt(minLength,maxLength);
        String randomNumber = String.valueOf(random.nextInt(0,9));
        String stringNumber = "";
        for(int i = 0; i < randomLength;i++){
            stringNumber += randomNumber;
        }
        stringNumber += new BigInt("1");
        BigInt bigNumber = new BigInt(stringNumber);

        return bigNumber;
    }

    public static BigInt primeNumber(BigInt bigRolloNumber){
        BigInteger prime = new BigInteger(bigRolloNumber.get());
        BigInt two = new BigInt("2");
        if(prime.isProbablePrime(10000)){
            return new BigInt(prime.toString());
        }
        else{
            return primeNumber(bigRolloNumber.add(two));
        }
    }



}
//public class PrimeNumbers {
//    public static long getPrimeNumber(long limit) {
//        long num = limit;
//        while (!PrimeNumbers.isPrime(num)) {
//            num--;
//        }
//
//        return num;
//    }
//
//    public static boolean isPrime(long num) {
//        for (int i = 2; i < num / 2; i++) {
//            if (num % i == 0) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//}
