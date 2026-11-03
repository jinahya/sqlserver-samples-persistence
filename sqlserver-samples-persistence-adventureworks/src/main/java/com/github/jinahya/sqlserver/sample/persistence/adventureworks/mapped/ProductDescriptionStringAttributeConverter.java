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
import io.github.jinahya.sqlserver.xml.schema.binding.b0fd0e.ObjectFactory;
import io.github.jinahya.sqlserver.xml.schema.binding.b0fd0e.ProductDescription;
import jakarta.persistence.Converter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.transform.Result;

/**
 * Converts a {@link ProductDescription} attribute to the text of an {@code xml(CONTENT Production.ProductDescriptionSchemaCollection)} column, and back.
 * <p>
 * The collection holds two schemas: {@code ProductModelDescription}, whose root, {@code ProductDescription}, has no class of its own, so the value is written as the element {@link io.github.jinahya.sqlserver.xml.schema.binding.b0fd0e.ObjectFactory#createProductDescription(ProductDescription)} creates, and read as its content; and {@code ProductModelWarrAndMain}, whose {@code Warranty} and {@code Maintenance} the description holds. A processing instruction before the root, such as the installed rows' {@code xml-stylesheet}, is not kept.
 */
@Converter(autoApply = false)
public class ProductDescriptionStringAttributeConverter
        extends JaxbAttributeConverter.OfString<ProductDescription> {

    // creates the ProductDescription element of a value
    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    // the classes of the schemas of the collection, through their ObjectFactory classes
    private static final class ContextHolder {

        private static final JAXBContext CONTEXT;

        static {
            try {
                CONTEXT = JAXBContext.newInstance(
                        ObjectFactory.class,
                        io.github.jinahya.sqlserver.xml.schema.binding.b4347e.ObjectFactory.class
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
    public ProductDescriptionStringAttributeConverter() {
        super(ProductDescription.class);
    }

    @Override
    protected JAXBContext context() {
        return ContextHolder.CONTEXT;
    }

    @Override
    protected void write(final Marshaller marshaller, final ProductDescription attribute, final Result result)
            throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(OBJECT_FACTORY.createProductDescription(attribute), result);
    }
}

