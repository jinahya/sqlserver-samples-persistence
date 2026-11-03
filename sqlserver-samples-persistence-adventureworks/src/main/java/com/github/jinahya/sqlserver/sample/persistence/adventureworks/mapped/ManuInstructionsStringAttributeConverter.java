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
import io.github.jinahya.sqlserver.xml.schema.binding.b73cae.Root;
import jakarta.persistence.Converter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

/**
 * Converts a {@link Root} attribute to the text of an
 * {@code xml(CONTENT Production.ManuInstructionsSchemaCollection)} column, and back.
 * <p>
 * The collection holds one schema, {@code ProductModelManuInstructions}, whose {@code root} is the
 * root.
 */
@Converter(autoApply = false)
public class ManuInstructionsStringAttributeConverter
        extends JaxbAttributeConverter.OfString<Root> {

    // the classes of the schemas of the collection, through their ObjectFactory classes
    private static final class ContextHolder {

        private static final JAXBContext CONTEXT;

        static {
            try {
                CONTEXT = JAXBContext.newInstance(
                        io.github.jinahya.sqlserver.xml.schema.binding.b73cae.ObjectFactory.class
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
    public ManuInstructionsStringAttributeConverter() {
        super(Root.class);
    }

    @Override
    protected JAXBContext context() {
        return ContextHolder.CONTEXT;
    }
}

