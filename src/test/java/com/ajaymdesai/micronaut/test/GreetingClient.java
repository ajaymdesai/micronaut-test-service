package com.ajaymdesai.micronaut.test;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.validation.constraints.Size;
import java.util.UUID;

@Client("/greet")
public interface GreetingClient {

    @Get
    Single<String> index(@Header("Authorization") String authorization);

    @Get(value = "/{name}")
    @Header(name="x-request-id", value = "123-456-7234")
    Single<Greeting> greet(@Header("Authorization") String authorization, String name);

    @Post(value = "/echo", processes = MediaType.TEXT_PLAIN)
    public String echo(@Header("Authorization") String authorization, @Body final String text);
}

