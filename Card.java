package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;

public class Card {
    private String faceName, suit;
    private int faceValue;

    public Card(String suit, String face, int value){
        this.suit = suit;
        this.faceName = face;
        this.faceValue = value;
    }

    public int getFaceValue(){ return faceValue; }
    public String getFaceName() {
        return faceName;
    }
    public String getSuit() {
        return suit;
    }
}
