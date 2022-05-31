package Test;

import org.junit.jupiter.api.Test;

import main.BigInt;

import static org.junit.jupiter.api.Assertions.*;

public class BigIntTest {
    @Test
    public void constructorTest() {
        String val = "04444444444444444444444455";
        int len = val.length();
        BigInt bi = new BigInt(val);
        int c = 4;

        assertEquals(c, bi.getBytes()[0]);
        assertEquals(len - 1, bi.length());
    }

    @Test
    public void getTest() {
        String val1 = "49846564684534984346100056106846154684068498730";
        String val2 = "00000000055549846564684534984346100056106846154684068498730";
        String val2Correct = "55549846564684534984346100056106846154684068498730";
        BigInt bi1 = new BigInt(val1);
        BigInt bi2 = new BigInt(val2);

        assertEquals(val1, bi1.get());
        assertEquals(val2Correct, bi2.get());
    }

    @Test
    public void multiplyTest() {
        String val1 = "66589";
        String val2 = "31158989";
        String res = "2074845918521";

        BigInt bi1 = new BigInt(val1);
        BigInt bi2 = new BigInt(val2);
        BigInt biRes = new BigInt(res);

        assertEquals(biRes.get(), bi1.multiply(bi2).get());
    }

    @Test
    public void modPowTest() {
        BigInt bi1 = new BigInt("3");
        BigInt bi2 = new BigInt("45");
        BigInt bi3 = new BigInt("7");

        String res = "6";

        assertEquals(res, bi1.moduloPower(bi2, bi3).get());

        bi1.set("51");
        bi2.set("37");
        bi3.set("100");
        res = "51";

        assertEquals(res, bi1.moduloPower(bi2, bi3).get());

        bi1.set("59");
        bi2.set("9843521");
        bi3.set("65465498416");
        res = "16980629179";

        assertEquals(res, bi1.moduloPower(bi2, bi3).get());

        bi1.set("1442897");
        bi2.set("4115226337448559670781893004115226337448559670781893004115226337448559670781893004115226337448559670781893004115226337448559670781893004115226337448559670781893004115226337448559670781893004115226337448559670370370370370370370370370370370370370370370370370370370370370329218106995884773662551440329218106995884773662551440329218106995884773662551440329218106995884773662551440329218106995884773662551440329218106995884773662551440329218106995884773662551440329218106995884773667");
        bi3.set("12345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679012345679011333333333333333333333333333333333333333333333333333333333333265432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098765432098771");
//        res = "16980629179";

        System.out.println(bi1.moduloPower(bi2, bi3).get());
    }

    @Test
    public void comperatorTest() {
        BigInt bi1 = new BigInt("99999999");
        BigInt bi2 = new BigInt("91111111");
        BigInt bi3 = new BigInt("1");
        BigInt bi4 = new BigInt("10000000000000");

        assertEquals(0, bi1.compareTo(bi1));
        assertEquals(1, bi1.compareTo(bi2));
        assertEquals(1, bi1.compareTo(bi3));
        assertEquals(-1, bi1.compareTo(bi4));
    }

    @Test
    public void diffTest() {
        BigInt bi = new BigInt("1000");
        assertEquals("999", bi.diff("1").get());
    }

    @Test
    public void divideTest() {
        BigInt bi = new BigInt("1000");
        assertEquals("500", bi.divide("2").get());
    }

    @Test
    public void modTest( ){
        BigInt bi = new BigInt("1026");
        assertEquals("1", bi.mod("25").get());
    }

}
