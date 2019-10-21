package com.example.myapplication;

public class Event extends DefiniteItem {

    private String name;

    //TODO: Use builder here
    public Event(String name, Place place, Interval interval) {
        super(place, interval);
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
