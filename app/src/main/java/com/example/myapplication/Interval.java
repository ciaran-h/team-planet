package com.example.myapplication;

import java.util.Calendar;

public class Interval {

    private Calendar start, end;
    private long duration;

    public Interval(Calendar start) {

        this.start = start;
        this.duration = 0;
    }

    public Interval(Calendar start, Calendar end) {

        this.start = start;
        this.end = end;

        if (end != null)
            this.duration = end.getTimeInMillis() - start.getTimeInMillis();
        else
            this.duration = 0;
    }
}
