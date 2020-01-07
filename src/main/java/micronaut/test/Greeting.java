package micronaut.test;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Greeting {

    private String name;
    private String greeting;

    public Greeting() {
        this.name = "World";
        this.greeting = "Hello";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Greeting withName(String name) {
        this.setName(name);
        return this;
    }

    public Greeting withGreeting(String greeting) {
        this.setGreeting(greeting);
        return this;
    }

    public String toString() {
        return String.format("%s %s", greeting, name);
    }
}
