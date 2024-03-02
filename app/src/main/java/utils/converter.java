package utils;

public class converter {

    public static String intToHex(int iVal) {
        String ret = Integer.toHexString(iVal).toUpperCase();

        if (ret.length() == 1) {
            ret = "0" + ret;
        }

        return ret;
    }

}
