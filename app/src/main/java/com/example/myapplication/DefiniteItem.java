package com.example.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DefiniteItem implements ItineraryItem {

    private Interval interval;
    private Place place;

    public DefiniteItem(Place place, Interval interval) {
        this.interval = interval;
        this.place = place;
    }

    public Place getPlace() {
        return this.place;
    }

    public Interval getInterval() {

        return this.interval;
    }

    public FloatingActionButton createButton(Context context) {

        FloatingActionButton button = new FloatingActionButton(context);
        button.setId(View.generateViewId());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("DEFINITE ITEM CLICKED");
            }
        });

        //TODO: Look into moving this into res
        button.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));

        return button;
    }
}
