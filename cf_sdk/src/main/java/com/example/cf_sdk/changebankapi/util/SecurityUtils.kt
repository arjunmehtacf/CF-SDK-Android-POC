package com.example.cf_sdk.changebankapi.util

import java.security.Key
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import java.util.Base64
import java.util.UUID
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/*
 * Copyright 2016 Farbod Safaei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */   object SecurityUtils {
    private const val DEFAULT_HASH_ITERATION_COUNT = 1
    private const val DEFAULT_HASH_KEY_LENGTH = 512
    private const val DEFAULT_SALT_SEED_SIZE = 16
    private val DEFAULT_HASH_ALGORITHM = HashAlgorithm.PBKDF2_WITH_HMAC_SHA1
    private val DEFAULT_RANDOM_SEED_ALGORITHM = SeedAlgorithm.SHA1PRNG

    /**
     * Generates a salt value using the default seed size provided in this class.
     *
     *
     * Note: To preserve consistency seed size should not be changed.
     *
     * This method uses "SHA1PRNG" algorithm to generate the salt
     *
     * @return Base64 encoded salt value
     *
     * @throws IllegalArgumentException
     * If seed algorithm does not exist
     */
    @Throws(IllegalArgumentException::class)
    fun generateRandomSalt(): ByteArray {
        return generateRandomSalt(DEFAULT_RANDOM_SEED_ALGORITHM, DEFAULT_SALT_SEED_SIZE)
    }

    /**
     * Generates a salt value using a seed algorithm and a seed size
     *
     * @param seedAlgorithm
     * A value from `SeedAlgorithm`
     *
     * @param seedSize
     * Size of seed
     *
     * @return  byte array of random generated salt
     *
     * @throws IllegalArgumentException
     * If seed algorithm is not correct or does not exist
     */
    @Throws(IllegalArgumentException::class)
    private fun generateRandomSalt(seedAlgorithm: SeedAlgorithm, seedSize: Int): ByteArray {
        return try {
            SecureRandom.getInstance(seedAlgorithm.name).generateSeed(seedSize)
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalArgumentException(e)
        }
    }

    fun createHashedPassword(rawPassword: CharArray, salt: ByteArray): String {
        return createHashedPassword(rawPassword, salt, DEFAULT_HASH_ALGORITHM)
    }

    @JvmStatic
    fun createFingerprintHash(fingerprintPassword: String): String {
        //Generate Fingerprint Hash
        val str = fingerprintPassword + UUID.randomUUID()
        val password = str.toCharArray()
        return createHashedPassword(password, generateRandomSalt())
    }

    private fun createHashedPassword(
        rawPassword: CharArray,
        salt: ByteArray,
        algorithm: HashAlgorithm
    ): String {
        return try {
            val keyFactory = SecretKeyFactory.getInstance(algorithm.algorithm)
            val keySpec: KeySpec = PBEKeySpec(
                rawPassword, salt,
                DEFAULT_HASH_ITERATION_COUNT, DEFAULT_HASH_KEY_LENGTH
            )
            val secretKey: Key = keyFactory.generateSecret(keySpec)
            Base64.getEncoder().encode(secretKey.encoded).toString()
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalArgumentException(e)
        } catch (e: InvalidKeySpecException) {
            throw IllegalArgumentException(e)
        }
    }

    /**
     * PBKDF2 (Password-Based Key Derivation Function 2) algorithms singleton.
     *
     * This class contains constants of algorithms supported by
     * [SecretKeyFactory] class.
     *
     * @author Farbod Safaei
     */
    private enum class HashAlgorithm(
        /**
         * Getter method to get the string value of enum constant
         *
         * @return String value of hash algorithm
         */
        val algorithm: String
    ) {
        /**
         * String representation of PBKDF2WithHmacSHA1 algorithm
         */
        PBKDF2_WITH_HMAC_SHA1("PBKDF2WithHmacSHA1");

    }

    /**
     * Seed algorithms singleton.
     *
     * This class contains constants of seed algorithms supported
     * by [SecureRandom] class
     *
     * @author Farbod Safaei
     */
    private enum class SeedAlgorithm(
        /**
         * Getter method to get the string value of enum constant
         *
         * @return String value of seed algorithm
         */
        val algorithm: String
    ) {
        /**
         * String representation of SHA1PRNG algorithm
         */
        SHA1PRNG("SHA1PRNG");

    }
}