package fr.synapsegaming.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>Cipher</b> encrypt / decrypt strings
 * 
 * @author Meidi
 * 
 */
public class CipherUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CipherUtils.class);

    private static byte[] key = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,
        0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79 };

    /**
     * Encrypt a data
     * 
     * @param strToEncrypt
     *            : string to encrypt
     * @return encrypted string
     */
    public static String encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (Exception e) {
            LOGGER.error("Error while encrypting", e);
        }
        return null;

    }

    /**
     * Decrypt a Cipher data
     * 
     * @param strToDecrypt : string to decrypt
     * @return encrypted string
     */
    public static String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        } catch (Exception e) {
            LOGGER.error("Error while encrypting", e);
        }
        return null;
    }
}