package com.ajaymdesai.micronaut.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTest {

    @BeforeEach
    public void setUp() {
    }

    public void tearDown() {
    }

    @Test
    public void testSetName() {
        Greeting g = new Greeting();
        g.setName("foo");
        assertEquals("foo", g.getName());
    }

    @Test
    public void testSetGreeting() {
        Greeting g = new Greeting();
        g.setGreeting("Hola");
        assertEquals("Hola", g.getGreeting());
    }

    @Test
    public void testWithName() {
        Greeting g = new Greeting();
        assertEquals("foo", g.withName("foo").getName());
    }

    @Test
    public void testWithGreeting() {
        Greeting g = new Greeting();
        assertEquals("foo", g.withGreeting("foo").getGreeting());
    }
}
