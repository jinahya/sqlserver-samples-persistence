package com.github.jinahya.sqlserver.sample.persistence.common.annotation;

/*-
 * #%L
 * sqlserver-samples-persistence-common
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documents that the column the annotated attribute maps is encrypted with Always Encrypted: the
 * database stores only ciphertext, and the driver encrypts and decrypts the value.
 * <p>
 * Jakarta Persistence has no annotation for Always Encrypted.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/security/encryption/always-encrypted-database-engine">
 *      Always Encrypted</a>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MsEncrypted {

    /**
     * The encryption type of an encrypted column.
     */
    enum EncryptionType {

        /**
         * {@code DETERMINISTIC}; equal values encrypt equally, so the column supports equality
         * comparisons.
         */
        DETERMINISTIC,

        /**
         * {@code RANDOMIZED}; equal values encrypt differently, and the column supports no
         * comparison.
         */
        RANDOMIZED
    }

    /**
     * The column encryption key, {@code CEK_Auto1} say.
     *
     * @return the column encryption key, {@code CEK_Auto1} say.
     */
    String columnEncryptionKey();

    /**
     * The encryption type.
     *
     * @return the encryption type.
     */
    EncryptionType encryptionType();

    /**
     * The encryption algorithm.
     *
     * @return the encryption algorithm.
     */
    String algorithm() default "AEAD_AES_256_CBC_HMAC_SHA_256";
}
