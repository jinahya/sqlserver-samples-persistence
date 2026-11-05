package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedOrder;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.Objects;

/**
 * An abstract base class for testing, without a persistence context, an entity class.
 * <p>
 * Its checks are, for now, the ones of {@link __Object_Test}, which {@link _NonEntity_Test} runs
 * too; checks only an entity has to pass go here.
 *
 * @param <T> the type of the entity.
 * @param <U> the type of the identifier of the entity: its id class, the type of its
 *            {@code @EmbeddedId}, or the type of its {@code @Id}.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
public abstract class _Entity_Test<T, U>
        extends __Test<T>
        implements __Object_Test<T> {

    /**
     * Creates a new instance for the specified entity class.
     *
     * @param targetClass the entity class to test.
     * @param idClass     the class of the identifier of the entity: its id class, the type of its
     *                    {@code @EmbeddedId}, or the type of its {@code @Id}.
     */
    protected _Entity_Test(final Class<T> targetClass, final Class<U> idClass) {
        super(targetClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * Returns a new instance of the specified entity class, whose key field, declared by the
     * specified class, is set to the specified value, as the provider sets it on a load.
     */
    private static <E> E withKey(final Class<E> entityClass, final Class<? super E> declaringClass,
                                 final String keyField, final Object key) {
        final var instance = ReflectionUtils.newInstance(entityClass);
        try {
            final var field = declaringClass.getDeclaredField(keyField);
            field.setAccessible(true);
            field.set(instance, key);
        } catch (final ReflectiveOperationException roe) {
            throw new AssertionError("failed to set " + keyField + " of " + entityClass, roe);
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @implNote Every table references {@code Application.People} with its {@code LastEditedBy},
     *         {@code Sales.Customers} references itself with its {@code BillToCustomerID}, and
     *         {@code Sales.Orders} with its {@code BackorderOrderID}, so EqualsVerifier, which
     *         follows the associations, is given two of each, which differ by their keys.
     */
    @Override
    public SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return __Object_Test.super.equals_verifier_()
                .withPrefabValues(Person.class,
                                  withKey(Person.class, MappedPerson.class, "personId", 1),
                                  withKey(Person.class, MappedPerson.class, "personId", 2))
                .withPrefabValues(Customer.class,
                                  withKey(Customer.class, MappedCustomer.class, "customerId", 1),
                                  withKey(Customer.class, MappedCustomer.class, "customerId", 2))
                .withPrefabValues(Order.class,
                                  withKey(Order.class, MappedOrder.class, "orderId", 1),
                                  withKey(Order.class, MappedOrder.class, "orderId", 2));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The class of the identifier of {@link #targetClass}: its id class, the type of its
     * {@code @EmbeddedId}, or the type of its {@code @Id}.
     */
    protected final Class<U> idClass;
}
