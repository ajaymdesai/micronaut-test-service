package com.ajaymdesai.micronaut.test;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterChain;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Filter("/greet/**")
public class GreetingHttpServerFilter implements HttpServerFilter {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingHttpServerFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        if (LOG.isInfoEnabled()) {
            HttpHeaders h = request.getHeaders();
            Map<String, List<String>> m = h.asMap();
            for (Map.Entry<String, List<String>> entry: m.entrySet()) {
                LOG.info("Header -> " + entry.getKey() + ": "
                    + entry.getValue().stream().reduce((acc, item) -> acc + " " + item).get());
            }
        }
        return chain.proceed(request);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
