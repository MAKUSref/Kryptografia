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
