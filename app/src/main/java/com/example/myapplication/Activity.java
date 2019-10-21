package com.example.myapplication;

public class Activity extends BlueprintItem {

    private String type;

    public Activity(String type, Interval interval) {
        super(interval);
        this.type = type;
    }
}
