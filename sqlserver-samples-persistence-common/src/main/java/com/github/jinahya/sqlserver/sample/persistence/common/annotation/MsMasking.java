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
 * Documents the dynamic data masking of the column the annotated attribute maps: a user without the
 * {@code UNMASK} permission reads the masked value, not the stored one.
 * <p>
 * Jakarta Persistence has no annotation for data masking.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/security/dynamic-data-masking">
 *      Dynamic data masking</a>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MsMasking {

    /**
     * The masking function, as the database defines it, {@code email()} or {@code
     * partial(0,"XXX-XX-",4)} say.
     *
     * @return the masking function, as the database defines it, {@code email()} or {@code
     *         partial(0,"XXX-XX-",4)} say.
     */
    String value();
}
