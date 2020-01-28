package com.ajaymdesai.micronaut.test;

import io.micronaut.core.annotation.Introspected;

@Introspected
public final class Greeting {

    private String name;
    private String greeting;

    public Greeting() {
        this.name = "World";
        this.greeting = "Hello";
    }

    public String getName() {
        return name;
    }

    public void setName(final String myName) {
        this.name = myName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(final String myGreeting) {
        this.greeting = myGreeting;
    }

    public Greeting withName(final String myName) {
        this.setName(myName);
        return this;
    }

    public Greeting withGreeting(final String myGreeting) {
        this.setGreeting(myGreeting);
        return this;
    }

    public String toString() {
        return String.format("%s %s", greeting, name);
    }
}
