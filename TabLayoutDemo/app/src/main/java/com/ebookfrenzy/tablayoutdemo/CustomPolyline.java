package com.ebookfrenzy.tablayoutdemo;

import android.media.Image;
import android.widget.ImageView;

import com.google.android.gms.maps.model.Polyline;

public class CustomPolyline {
    Polyline polyline;
    String name, length;
    ImageView walk, bike, dog, ski;

    public CustomPolyline(Polyline polyline, String name, String length){
        this.polyline = polyline;
        this.name = name;
        this.length = length;
        walk.setImageResource(R.drawable.hiking);
        bike.setImageResource(R.drawable.bicycle);
        dog.setImageResource(R.drawable.dog);
        ski.setImageResource(R.drawable.play);
    }


    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Length: " + length + '\n';
    }
}
