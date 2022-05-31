package main;

public class RSA {
    final private BigInt ZERO = new BigInt("0");
    final private BigInt ONE = new BigInt("1");
    private BigInt originalNumber;
    private BigInt encryptedNumber;
    private BigInt decryptedNumber;
    private BigInt publicKey;
    private BigInt privateKey;
    private BigInt p;
    private BigInt q;
    private BigInt n;
    private BigInt z;

    public RSA(BigInt number) {
        if (number == null) {
            throw new NullPointerException("Given number is null!");
        }

        originalNumber = number;

        this.generatePrimes();
        this.generatePublicKey();
        this.generatePrivateKey();
    }

    // getters
    public BigInt getEncryptedNumber() {
        return encryptedNumber;
    }

    public BigInt getDecryptedNumber() {
        return decryptedNumber;
    }

    public BigInt getPublicKey() {
        return publicKey;
    }

    public BigInt getPrivateKey() {
        return privateKey;
    }

    public BigInt getN() {
        return n;
    }

    // static
    public static String concatKeyWithN(String key, String n) {
        return key + ":" + n;
    }

    public static String extractKey(String longKey) {
        return longKey.split(":")[0];
    }

    public static String extractN(String longKey) {
        return longKey.split(":")[1];
    }

    // public
    public void encode() {
        if (originalNumber == null) {
            throw new NullPointerException("originalNumber is null!");
        }

        if (publicKey == null) {
            throw new NullPointerException("Public key is null!");
        }

        if (n == null) {
            throw new NullPointerException("N is null!");
        }

        encryptedNumber = originalNumber.moduloPower(publicKey, n);
    }

    public void decode() {
        if (encryptedNumber == null) {
            throw new NullPointerException("First you must encrypt the number!");
        }

        if (privateKey == null) {
            throw new NullPointerException("Private key is null!");
        }

        if (n == null) {
            throw new NullPointerException("N is null!");
        }

        decryptedNumber = encryptedNumber.moduloPower(privateKey, n);
    }

    // private
    private void generatePrimes() {
        this.p = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(100, 110));
        this.q = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(150, 180));

        this.n = p.multiply(q);
        this.z = p.diff("1").multiply(q.diff("1"));
    }

    private void generatePublicKey() {
        BigInt propablyPrime = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(z.length() - 10, z.length() - 5));

        while (propablyPrime.compareTo(z) == -1) {
            if (BigInt.gcd(propablyPrime, z).compareTo(ONE) == 0) {
                break;
            }

            propablyPrime = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(z.length() - 10, z.length() - 5));
        }

        publicKey = propablyPrime;
    }

    private void generatePrivateKey() {
        privateKey = publicKey.modInv(z);
    }
}

