package com.example.myapplication;

import android.content.Context;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public interface ItineraryItem {

    Interval getInterval();

    FloatingActionButton createButton(Context context);
}
