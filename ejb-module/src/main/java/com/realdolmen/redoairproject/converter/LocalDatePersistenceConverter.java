package com.realdolmen.redoairproject.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
/**
 * Converts {@link LocalDate} to {@link java.sql.Date} and back
 * in support of JPA persistence.
 * <p>
 * The existence of this class in the classpath and it being known by the persistence unit
 * is sufficient
 * to allow you to use the as-of Java SE 8 {@link LocalDate} class in
 * an {@link javax.persistence.Entity} or in other persistable classes.
 * <p>
 * Important: the setting of <code>@Converter(autoApply = true)</code>
 * in this class will make this conversion
 * effective for all Entities that have one or more
 * persistent {@link java.time.LocalDate} properties.
 * <p>
 * The persistence provider must minimally support
 * <a href="https://jcp.org/aboutJava/communityprocess/final/jsr338/index.html">JPA 2.1</a>
 * for this to work.
 */
@Converter(autoApply = false)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return (entityValue != null) ? java.sql.Date.valueOf(entityValue) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return databaseValue.toLocalDate();
    }

}