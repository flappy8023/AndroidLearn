package com.example.androidlearn.security;// javac DHHHHHH.java && java DHHHHHH
// "Just use libsodium if you can," also applies for every other language below

import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DH {
    BigInteger generatorValue, primeValue, publicA, publicB, secretA, secretB, sharedKeyA, sharedKeyB;
    Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization() //当Map的key为复杂对象时,需要开启该方法
            .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
            .disableHtmlEscaping() //防止特殊字符出现乱码
            .create();
    int bitLength = 128;//128位密钥
    private byte[] ivBytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    int certainty = 20;// probabilistic prime generator 1-2^-certainty => practically 'almost sure'
    SecretKey sharedKey;
    private static final SecureRandom rnd = new SecureRandom();

    public static DH get() {
        return DHHolder.dh;
    }

    private static class DHHolder {
        private static final DH dh = new DH();
    }

    public byte[] getP() {
        primeValue = findPrime();

        return bigIntegerToByteArr(primeValue);
    }

    /**
     * 大数转化为128位，去除符号位
     *
     * @param big
     * @return
     */
    private byte[] bigIntegerToByteArr(BigInteger big) {
        byte[] arr = big.toByteArray();
        byte[] temp = new byte[16];
        if (arr.length > 16 && arr[0] == 0) {
            System.arraycopy(arr, 1, temp, 0, 16);
            arr = temp;
        }
        return arr;
    }

    public byte[] getG() {
        generatorValue = new BigInteger("2");//BigInteger.valueOf((long)p);
        return generatorValue.toByteArray();
    }

    public byte[] getPubKey() {
        //Random randomGenerator = new Random();
        Random randomGenerator = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                randomGenerator = SecureRandom.getInstanceStrong();
            } else {
                randomGenerator = SecureRandom.getInstance("SHA1PRNG");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        secretA = new BigInteger(bitLength - 2, randomGenerator);
        publicA = generatorValue.modPow(secretA, primeValue);
        return bigIntegerToByteArr(publicA);

    }


    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    private SecretKey toKey(byte[] key) throws Exception {
        // 实例化AES密钥材料
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }

    public SecretKey buildCommonKey(String receiverPublicKeyEnc, String p, String g) {
        try {
            publicB = new BigInteger(bytesToHex(Base64.decode(receiverPublicKeyEnc,Base64.DEFAULT)), 16);
            sharedKeyA = publicB.modPow(secretA, new BigInteger(bytesToHex(Base64.decode(p,Base64.DEFAULT)), 16));// should always be same as:
            byte[] temp = bigIntegerToByteArr(sharedKeyA);
            sharedKey = toKey(temp);
            return sharedKey;
        } catch (Exception e) {
            Log.e("DH", e.getLocalizedMessage());
        }
        return null;
    }


    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    private static long f(long a, long n, long p) {
        long x = 1;
        for (long i = 0; i < n; i++) {
            x = (x * a) % p;
        }
        return x % p;
    }

    public byte[] encData(Object data) {
        //4.加密
        try {
            String strData = gson.toJson(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//PKCS5Padding    ISO10126Padding
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, sharedKey, iv);
            byte[] result = cipher.doFinal(strData.getBytes("utf-8"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decData(byte[] recive) {
        //5.解密
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//PKCS5Padding
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, sharedKey, iv);
            byte[] result = cipher.doFinal(recive);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }


    private static boolean miller_rabin_pass(BigInteger a, BigInteger n) {
        BigInteger n_minus_one = n.subtract(BigInteger.ONE);
        BigInteger d = n_minus_one;
        int s = d.getLowestSetBit();
        d = d.shiftRight(s);
        BigInteger a_to_power = a.modPow(d, n);
        if (a_to_power.equals(BigInteger.ONE)) {
            return true;
        }
        for (int i = 0; i < s - 1; i++) {
            if (a_to_power.equals(n_minus_one)) {
                return true;
            }
            a_to_power = a_to_power.multiply(a_to_power).mod(n);
        }
        if (a_to_power.equals(n_minus_one)) {
            return true;
        }
        return false;
    }

    public static boolean miller_rabin(BigInteger n) {
        for (int repeat = 0; repeat < 20; repeat++) {
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rnd);
            } while (a.equals(BigInteger.ZERO));
            if (!miller_rabin_pass(a, n)) {
                return false;
            }
        }
        return true;
    }

    boolean isPrime(BigInteger r) {
        return miller_rabin(r);
        // return BN_is_prime_fasttest_ex(r,bitLength)==1;
    }

    public List<BigInteger> primeFactors(BigInteger number) {
        BigInteger n = number;
        BigInteger i = BigInteger.valueOf(2);
        BigInteger limit = BigInteger.valueOf(10000);// speed hack! -> consequences ???
        List<BigInteger> factors = new ArrayList<BigInteger>();
        while (!n.equals(BigInteger.ONE)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
                // System.out.println(i);
                // System.out.println(n);
                if (isPrime(n)) {
                    factors.add(n);// yes?
                    return factors;
                }
            }
            i = i.add(BigInteger.ONE);
            if (i.equals(limit)) return factors;// hack! -> consequences ???
            // System.out.print(i+"    \r");
        }
        System.out.println(factors);
        return factors;
    }


    BigInteger findPrime() {
        //Random rnd = new Random();
        Random rnd = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                rnd = SecureRandom.getInstanceStrong();
            } else {
                rnd = SecureRandom.getInstance("SHA1PRNG");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger p = BigInteger.ZERO;
        // while(!isPrime(p))
        p = new BigInteger(bitLength, certainty, rnd);// sufficiently NSA SAFE?!!
        return p;

        // BigInteger r;
        // BigInteger r2= BN_generate_prime(r,512);
        //  System.out.println("isPrime(i)? "+r+" "+r2);
        // return r;
    }


}