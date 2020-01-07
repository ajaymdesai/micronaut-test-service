FROM oracle/graalvm-ce:19.3.0-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.0-java11 as graalvm # For JDK 11
COPY . /home/app/micronaut-test
WORKDIR /home/app/micronaut-test
RUN ./gradlew build
RUN gu install native-image
RUN native-image --no-server --static -cp build/libs/micronaut-test-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronaut-test/micronaut-test /app/micronaut-test
ENTRYPOINT ["/app/micronaut-test", "-Djava.library.path=/app"]
