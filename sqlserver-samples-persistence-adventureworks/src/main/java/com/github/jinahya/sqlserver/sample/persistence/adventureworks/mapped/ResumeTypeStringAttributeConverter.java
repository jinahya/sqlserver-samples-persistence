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
import io.github.jinahya.sqlserver.xml.schema.binding.b3da3e.ObjectFactory;
import io.github.jinahya.sqlserver.xml.schema.binding.b3da3e.ResumeType;
import jakarta.persistence.Converter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.transform.Result;

/**
 * Converts a {@link ResumeType} attribute to the text of an {@code xml(CONTENT HumanResources.HRResumeSchemaCollection)} column, and back.
 * <p>
 * The collection holds one schema, {@code Resume}, whose root, {@code Resume}, is of {@code ResumeType}; it has no class of its own, so the value is written as the element {@link io.github.jinahya.sqlserver.xml.schema.binding.b3da3e.ObjectFactory#createResume(ResumeType)} creates, and read as its content.
 */
@Converter(autoApply = false)
public class ResumeTypeStringAttributeConverter
        extends JaxbAttributeConverter.OfString<ResumeType> {

    // creates the Resume element of a value
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    // the classes of the schemas of the collection, through their ObjectFactory classes
    private static final class ContextHolder {

        private static final JAXBContext CONTEXT;

        static {
            try {
                CONTEXT = JAXBContext.newInstance(
                        ObjectFactory.class
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
    public ResumeTypeStringAttributeConverter() {
        super(ResumeType.class);
    }

    @Override
    protected JAXBContext context() {
        return ContextHolder.CONTEXT;
    }

    @Override
    protected void write(final Marshaller marshaller, final ResumeType attribute, final Result result)
            throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(OBJECT_FACTORY.createResume(attribute), result);
    }
}

