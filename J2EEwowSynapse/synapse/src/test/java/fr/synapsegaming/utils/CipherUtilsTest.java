package fr.synapsegaming.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CipherUtilsTest {

    private static final String CLEAR_PASSWORD = "Test1.";
    private static final String ENCRYPTED_PASSWORD = "vjZn7mxIbux578oxUfoU1A==";

    @Test
    public void testEncrypt() {
        assertTrue(CipherUtils.encrypt(CLEAR_PASSWORD).equals(ENCRYPTED_PASSWORD));
    }

    @Test
    public void testDecrypt() {
        assertTrue(CipherUtils.decrypt(ENCRYPTED_PASSWORD).equals(CLEAR_PASSWORD));
    }

}
