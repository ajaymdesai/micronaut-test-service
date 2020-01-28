package com.ajaymdesai.micronaut.test;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class GreetingControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    GreetingClient client;

    @Test
    public void testGreeting() {
        String response = client.index().blockingGet();
        assertEquals("Hello World", response);
    }

    @Test
    public void testGreetingWithName() {
        Greeting response = client.greet("Norbert").blockingGet();
        assertEquals("Norbert", response.getName());
        assertEquals("Hello", response.getGreeting());
    }
}
