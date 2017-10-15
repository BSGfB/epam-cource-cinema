package com.epam.cinema.model;

public class CounterPair {
    private String name;
    private Integer counter;

    public CounterPair() {
    }

    public CounterPair(String name, Integer counter) {
        this.name = name;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return String.format("name: %-40s counter: %d", name, counter);
    }
}
