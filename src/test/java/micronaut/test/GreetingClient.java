package micronaut.test;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/")
public interface GreetingClient {

    @Get
    public Single<String> index();

    @Get(value = "/greet/{name}")
    public Single<Greeting> greet(String name);
}

