package com.ajaymdesai.micronaut.test;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "com.ajaymdesai.micronaut-test",
        version = "1.0"
    )
)
public final class Application {
    public static void main(final String[] args) {
        Micronaut.run(Application.class, args);
    }
}
