package com.test;

import com.breakeven.backend.model.Initiative;
import com.breakeven.backend.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitTests {
    @Test
    public void testTripName() {
        Initiative initiative = new Initiative("Sotravel Trip", LocalDateTime.now(), LocalDateTime.MAX);
        String initiativeName = initiative.getName();
        assertEquals("Sotravel Trip", initiativeName, "The initiative name should be 'Sotravel Trip'.");
    }

    @Test
    public void testPersonName() {
        Initiative initiative = new Initiative("Sotravel Trip", LocalDateTime.now(), LocalDateTime.MAX);
        Person person = new Person(initiative, "John Doe");
        String personName = person.getName();
        assertEquals("John Doe", personName, "The person name should be 'John Doe'.");
    }
}
