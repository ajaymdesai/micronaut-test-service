package com.ajaymdesai.micronaut.test;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class GreetingControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    GreetingClient client;

    @Test
    public void testGreeting() {
        String response = client.greet().blockingGet();
        assertEquals("Hello World", response);
    }

    @Test
    public void testGreetingWithName() {
        Greeting response = client.greet(UUID.randomUUID().toString(),"Norbert").blockingGet();
        assertEquals("Norbert", response.getName());
        assertEquals("Hello", response.getGreeting());
    }

    @Test
    public void echoTextTest() {
        String response = client.echo("foo").blockingGet();
        assertEquals("foo", response);
    }

    @Test
    public void echoStreamTest() {
        String response = client.echoStream("foo").blockingGet();
        assertEquals("foo", response);
    }

    @Test
    public void echoJsonTest() {
        Greeting response = client.echoJson(new Greeting().withGreeting("Yo")).blockingGet();
        assertEquals("Yo", response.getGreeting());
    }
}
