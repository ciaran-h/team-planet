package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

        private static List<ItineraryItem> itinerary;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Get constraint layout for node visualization
            ConstraintLayout constraintLayout = findViewById(R.id.node_layout);

            // Places and intervals times
            Place home = new Place("Home", "123 Str. NW");
            Place friends = new Place("Friends", "456 Ave. SE");
            Calendar start = new GregorianCalendar(2019, 1, 1, 10, 0, 0);
            Calendar end = new GregorianCalendar(2019, 1, 1, 10, 0, 0);

            // Fill itinerary with examples events
            itinerary = new ArrayList<>();
            itinerary.add(new DefiniteItem(home, new Interval(start)));
            end.add(Calendar.MINUTE, 45);
            itinerary.add(new Activity("Coffee", new Interval(start, end)));
            start.add(Calendar.MINUTE, 45);
            end.add(Calendar.MINUTE, 120 + 15);
            itinerary.add(new DefiniteItem(friends, new Interval(start, end)));
            start.add(Calendar.MINUTE, 120 + 15);
            end.add(Calendar.MINUTE, 120);
            itinerary.add(new DefiniteItem(home, new Interval(end)));

            // Create nodes

            int[] chainIds = new int[itinerary.size()];
            float[] weights = new float[itinerary.size()];
            ConstraintSet set = new ConstraintSet();
            for (int i = 0; i < itinerary.size(); i++) {

                FloatingActionButton button = new FloatingActionButton(this);
                button.setId(View.generateViewId());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("BUTTON CLICKED");
                    }
                });
                /*
                button.setLayoutParams(new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                ));*/
                button.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                chainIds[i] = button.getId();
                weights[i] = 0;

                constraintLayout.addView(button);
            }

            FloatingActionButton[] buttons = new FloatingActionButton[chainIds.length - 1];

            for (int i = 0; i < chainIds.length - 1; i++) {

                FloatingActionButton addButton = new FloatingActionButton(this);
                addButton.setId(View.generateViewId());
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("BUTTON CLICKED");
                    }
                });

                addButton.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                addButton.setSize(FloatingActionButton.SIZE_MINI);

                constraintLayout.addView(addButton);
                buttons[i] = addButton;
            }

            set.clone(constraintLayout);

            for (int i = 0; i < buttons.length; i++) {
                    set.connect(buttons[i].getId(), ConstraintSet.LEFT, chainIds[i], ConstraintSet.RIGHT, 5);
                    set.connect(buttons[i].getId(), ConstraintSet.TOP, chainIds[i], ConstraintSet.BOTTOM, 5);
                    set.connect(buttons[i].getId(), ConstraintSet.RIGHT, chainIds[i + 1], ConstraintSet.LEFT, 5);
                    set.connect(buttons[i].getId(), ConstraintSet.TOP, chainIds[i + 1], ConstraintSet.BOTTOM, 5);
            }

            set.createHorizontalChain(
                    constraintLayout.getId(), ConstraintSet.LEFT,
                    constraintLayout.getId(), ConstraintSet.RIGHT,
                    chainIds,
                    weights,
                    ConstraintSet.CHAIN_SPREAD);

            set.applyTo(constraintLayout);


            //startButton.setLayoutParams(constraintLayout);
            //startButton.setImageResource(android.R.drawable.ic_dialog_email);
            //startButton.setSize(FloatingActionButton.SIZE_NORMAL);
            //startButton.setBackground(getDrawable(R.drawable.node));

            /*
            View view1 = findViewById(R.id.floatingActionButton2);
            SpringAnimation anim = new SpringAnimation(view1, DynamicAnimation.TRANSLATION_X);
            SpringForce springForce = new SpringForce();
            springForce.setFinalPosition(-200f);
            springForce.setStiffness(SpringForce.STIFFNESS_LOW);
            springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);

            anim.setSpring(springForce);
            anim.start();
            */
    }
}
