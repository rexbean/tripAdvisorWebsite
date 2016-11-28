package Model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by zihaoli on 11/13/16.
 */
public class Helper
{
    private static Random random;
    private static Status status;
    public Helper()
    {

    }

    /**
     * generate salt for password
     * @return
     */
    public static byte[] generateSalt()
    {
        random=new Random();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return saltBytes;
    }

    /**
     * Returns the hex encoding of a byte array.
     *
     * @param bytes
     *            - byte array to encode
     * @param length
     *            - desired length of encoding
     * @return hex encoded byte array
     */
    public static String encodeHex(byte[] bytes, int length) {
        BigInteger bigint = new BigInteger(1, bytes);
        String hex = String.format("%0" + length + "X", bigint);

        assert hex.length() == length;
        return hex;
    }


    /**
     * hash the input s
     * @param s the input
     * @return md5
     */
    public static String md5(String s)
    {
        String md5 = null;

        if(null == s)
            return null;
        try
        {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(s.getBytes(), 0, s.length());
            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e)
        {
            status=Status.ERROR;
            A.logger.fatal(status.toString()+"md5");
            e.printStackTrace();
        }
        return md5;
    }

    public static String String2UTF8(String s)
    {
        try
        {
            s =new String(s.getBytes("gbk"),"utf-8");

        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return s;
    }
}
