package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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

import com.github.jinahya.persistence.more.converter.jakartaxmlbind.JaxbAttributeConverter;
import io.github.jinahya.sqlserver.xml.schema.binding.b7fdbe.AdditionalContactInfo;
import jakarta.persistence.Converter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

/**
 * Converts an {@link AdditionalContactInfo} attribute to the text of an
 * {@code xml(CONTENT Person.AdditionalContactInfoSchemaCollection)} column, and back.
 * <p>
 * The collection holds three schemas: {@code ContactInfo}, whose {@code AdditionalContactInfo} is the root, and
 * {@code ContactRecord} and {@code ContactTypes}, whose elements its mixed content holds. The context knows the classes
 * of all three, so that the content is read as {@code ContactRecord}s and as elements of the {@code ContactTypes}
 * classes, {@code telephoneNumber}, {@code homePostalAddress}, {@code eMail} and the others, not as DOM elements.
 */
@Converter(autoApply = false)
public class AdditionalContactInfoStringAttributeConverter
        extends JaxbAttributeConverter.OfString<AdditionalContactInfo> {

    // the classes of the three schemas of the collection, through their ObjectFactory classes
    private static final class ContextHolder {

        private static final JAXBContext CONTEXT;

        static {
            try {
                CONTEXT = JAXBContext.newInstance(
                        io.github.jinahya.sqlserver.xml.schema.binding.b7fdbe.ObjectFactory.class,
                        io.github.jinahya.sqlserver.xml.schema.binding.b80e2e.ObjectFactory.class,
                        io.github.jinahya.sqlserver.xml.schema.binding.b2e7ae.ObjectFactory.class
                );
            } catch (final JAXBException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        private ContextHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Creates a new instance.
     */
    public AdditionalContactInfoStringAttributeConverter() {
        super(AdditionalContactInfo.class);
    }

    @Override
    protected JAXBContext context() {
        return ContextHolder.CONTEXT;
    }
}
