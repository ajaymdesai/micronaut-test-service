package com.ajaymdesai.micronaut.test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.context.ServerRequestContext;
import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;


@Validated
@Controller(value = "/", produces = MediaType.APPLICATION_JSON)
public class GreetingController {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

    @Get(produces = MediaType.TEXT_PLAIN)
    public Single<String> index() {
        return Single.just(new Greeting().toString());
    }

    @Get(value = "/greet/{name}")
    public Single<Greeting> greet(@NotNull final String name) {
        Optional<HttpRequest<Object>> request = ServerRequestContext.currentRequest();
        if (LOG.isInfoEnabled()) {
            if (request.isPresent()) {
                LOG.info("Tracing request: " +
                    request.get().getHeaders().findFirst("x-request-id").orElse("foo"));
            }
            LOG.info(Long.toString(Thread.currentThread().getId()));
        }
        return Single.just(new Greeting().withName(name));
    }

    @Post(value = "/echo", consumes = MediaType.TEXT_PLAIN)
    public String echo(@Size(max = 1024) @Body final String text) {
        return text;
    }

    @Post(value = "/echo-stream", consumes = MediaType.APPLICATION_OCTET_STREAM)
    public Single<MutableHttpResponse<String>> echoFlow(
        @Body final Flowable<String> text) {
        return text.collect(
            StringBuffer::new,
            StringBuffer::append).map(
            (buffer) -> HttpResponse.ok(buffer.toString()
            )
        );
    }

    @Post(value = "/echo-json", consumes = MediaType.APPLICATION_JSON)
    public Single<Greeting> echoJson(@Body final Greeting greeting) {
        return Single.just(greeting);
    }
}
