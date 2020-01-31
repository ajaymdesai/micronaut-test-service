package com.ajaymdesai.micronaut.test;

import com.ajaymdesai.micronaut.test.swagger.SwaggerConfig;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class SwaggerControllerTest {

    @Inject
    EmbeddedServer server;

    @Inject
    GreetingClient client;

    @Inject
    SwaggerConfig config;

    String expected;

    private String generateBasicAuth(final String user, final String password) {
        String s = user + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(s.getBytes());
    }

    @BeforeEach
    public void setup() throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/views/swagger", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("index");
        expected = template.apply(config);
    }

    @Test
    public void testSwaggerUI() {
        String response = client.swaggerUI(generateBasicAuth("admin", "password"));
        assertEquals(expected, response);
    }

    @Test
    public void testSwaggerUIBadAuth() {
        Assertions.assertThrows(HttpClientResponseException.class, () -> {
            client.swaggerUI(generateBasicAuth("admin", "bad"));
        });
    }

}
