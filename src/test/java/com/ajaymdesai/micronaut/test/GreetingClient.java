package com.ajaymdesai.micronaut.test;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/")
public interface GreetingClient {

    @Get
    Single<String> index();

    @Get("/greet")
    Single<String> greet();

    @Get(value = "/greet/{name}")
    Single<Greeting> greet(@Header("x-request-id") String requestId, String name);

    @Post(value = "/greet/echo", processes = MediaType.TEXT_PLAIN)
    public Single<String> echo(@Body final String text);

    @Post(value = "/greet/echo-stream", processes = MediaType.APPLICATION_OCTET_STREAM)
    public Single<String> echoStream(@Body final String buffer);

    @Post(value = "/greet/echo-json", processes = MediaType.APPLICATION_JSON)
    public Single<Greeting> echoJson(@Body final Greeting greet);

    @Get("/api")
    public String swaggerUI(@Header("Authorization") final String authorization);
}

