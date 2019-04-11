import java.math.BigInteger;

public class hextoint {
    public static void main(String[] args) {
        String data = "05";
        String str = "ffffff";
        String hexdata = str + data;
        BigInteger bi = new BigInteger(hexdata, 16);
        int intValue=bi.intValue();
        System.out.println(intValue);
    }
}
