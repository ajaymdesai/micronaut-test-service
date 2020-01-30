package com.ajaymdesai.micronaut.test;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class GreetingControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    GreetingClient client;

    String credsEncoded = "Basic " + Base64.getEncoder().encodeToString("admin:password".getBytes());

    @Test
    public void testGreeting() {
        String response = client.index(credsEncoded).blockingGet();
        assertEquals("Hello World", response);
    }

    @Test
    public void testGreetingWithName() {
        Greeting response = client.greet(credsEncoded, "Norbert").blockingGet();
        assertEquals("Norbert", response.getName());
        assertEquals("Hello", response.getGreeting());
    }

    @Test
    public void echoTextTest() {
        String response = client.echo(credsEncoded,"foo");
        assertEquals("foo", response);
    }
}
