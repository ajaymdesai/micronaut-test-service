package com.ajaymdesai.micronaut.test;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;
import java.util.UUID;

@Client("/")
public interface GreetingClient {

    @Get
    Single<String> index();

    @Get(value = "/greet/{name}")
    @Header(name="x-request-id", value = "123-456-7234")
    Single<Greeting> greet(String name);
}

