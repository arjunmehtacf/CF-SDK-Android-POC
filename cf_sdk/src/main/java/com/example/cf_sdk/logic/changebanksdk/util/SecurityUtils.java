package com.example.cf_sdk.logic.changebanksdk.util;


import com.google.common.io.BaseEncoding;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecurityUtils {
    private static final int DEFAULT_HASH_ITERATION_COUNT = 1;
    private static final int DEFAULT_HASH_KEY_LENGTH = 512;
    private static final int DEFAULT_SALT_SEED_SIZE = 16;
    private static final HashAlgorithm DEFAULT_HASH_ALGORITHM = HashAlgorithm.PBKDF2_WITH_HMAC_SHA1;
    private static final SeedAlgorithm DEFAULT_RANDOM_SEED_ALGORITHM = SeedAlgorithm.SHA1PRNG;


    /**
     * Generates a salt value using the default seed size provided in this class.
     *
     * <p>Note: To preserve consistency seed size should not be changed.</p>
     *
     * This method uses "SHA1PRNG" algorithm to generate the salt
     *
     * @return Base64 encoded salt value
     *
     * @throws IllegalArgumentException
     *          If seed algorithm does not exist
     */
    public static byte[] generateRandomSalt() throws IllegalArgumentException {
        return generateRandomSalt(DEFAULT_RANDOM_SEED_ALGORITHM, DEFAULT_SALT_SEED_SIZE);
    }

    /**
     * Generates a salt value using a seed algorithm and a seed size
     *
     * @param seedAlgorithm
     *          A value from {@code SeedAlgorithm}
     *
     * @param seedSize
     *          Size of seed
     *
     * @return  byte array of random generated salt
     *
     * @throws IllegalArgumentException
     *          If seed algorithm is not correct or does not exist
     */
    private static byte[] generateRandomSalt(SeedAlgorithm seedAlgorithm, final int seedSize)
            throws IllegalArgumentException {
        try {
            return SecureRandom.getInstance(seedAlgorithm.name()).generateSeed(seedSize);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String createHashedPassword(final char[] rawPassword, final byte[] salt) {
        return createHashedPassword(rawPassword, salt, DEFAULT_HASH_ALGORITHM);
    }

    public static String createFingerprintHash(String fingerprintPassword) {
        //Generate Fingerprint Hash
        String str = fingerprintPassword + UUID.randomUUID();
        char[] password = str.toCharArray();
        return createHashedPassword(password, generateRandomSalt());
    }

    private static String createHashedPassword(final char[] rawPassword,
                                               final byte[] salt,
                                               HashAlgorithm algorithm) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm.getAlgorithm());

            KeySpec keySpec = new PBEKeySpec(rawPassword, salt,
                    DEFAULT_HASH_ITERATION_COUNT, DEFAULT_HASH_KEY_LENGTH);

            Key secretKey = keyFactory.generateSecret(keySpec);
            return BaseEncoding.base64().encode(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * PBKDF2 (Password-Based Key Derivation Function 2) algorithms singleton.
     *
     * This class contains constants of algorithms supported by
     * {@link SecretKeyFactory} class.
     *
     * @author Farbod Safaei
     *
     */
    private enum HashAlgorithm {

        /**
         * String representation of PBKDF2WithHmacSHA1 algorithm
         */
        PBKDF2_WITH_HMAC_SHA1("PBKDF2WithHmacSHA1");

        HashAlgorithm(String s) {
            this.algorithm = s;
        }

        private String algorithm;

        /**
         * Getter method to get the string value of enum constant
         *
         * @return String value of hash algorithm
         */
        public String getAlgorithm() {
            return this.algorithm;
        }
    }

    /**
     * Seed algorithms singleton.
     *
     * This class contains constants of seed algorithms supported
     * by {@link SecureRandom} class
     *
     * @author Farbod Safaei
     */
    private enum SeedAlgorithm {

        /**
         * String representation of SHA1PRNG algorithm
         */
        SHA1PRNG("SHA1PRNG");

        SeedAlgorithm(String s) {
            this.algorithm = s;
        }

        private String algorithm;

        /**
         * Getter method to get the string value of enum constant
         *
         * @return String value of seed algorithm
         */
        public String getAlgorithm() {
            return this.algorithm;
        }
    }
}
