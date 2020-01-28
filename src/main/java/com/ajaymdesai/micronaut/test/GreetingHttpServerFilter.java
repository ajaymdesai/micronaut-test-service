package com.ajaymdesai.micronaut.test;

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

@Filter("/v1/greet/**")
public class GreetingHttpServerFilter implements HttpServerFilter {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingHttpServerFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        if (LOG.isInfoEnabled()) {
            LOG.info("Tracing request: " + request.getHeaders().findFirst("x-request-id").orElse("foo"));
            LOG.info(Long.toString(Thread.currentThread().getId()));
        }
        return chain.proceed(request);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
