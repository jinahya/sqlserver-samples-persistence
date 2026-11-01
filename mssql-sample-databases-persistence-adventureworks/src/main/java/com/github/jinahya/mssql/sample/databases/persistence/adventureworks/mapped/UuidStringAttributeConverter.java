package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

/**
 * Converts a {@link UUID} attribute to the text of a {@code uniqueidentifier} column, and back.
 * <p>
 * Jakarta Persistence 3.1 made {@link UUID} a basic type, but leaves its column type to the provider. The SQL Server
 * JDBC driver reads a {@code uniqueidentifier} as its text, {@code 6F9619FF-8B86-D011-B42D-00C04FC964FF}, which one
 * provider maps to a {@link UUID} and another does not; this converter makes the text the column type for every
 * provider. SQL Server converts the text to a {@code uniqueidentifier} on a write.
 * <p>
 * A converter is not applied to an identifier, so a {@code uniqueidentifier} identifier is mapped as a {@link String}.
 */
@Converter(autoApply = false)
public class UuidStringAttributeConverter implements AttributeConverter<UUID, String> {

    /**
     * Creates a new instance.
     */
    public UuidStringAttributeConverter() {
        super();
    }

    @Override
    public String convertToDatabaseColumn(final UUID attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public UUID convertToEntityAttribute(final String dbData) {
        return dbData == null ? null : UUID.fromString(dbData);
    }
}