//public class RSA {
//    final private BigInt ZERO = new BigInt("0");
//    final private BigInt ONE = new BigInt("1");
//    private int[] bytes;
//    private String msg;
//    private String decodedMsg;
//    private BigInt[] encryptedBytes;
//    private int[] decryptedBytes;
//    private BigInt publicKey;
//    private BigInt privateKey;
//    private BigInt p;
//    private BigInt q;
//    private BigInt n;
//    private BigInt z;
//
//    // constructors
//    public RSA(String msg) {
//        if (msg == null) {
//            throw new NullPointerException();
//        } else if (msg.equals("")) {
//            throw new NullPointerException("Empty string exception!");
//        }
//
//
//        int[] temp = new int[msg.length()];
//        char[] chars = msg.toCharArray();
//
//        for (int i = 0; i < msg.length(); i++) {
//            temp[i] = chars[i];
//        }
//
//        this.msg = msg;
//        this.bytes = temp;
//        this.generatePrimes();
//        this.generateKeys();
//    }
//
//    public RSA(int[] bytes) {
//        if (bytes.length == 0) {
//            throw new NullPointerException("Empty byte array exception!");
//        }
//
//        this.bytes = bytes;
//        this.generatePrimes();
//        this.generateKeys();
//    }
//
////    public RSA(String[] codedMsg, String privKey, String pubKey) {
////        if (codedMsg.length == 0 || privKey == null) {
////            throw new NullPointerException();
////        } else if (privKey.equals("")) {
////            throw new NullPointerException("Private key is empty!");
////        }
////
////        BigInt[] mag = new BigInt[codedMsg.length];
////        for (int i = 0; i < codedMsg.length; i++) {
////            mag[i] = new BigInt(codedMsg[i]);
////        }
////
////        this.encryptedBytes = mag;
////        this.privateKey = new BigInt(privKey);
////
////        if (pubKey.equals("")) {
////            this.publicKey = new BigInt(pubKey);
////        }
////    }
//
//    // getters
//    public String getDecodedMsg() {
//        StringBuilder msg = new StringBuilder("");
//
//        for (int i: decryptedBytes) {
//
//            msg.append(Character.toString(i));
//        }
//
//        return msg.toString();
//    }
//
//    public int[] getBytes() {
//        return this.bytes;
//    }
//
//    public BigInt[] getEncodedBytes() {
//        return this.encryptedBytes;
//    }
//
//    public String getEncodedMsg() {
//        StringBuilder msg = new StringBuilder();
//
//        for (BigInt c: this.encryptedBytes) {
//            msg.append(c.get()).append("\n");
//        }
//
//        return msg.toString();
//    }
//
//    public String getPublicKey() {
//        return RSA.concatKeyWithN(publicKey.get(), n.get());
//    }
//
//    public String getPrivateKey() {
//        return RSA.concatKeyWithN(privateKey.get(), n.get());
//    }
//
//    // setters
//    public void setMsg(String msg) {
//        if (msg == null) {
//            return;
//        } else if (msg.equals("")) {
//            return;
//        }
//
//        this.msg = msg;
//    }
//
//    // static
//    public static String concatKeyWithN(String key, String n) {
//        return key + ":" + n;
//    }
//
//    public static String extractKey(String longKey) {
//        return longKey.split(":")[0];
//    }
//
//    public static String extractN(String longKey) {
//        return longKey.split(":")[1];
//    }
//
//    public static String encode(String msg, String longKey) {
//        String pubKey = RSA.extractKey(longKey);
//        String n = RSA.extractN(longKey);
//
//        return RSA.encode(msg, pubKey, n);
//    }
//
//    public static String encode(String msg, String publicKey, String n) {
//        if (msg.equals("") || publicKey.equals("") || n.equals("")) {
//            throw new NullPointerException("Empty string exception!");
//        }
//
//        BigInt biPublicKey = new BigInt(publicKey);
//        BigInt biN = new BigInt(n);
//
//        StringBuilder encryptedMsg = new StringBuilder("");
//
//        for (int c: msg.toCharArray()) {
//            BigInt singleChar = new BigInt(("" + c));
//            BigInt encrypted = singleChar.moduloPower(biPublicKey, biN);
//            encryptedMsg.append(encrypted.get());
//            encryptedMsg.append("\n");
//        }
//
//        return encryptedMsg.toString();
//    }
//
//    public static int[] decode(BigInt[] encodedBytes, String longKey) {
//        String privateKey = RSA.extractKey(longKey);
//        String n = RSA.extractN(longKey);
//
//        return RSA.decode(encodedBytes, privateKey, n);
//    }
//
//    public static int[] decode(BigInt[] encodedBytes, String privateKey, String n) {
//        if (privateKey.equals("") || n.equals("")) {
//            throw new NullPointerException("Empty string exception!");
//        } else if (encodedBytes.length == 0) {
//            throw new NullPointerException("Empty message exception!");
//        }
//
//        BigInt biPrivateKey = new BigInt(privateKey);
//        BigInt biN = new BigInt(n);
//        int[] decoded = new int[encodedBytes.length];
//
//        int i = 0;
//        for (BigInt bi: encodedBytes) {
//            String decodedString = bi.moduloPower(biPrivateKey, biN).get();
//            int decodedInt = Integer.parseInt(decodedString);
//            decoded[i] = decodedInt;
//            i++;
//        }
//
//        return decoded;
//    }
//
//    public static String decode(String[] encodedMsg, String longKey) {
//        String privateKey = RSA.extractKey(longKey);
//        String n = RSA.extractN(longKey);
//
//        return RSA.decode(encodedMsg, privateKey, n);
//    }
//
//    public static String decode(String[] encodedMsg, String privateKey, String n) {
//        if (privateKey.equals("") || n.equals("")) {
//            throw new NullPointerException("Empty string exception!");
//        } else if (encodedMsg.length == 0) {
//            throw new NullPointerException("Empty message exception!");
//        }
//
//        BigInt biPrivateKey = new BigInt(privateKey);
//        BigInt biN = new BigInt(n);
//        StringBuilder decodedMsg = new StringBuilder();
//
//        for (String singleEncodedMsg: encodedMsg) {
//            BigInt biEncodedMsg = new BigInt(singleEncodedMsg);
//            String decryptedAscii = biEncodedMsg.moduloPower(biPrivateKey, biN).get();
//            System.out.println(singleEncodedMsg + " " + n);
//            System.out.println("Private key: " + privateKey);
//            int decryptedInt = Integer.parseInt(decryptedAscii);
//            String decryptedChar = Character.toString(decryptedInt);
//            decodedMsg.append(decryptedChar);
//        }
//
//        return decodedMsg.toString();
//
////        if (privateKey.equals("") || n.equals("")) {
////            throw new NullPointerException("Empty string exception!");
////        } else if (encodedMsg.length == 0) {
////            throw new NullPointerException("Empty message exception!");
////        }
////
////        BigInt biPrivateKey = new BigInt(privateKey);
////        BigInt biN = new BigInt(n);
////        StringBuilder decodedMsg = new StringBuilder();
////
////        for (String singleEncodedMsg: encodedMsg) {
////            BigInt biEncodedMsg = new BigInt(singleEncodedMsg);
////            String decryptedAscii = biEncodedMsg.moduloPower(biPrivateKey, biN).get();
////            int decryptedInt = Integer.parseInt(decryptedAscii);
////            String decryptedChar = Character.toString(decryptedInt);
////            decodedMsg.append(decryptedChar);
////        }
////
////        return decodedMsg.toString();
//    }
//
//    // public
//    public void encode() {
//        int i = 0;
//        BigInt[] newCryptedValues = new BigInt[bytes.length];
//
//        for (int c : bytes) {
//            BigInt singleByte = new BigInt("" + c);
//            BigInt cryptedByte = singleByte.moduloPower(publicKey, n);
//            newCryptedValues[i] = cryptedByte;
//            i++;
//        }
//
//        encryptedBytes = newCryptedValues;
//    }
//
////    public void decode() {
////        if (this.encryptedBytes.length == 0) {
////            return;
////        }
////
////        StringBuilder newMsg = new StringBuilder();
////
////        for (BigInt encryptedByte : encryptedBytes) {
////            String decryptedString = encryptedByte.moduloPower(privateKey, n).get();
////            int decryptedInt = Integer.parseInt(decryptedString);
////            String decryptedChar = Character.toString(decryptedInt);
////            newMsg.append(decryptedChar);
////        }
////
////        this.decodedMsg = newMsg.toString();
////    }
//
//    public void decode() {
//        if (this.encryptedBytes.length == 0) {
//            return;
//        }
//
//        int[] decodedMsg = new int[bytes.length];
//
//        int i = 0;
//        for (BigInt encryptedByte : encryptedBytes) {
//            String decryptedString = encryptedByte.moduloPower(privateKey, n).get();
//            decodedMsg[i] = Integer.parseInt(decryptedString);
//            i++;
//        }
//
//        decryptedBytes = decodedMsg;
//    }
//
//    // privates
//    private void generatePrimes() {
//        this.p = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(100, 110));
//        this.q = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(150, 180));
//
//        this.n = p.multiply(q);
//        this.z = p.diff("1").multiply(q.diff("1"));
//    }
//
//    private void generateKeys() {
//        if (p == null || q == null || z == null || n == null) {
//            return;
//        }
//
//        this.generatePublicKey();
//        this.generatePrivateKey();
//    }
//
//    private void generatePublicKey() {
//        BigInt propablyPrime = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(z.length() - 10, z.length() - 5));
//
//        while (propablyPrime.compareTo(z) == -1) {
//            if (BigInt.gcd(propablyPrime, z).compareTo(ONE) == 0) {
//                break;
//            }
//
//            propablyPrime = PrimeNumbers.primeNumber(PrimeNumbers.bigRolloNumber(z.length() - 10, z.length() - 5));
//        }
//
//        publicKey = propablyPrime;
//    }
//
//    private void generatePrivateKey() {
//        privateKey = publicKey.modInv(z);
//    }
//
//}