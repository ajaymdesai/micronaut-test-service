package com.ajaymdesai.micronaut.test.swagger;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.Collections.singletonList;

@Hidden
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api")
@Validated
public class SwaggerController {

    @Inject
    SwaggerConfig config;

    @View("swagger/index")
    @Get
    @Produces(MediaType.TEXT_HTML)
    public SwaggerConfig index() {
        LoggerFactory.getLogger(SwaggerController.class).info("Trying to render swagger-view");
        return config;
    }

    @View("swagger/index")
    @Get("/{url}")
    @Produces(MediaType.TEXT_HTML)
    public SwaggerConfig renderSpec(@NotNull String url) {
        return new SwaggerConfig.Builder()
                .withDeepLinking(config.isDeepLinking())
                .withLayout(config.getLayout())
                .withVersion(config.getVersion())
                .withUrls(singletonList(new SwaggerConfig.URIConfig.Builder()
                        .withName(url)
                        .withURI(url)
                        .build()))
                .build();
    }

    @View("swagger/index")
    @Post
    @Produces(MediaType.TEXT_HTML)
    public SwaggerConfig renderSpecs(@Body @NotEmpty List<SwaggerConfig.URIConfig> urls) {
        return new SwaggerConfig.Builder()
                .withDeepLinking(config.isDeepLinking())
                .withLayout(config.getLayout())
                .withVersion(config.getVersion())
                .withUrls(urls)
                .build();
    }

}
