package com.ajaymdesai.micronaut.test;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class HomeControllerTest {

    @Inject
    EmbeddedServer server;

    @Inject
    GreetingClient client;

    String expected;

    @BeforeEach
    public void setup() throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/views", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("home");
        expected = template.apply(new Greeting());
    }

    @Test
    public void testGreeting() {
        String response = client.index().blockingGet();
        assertEquals(expected, response);
    }

}
