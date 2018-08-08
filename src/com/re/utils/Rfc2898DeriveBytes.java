package com.re.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

/**
 * 生成哈希加盐(hash+salt)密码的工具类
 */
public class Rfc2898DeriveBytes {

    private static final int BLOCK_SIZE = 20;
    private static Random random = new Random();
    private int startIndex = 0;
    private int endIndex = 0;
    private int block = 1;
    private byte[] buffer = new byte[BLOCK_SIZE];
    private byte[] salt;
    private int iterations;
    private Mac hmacsha1;

    /**
     * 构造函数
     *
     * @param password   用于生成加密密码的源密码
     * @param salt       用于生成加密密码的盐值
     * @param iterations 迭代次数
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public Rfc2898DeriveBytes(byte[] password, byte[] salt, int iterations) throws NoSuchAlgorithmException,
            InvalidKeyException {
        this.salt = salt;
        this.iterations = iterations;
        this.hmacsha1 = Mac.getInstance("HmacSHA1");
        this.hmacsha1.init(new SecretKeySpec(password, "HmacSHA1"));
    }

    public Rfc2898DeriveBytes(String password, int saltSize, int iterations) throws NoSuchAlgorithmException,
            InvalidKeyException, UnsupportedEncodingException {
        this.salt = randomSalt(saltSize);
        this.iterations = iterations;
        this.hmacsha1 = Mac.getInstance("HmacSHA1");
        this.hmacsha1.init(new SecretKeySpec(password.getBytes("UTF-8"), "HmacSHA1"));
        this.buffer = new byte[BLOCK_SIZE];
        this.block = 1;
        this.startIndex = this.endIndex = 1;
    }


    public Rfc2898DeriveBytes(String password, int saltSize) throws NoSuchAlgorithmException, InvalidKeyException,
            UnsupportedEncodingException {
        this(password, saltSize, 1000);
    }

    public Rfc2898DeriveBytes(String password, byte[] salt, int iterations) throws InvalidKeyException,
            NoSuchAlgorithmException {
        this(password.getBytes(StandardCharsets.UTF_8), salt, iterations);
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public String getSaltAsString() {
        return Base64.encodeBase64String(this.salt);
    }

    public byte[] getBytes(int cb) {
        byte[] result = new byte[cb];
        int offset = 0;
        int size = this.endIndex - this.startIndex;
        if (size > 0) { // if there is some data in buffer
            if (cb >= size) { // if there is enough data in buffer
                System.arraycopy(this.buffer, this.startIndex, result, 0, size);
                this.startIndex = this.endIndex = 0;
                offset += size;
            } else {
                System.arraycopy(this.buffer, this.startIndex, result, 0, cb);
                startIndex += cb;
                return result;
            }
        }

        while (offset < cb) {
            byte[] block = this.func();
            int remainder = cb - offset;
            if (remainder > BLOCK_SIZE) {
                System.arraycopy(block, 0, result, offset, BLOCK_SIZE);
                offset += BLOCK_SIZE;
            } else {
                System.arraycopy(block, 0, result, offset, remainder);
                offset += remainder;
                System.arraycopy(block, remainder, this.buffer, startIndex, BLOCK_SIZE - remainder);
                endIndex += (BLOCK_SIZE - remainder);
                return result;
            }
        }
        return result;
    }

    public static byte[] randomSalt(int size) {
        byte[] salt = new byte[size];
        random.nextBytes(salt);
        return salt;
    }

    public static String generateSalt(int size) {
        byte[] salt = randomSalt(size);
        return Base64.encodeBase64String(salt);
    }

    private byte[] func() {
        this.hmacsha1.update(this.salt, 0, this.salt.length);
        byte[] tempHash = this.hmacsha1.doFinal(getBytesFromInt(this.block));

        this.hmacsha1.reset();
        byte[] finalHash = tempHash;
        for (int i = 2; i <= this.iterations; i++) {
            tempHash = this.hmacsha1.doFinal(tempHash);
            for (int j = 0; j < 20; j++) {
                finalHash[j] = (byte) (finalHash[j] ^ tempHash[j]);
            }
        }
        if (this.block == 2147483647) {
            this.block = -2147483648;
        } else {
            this.block += 1;
        }
        return finalHash;
    }

    private static byte[] getBytesFromInt(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }


}
