package main;

public class RSA {
    final private BigInt ZERO = new BigInt("0");
    final private BigInt ONE = new BigInt("1");
    private String msg;
    private String decodedMsg;
    private BigInt[] encryptedBytes;
    private BigInt publicKey;
    private BigInt privateKey;
    private BigInt p;
    private BigInt q;
    private BigInt n;
    private BigInt z;

    // constructors
    public RSA(String msg) {
        if (msg == null) {
            throw new NullPointerException();
        } else if (msg.equals("")) {
            throw new NullPointerException("Empty string exception!");
        }

        this.msg = msg;
        this.generatePrimes();
        this.generateKeys();
    }

    public RSA(byte[] bytes) {
        if (bytes.length == 0) {
            throw new NullPointerException("Empty byte array exception!");
        }


    }

//    public RSA(String[] codedMsg, String privKey, String pubKey) {
//        if (codedMsg.length == 0 || privKey == null) {
//            throw new NullPointerException();
//        } else if (privKey.equals("")) {
//            throw new NullPointerException("Private key is empty!");
//        }
//
//        BigInt[] mag = new BigInt[codedMsg.length];
//        for (int i = 0; i < codedMsg.length; i++) {
//            mag[i] = new BigInt(codedMsg[i]);
//        }
//
//        this.encryptedBytes = mag;
//        this.privateKey = new BigInt(privKey);
//
//        if (pubKey.equals("")) {
//            this.publicKey = new BigInt(pubKey);
//        }
//    }

    // getters
    public String getDecodedMsg() {
        return this.decodedMsg;
    }

    public String getEncodedMsg() {
        StringBuilder msg = new StringBuilder();

        for (BigInt c: this.encryptedBytes) {
            msg.append(c.get()).append("\n");
        }

        return msg.toString();
    }

    public String getPublicKey() {
        return RSA.concatKeyWithN(publicKey.get(), n.get());
    }

    public String getPrivateKey() {
        return RSA.concatKeyWithN(privateKey.get(), n.get());
    }

    // setters
    public void setMsg(String msg) {
        if (msg == null) {
            return;
        } else if (msg.equals("")) {
            return;
        }

        this.msg = msg;
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

    public static String encode(String msg, String longKey) {
        String pubKey = RSA.extractKey(longKey);
        String n = RSA.extractN(longKey);

        return RSA.encode(msg, pubKey, n);
    }

    public static String encode(String msg, String publicKey, String n) {
        if (msg.equals("") || publicKey.equals("") || n.equals("")) {
            throw new NullPointerException("Empty string exception!");
        }

        BigInt biPublicKey = new BigInt(publicKey);
        BigInt biN = new BigInt(n);

        StringBuilder encryptedMsg = new StringBuilder("");

        for (int c: msg.toCharArray()) {
            BigInt singleChar = new BigInt(("" + c));
            BigInt encrypted = singleChar.moduloPower(biPublicKey, biN);
            encryptedMsg.append(encrypted.get());
            encryptedMsg.append("\n");
        }

        return encryptedMsg.toString();
    }

    public static String decode(String[] encodedMsg, String longKey) {
        String privateKey = RSA.extractKey(longKey);
        String n = RSA.extractN(longKey);

        return RSA.decode(encodedMsg, privateKey, n);
    }

    public static String decode(String[] encodedMsg, String privateKey, String n) {
        if (privateKey.equals("") || n.equals("")) {
            throw new NullPointerException("Empty string exception!");
        } else if (encodedMsg.length == 0) {
            throw new NullPointerException("Empty message exception!");
        }

        BigInt biPrivateKey = new BigInt(privateKey);
        BigInt biN = new BigInt(n);
        StringBuilder decodedMsg = new StringBuilder();

        for (String singleEncodedMsg: encodedMsg) {
            BigInt biEncodedMsg = new BigInt(singleEncodedMsg);
            String decryptedAscii = biEncodedMsg.moduloPower(biPrivateKey, biN).get();
            int decryptedInt = Integer.parseInt(decryptedAscii);
            String decryptedChar = Character.toString(decryptedInt);
            decodedMsg.append(decryptedChar);
        }

        return decodedMsg.toString();
    }

    // public
    public void encode() {
        int i = 0;
        BigInt[] newCryptedValues = new BigInt[msg.length()];
        for (int c : msg.toCharArray()) {
            BigInt singleChar = new BigInt(("" + c));
            BigInt crypted = singleChar.moduloPower(publicKey, n);
            newCryptedValues[i] = crypted;
            i++;
        }

        encryptedBytes = newCryptedValues;
    }

    public void decode() {
        if (this.encryptedBytes.length == 0) {
            return;
        }

        StringBuilder newMsg = new StringBuilder();

        for (BigInt encryptedByte : encryptedBytes) {
            String decryptedString = encryptedByte.moduloPower(privateKey, n).get();
            int decryptedInt = Integer.parseInt(decryptedString);
            String decryptedChar = Character.toString(decryptedInt);
            newMsg.append(decryptedChar);
        }

        this.decodedMsg = newMsg.toString();
    }

    // privates
    private void generatePrimes() {
        // to delete
        this.p = new BigInt(Long.toString(PrimeNumbers.getPrimeNumber(5000)));
        this.q = new BigInt(Long.toString(PrimeNumbers.getPrimeNumber(20000)));

//        this.p = PrimeNumbers.randomPrimeNumber(new BigInt("65646464646454433333"));
//        this.q = PrimeNumbers.randomPrimeNumber(new BigInt("94846545464654646545468"));
        this.n = p.multiply(q);
        this.z = p.diff("1").multiply(q.diff("1"));
        // ^^^^
    }

    private void generateKeys() {
        if (p == null || q == null || z == null || n == null) {
            return;
        }

        this.generatePublicKey();
        this.generatePrivateKey();
    }

    private void generatePublicKey() {
        BigInt startNumber = new BigInt("2");


        // while (startNumber.compareTo(ZERO) != 0) {
        //   startNumber = startNumber.diff("1");
        //   System.out.println("public - " + startNumber);
        //   if (BigInt.gcd(startNumber, z).compareTo(ONE) == 0) {
        //     break;
        //   }
        // }

        while (startNumber.compareTo(n) == -1) {

            if (BigInt.gcd(startNumber, z).compareTo(ONE) == 0) {
                break;
            }

            startNumber = startNumber.add("1");
        }

        publicKey = startNumber;
    }

    private void generatePrivateKey() {
        for (BigInt bi = new BigInt("1"); bi.compareTo(n) == -1; bi = bi.add("1")) {

            BigInt val = ONE.add(bi.multiply(z));

            if (val.mod(publicKey).compareTo(ZERO) == 0) {
                privateKey = new BigInt(val.divide(publicKey).get());
                break;
            }
        }
    }

}