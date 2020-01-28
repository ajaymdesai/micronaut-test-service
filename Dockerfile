FROM oracle/graalvm-ce:19.3.0-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.0-java11 as graalvm # For JDK 11
COPY . /home/app/com.ajaymdesai.micronaut-test
WORKDIR /home/app/com.ajaymdesai.micronaut-test
RUN ./gradlew build
RUN gu install native-image
RUN native-image --no-server --static -cp build/libs/com.ajaymdesai.micronaut-test-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/com.ajaymdesai.micronaut-test/com.ajaymdesai.micronaut-test /app/com.ajaymdesai.micronaut-test
ENTRYPOINT ["/app/com.ajaymdesai.micronaut-test", "-Djava.library.path=/app"]
