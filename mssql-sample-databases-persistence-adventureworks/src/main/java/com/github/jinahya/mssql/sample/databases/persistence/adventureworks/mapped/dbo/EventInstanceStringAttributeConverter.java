package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.dbo;

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

import com.github.jinahya.persistence.more.converter.jakartaxmlbind.JaxbAttributeConverter;
import io.github.jinahya.sqlserver.xml.schema.binding.b872ce.EventInstance;
import io.github.jinahya.sqlserver.xml.schema.binding.b872ce.EventInstances;
import io.github.jinahya.sqlserver.xml.schema.binding.b872ce.ObjectFactory;
import jakarta.persistence.Converter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Node;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

/**
 * Converts an {@code EVENT_INSTANCE} attribute to the text of an {@code xml} column, and back.
 * <p>
 * The attribute is an instance of the class the binding of the {@code EVENTDATA()} schema generates for the event type,
 * {@code EVENTINSTANCECREATETABLE} for a {@code CREATE_TABLE}, say; {@link EventInstances#typeOf(String)} names it, and
 * every one of them is an {@link EventInstance}. The schema declares the root, {@code EVENT_INSTANCE}, of no particular
 * type, so the document is read as a DOM element, whose {@code EventType} {@link EventInstances} reads the class out
 * of, and the value is written as an {@code EVENT_INSTANCE} element of its own class, with a context of that class
 * alone; in a context which knows the declaration of the root, the element would carry an {@code xsi:type}, which
 * {@code EVENTDATA()} does not write.
 */
@Converter(autoApply = false)
public class EventInstanceStringAttributeConverter extends JaxbAttributeConverter.OfString<EventInstance> {

    // the classes of every event type, through the ObjectFactory
    private static final class ContextHolder {

        private static final JAXBContext CONTEXT;

        static {
            try {
                CONTEXT = JAXBContext.newInstance(ObjectFactory.class);
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
    public EventInstanceStringAttributeConverter() {
        super(EventInstance.class);
    }

    @Override
    protected JAXBContext context() {
        return ContextHolder.CONTEXT;
    }

    @Override
    protected void write(final Marshaller marshaller, final EventInstance attribute, final Result result)
            throws JAXBException {
        // not the marshaller of the context(), whose declaration of the root would add an xsi:type; and as the default
        // does, without the XML declaration
        final var eventMarshaller = EventInstances.context(attribute.getClass()).createMarshaller();
        eventMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        eventMarshaller.marshal(EventInstances.element(attribute), result);
    }

    @Override
    protected EventInstance read(final Unmarshaller unmarshaller, final Source source) throws JAXBException {
        return EventInstances.unmarshal((Node) JAXBIntrospector.getValue(unmarshaller.unmarshal(source)));
    }
}
