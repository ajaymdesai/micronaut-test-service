package micronaut.test;

import com.google.common.base.Strings;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@Controller(value = "/", produces = MediaType.APPLICATION_JSON)
public class GreetingController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public Single<String> index() {
        return Single.just(new Greeting().toString());
    }

    @Get(value = "/greet/{name}")
    public Single<Greeting> greet(@NotNull String name) {
        return Single.just(new Greeting().withName(name));
    }

    @Post(value = "/echo", consumes = MediaType.TEXT_PLAIN)
    public String echo(@Size(max = 1024) @Body String text) {
        return text;
    }

    @Post(value = "/echo-stream", consumes = MediaType.APPLICATION_JSON)
    public Single<MutableHttpResponse<String>> echoFlow(@Body Flowable<String> text) {
        return text.collect(StringBuffer::new, StringBuffer::append).map((buffer) -> HttpResponse.ok(buffer.toString()));
    }

    @Post(value = "/echo-json", consumes = MediaType.APPLICATION_JSON)
    public Single<Greeting> echoJson(@Body Greeting greeting) {
        return Single.just(greeting);
    }
}
