package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DrawCards extends AppCompatActivity {
    protected int suit1;
    protected int num1;
    protected String cardSuit1;
    protected String cardNum1;
    protected int suit2;
    protected int num2;
    protected String cardSuit2;
    protected String cardNum2;
    protected int suit3;
    protected int num3;
    protected String cardSuit3;
    protected String cardNum3;
    protected int suit4;
    protected int num4;
    protected String cardSuit4;
    protected String cardNum4;
    protected int suit5;
    protected int num5;
    protected String cardSuit5;
    protected String cardNum5;
    protected int suit6;
    protected int num6;
    protected String cardSuit6;
    protected String cardNum6;
    protected int suit7;
    protected int num7;
    protected String cardSuit7;
    protected String cardNum7;
    protected int suit8;
    protected int num8;
    protected String cardSuit8;
    protected String cardNum8;
    protected int suit9;
    protected int num9;
    protected String cardSuit9;
    protected String cardNum9;
    protected int whoseBlind = 0;

    protected Card card1;
    protected Card card2;
    protected Card card3;
    protected Card card4;
    protected Card card5;
    protected Card card6;
    protected Card card7;
    protected Card card8;
    protected Card card9;

    protected String cardHolder1;
    protected String cardHolder2;
    protected String cardHolder3;
    protected String cardHolder4;
    protected String cardHolder5;
    protected String cardHolder6;
    protected String cardHolder7;
    protected String cardHolder8;
    protected String cardHolder9;

    protected static int totalMoney;

    protected void draw_first_card() {
        //Player's First Card
        Random s1 = new Random();
        Random n1 = new Random();
        suit1 = s1.nextInt(4);
        num1 = n1.nextInt(13);
        num1 = num1 + 2;
        if (suit1 == 0) {
            cardSuit1 = "s";
        }
        if (suit1 == 1) {
            cardSuit1 = "h";
        }
        if (suit1 == 2) {
            cardSuit1 = "c";
        }
        if (suit1 == 3) {
            cardSuit1 = "d";
        }
        if (num1 == 2) {
            cardNum1 = "2";
        }
        if (num1 == 3) {
            cardNum1 = "3";
        }
        if (num1 == 4) {
            cardNum1 = "4";
        }
        if (num1 == 5) {
            cardNum1 = "5";
        }
        if (num1 == 6) {
            cardNum1 = "6";
        }
        if (num1 == 7) {
            cardNum1 = "7";
        }
        if (num1 == 8) {
            cardNum1 = "8";
        }
        if (num1 == 9) {
            cardNum1 = "9";
        }
        if (num1 == 10) {
            cardNum1 = "10";
        }
        if (num1 == 11) {
            cardNum1 = "j";
        }
        if (num1 == 12) {
            cardNum1 = "q";
        }
        if (num1 == 13) {
            cardNum1 = "k";
        }
        if (num1 == 14) {
            cardNum1 = "a";
        }
        card1 = new Card(cardSuit1, cardNum1, num1);
        cardHolder1 = cardSuit1 + cardNum1;
        ((ImageView) findViewById(R.id.playerCard2)).setImageResource(getResources().getIdentifier(cardHolder1,"drawable",getPackageName()));
        //ImageView flopCard1 = (ImageView) (findViewById(R.id.flop1));
        //flopCard1.setImageResource(R.drawable.getIdentifier(cardHolder1,"drawable",getPackageName()));
    }

    protected void draw_second_card() {
        //Player's Second Card
        Random s2 = new Random();
        Random n2 = new Random();
        suit2 = s2.nextInt(4);
        num2 = n2.nextInt(13);
        num2 = num2 + 2;
        if (suit2 == 0) {
            cardSuit2 = "s";
        }
        if (suit2 == 1) {
            cardSuit2 = "h";
        }
        if (suit2 == 2) {
            cardSuit2 = "c";
        }
        if (suit2 == 3) {
            cardSuit2 = "d";
        }
        if (num2 == 2) {
            cardNum2 = "2";
        }
        if (num2 == 3) {
            cardNum2 = "3";
        }
        if (num2 == 4) {
            cardNum2 = "4";
        }
        if (num2 == 5) {
            cardNum2 = "5";
        }
        if (num2 == 6) {
            cardNum2 = "6";
        }
        if (num2 == 7) {
            cardNum2 = "7";
        }
        if (num2 == 8) {
            cardNum2 = "8";
        }
        if (num2 == 9) {
            cardNum2 = "9";
        }
        if (num2 == 10) {
            cardNum2 = "10";
        }
        if (num2 == 11) {
            cardNum2 = "j";
        }
        if (num2 == 12) {
            cardNum2 = "q";
        }
        if (num2 == 13) {
            cardNum2 = "k";
        }
        if (num2 == 14) {
            cardNum2 = "a";
        }
        card2 = new Card(cardSuit2, cardNum2, num2);
        cardHolder2 = cardSuit2 + cardNum2;
        if (cardHolder2.equals(cardHolder1)){
            draw_second_card();
        }
        ((ImageView) findViewById(R.id.playerCard1)).setImageResource(getResources().getIdentifier(cardHolder2,"drawable",getPackageName()));
    }

    protected void draw_third_card() {
        //Computer's First Card
        Random s3 = new Random();
        Random n3 = new Random();
        suit3 = s3.nextInt(4);
        num3 = n3.nextInt(13);
        num3 = num3 + 2;
        if (suit3 == 0) {
            cardSuit3 = "s";
        }
        if (suit3 == 1) {
            cardSuit3 = "h";
        }
        if (suit3 == 2) {
            cardSuit3 = "c";
        }
        if (suit3 == 3) {
            cardSuit3 = "d";
        }
        if (num3 == 2) {
            cardNum3 = "2";
        }
        if (num3 == 3) {
            cardNum3 = "3";
        }
        if (num3 == 4) {
            cardNum3 = "4";
        }
        if (num3 == 5) {
            cardNum3 = "5";
        }
        if (num3 == 6) {
            cardNum3 = "6";
        }
        if (num3 == 7) {
            cardNum3 = "7";
        }
        if (num3 == 8) {
            cardNum3 = "8";
        }
        if (num3 == 9) {
            cardNum3 = "9";
        }
        if (num3 == 10) {
            cardNum3 = "10";
        }
        if (num3 == 11) {
            cardNum3 = "j";
        }
        if (num3 == 12) {
            cardNum3 = "q";
        }
        if (num3 == 13) {
            cardNum3 = "k";
        }
        if (num3 == 14) {
            cardNum3 = "a";
        }
        card3 = new Card(cardSuit3, cardNum3, num3);
        cardHolder3 = cardSuit3 + cardNum3;
        if (cardHolder3.equals(cardHolder1) | cardHolder3.equals(cardHolder2)){
            draw_third_card();
        }
    }

    protected void draw_fourth_card() {
        //Computer's Second Card
        Random s4 = new Random();
        Random n4 = new Random();
        suit4 = s4.nextInt(4);
        num4 = n4.nextInt(13);
        num4 = num4 + 2;
        if (suit4 == 0) {
            cardSuit4 = "s";
        }
        if (suit4 == 1) {
            cardSuit4 = "h";
        }
        if (suit4 == 2) {
            cardSuit4 = "c";
        }
        if (suit4 == 3) {
            cardSuit4 = "d";
        }
        if (num4 == 2) {
            cardNum4 = "2";
        }
        if (num4 == 3) {
            cardNum4 = "3";
        }
        if (num4 == 4) {
            cardNum4 = "4";
        }
        if (num4 == 5) {
            cardNum4 = "5";
        }
        if (num4 == 6) {
            cardNum4 = "6";
        }
        if (num4 == 7) {
            cardNum4 = "7";
        }
        if (num4 == 8) {
            cardNum4 = "8";
        }
        if (num4 == 9) {
            cardNum4 = "9";
        }
        if (num4 == 10) {
            cardNum4 = "10";
        }
        if (num4 == 11) {
            cardNum4 = "j";
        }
        if (num4 == 12) {
            cardNum4 = "q";
        }
        if (num4 == 13) {
            cardNum4 = "k";
        }
        if (num4 == 14) {
            cardNum4 = "a";
        }
        card4 = new Card(cardSuit4, cardNum4, num4);
        cardHolder4 = cardSuit4 + cardNum4;
        if (cardHolder4.equals(cardHolder1) | cardHolder4.equals(cardHolder2) | cardHolder4.equals(cardHolder3)){
            draw_fourth_card();
        }
    }

    protected void draw_fifth_card() {
        //Flop's First Card
        Random s5 = new Random();
        Random n5 = new Random();
        suit5 = s5.nextInt(4);
        num5 = n5.nextInt(13);
        num5 = num5 + 2;
        if (suit5 == 0) {
            cardSuit5 = "s";
        }
        if (suit5 == 1) {
            cardSuit5 = "h";
        }
        if (suit5 == 2) {
            cardSuit5 = "c";
        }
        if (suit5 == 3) {
            cardSuit5 = "d";
        }
        if (num5 == 2) {
            cardNum5 = "2";
        }
        if (num5 == 3) {
            cardNum5 = "3";
        }
        if (num5 == 4) {
            cardNum5 = "4";
        }
        if (num5 == 5) {
            cardNum5 = "5";
        }
        if (num5 == 6) {
            cardNum5 = "6";
        }
        if (num5 == 7) {
            cardNum5 = "7";
        }
        if (num5 == 8) {
            cardNum5 = "8";
        }
        if (num5 == 9) {
            cardNum5 = "9";
        }
        if (num5 == 10) {
            cardNum5 = "10";
        }
        if (num5 == 11) {
            cardNum5 = "j";
        }
        if (num5 == 12) {
            cardNum5 = "q";
        }
        if (num5 == 13) {
            cardNum5 = "k";
        }
        if (num5 == 14) {
            cardNum5 = "a";
        }
        card5 = new Card(cardSuit5, cardNum5, num5);
        cardHolder5 = cardSuit5 + cardNum5;
        if (cardHolder5.equals(cardHolder1) | cardHolder5.equals(cardHolder2) | cardHolder5.equals(cardHolder3) | cardHolder5.equals(cardHolder4)){
            draw_fifth_card();
        }
        //((ImageView) findViewById(R.id.flop1)).setImageResource(getResources().getIdentifier(cardHolder5,"drawable",getPackageName()));
    }

    protected void draw_sixth_card() {
        //Flop's Second Card
        Random s6 = new Random();
        Random n6 = new Random();
        suit6 = s6.nextInt(4);
        num6 = n6.nextInt(13);
        num6 = num6 + 2;
        if (suit6 == 0) {
            cardSuit6 = "s";
        }
        if (suit6 == 1) {
            cardSuit6 = "h";
        }
        if (suit6 == 2) {
            cardSuit6 = "c";
        }
        if (suit6 == 3) {
            cardSuit6 = "d";
        }
        if (num6 == 2) {
            cardNum6 = "2";
        }
        if (num6 == 3) {
            cardNum6 = "3";
        }
        if (num6 == 4) {
            cardNum6 = "4";
        }
        if (num6 == 5) {
            cardNum6 = "5";
        }
        if (num6 == 6) {
            cardNum6 = "6";
        }
        if (num6 == 7) {
            cardNum6 = "7";
        }
        if (num6 == 8) {
            cardNum6 = "8";
        }
        if (num6 == 9) {
            cardNum6 = "9";
        }
        if (num6 == 10) {
            cardNum6 = "10";
        }
        if (num6 == 11) {
            cardNum6 = "j";
        }
        if (num6 == 12) {
            cardNum6 = "q";
        }
        if (num6 == 13) {
            cardNum6 = "k";
        }
        if (num6 == 14) {
            cardNum6 = "a";
        }
        card6 = new Card(cardSuit6, cardNum6, num6);
        cardHolder6 = cardSuit6 + cardNum6;
        if (cardHolder6.equals(cardHolder5) | cardHolder6.equals(cardHolder1) | cardHolder6.equals(cardHolder2) | cardHolder6.equals(cardHolder3) | cardHolder6.equals(cardHolder4)){
            draw_sixth_card();
        }
        //((ImageView) findViewById(R.id.flop2)).setImageResource(getResources().getIdentifier(cardHolder6,"drawable",getPackageName()));
    }

    protected void draw_seventh_card() {
        //Flop's Third Card
        Random s7 = new Random();
        Random n7 = new Random();
        suit7 = s7.nextInt(4);
        num7 = n7.nextInt(13);
        num7 = num7 + 2;
        if (suit7 == 0) {
            cardSuit7 = "s";
        }
        if (suit7 == 1) {
            cardSuit7 = "h";
        }
        if (suit7 == 2) {
            cardSuit7 = "c";
        }
        if (suit7 == 3) {
            cardSuit7 = "d";
        }
        if (num7 == 2) {
            cardNum7 = "2";
        }
        if (num7 == 3) {
            cardNum7 = "3";
        }
        if (num7 == 4) {
            cardNum7 = "4";
        }
        if (num7 == 5) {
            cardNum7 = "5";
        }
        if (num7 == 6) {
            cardNum7 = "6";
        }
        if (num7 == 7) {
            cardNum7 = "7";
        }
        if (num7 == 8) {
            cardNum7 = "8";
        }
        if (num7 == 9) {
            cardNum7 = "9";
        }
        if (num7 == 10) {
            cardNum7 = "10";
        }
        if (num7 == 11) {
            cardNum7 = "j";
        }
        if (num7 == 12) {
            cardNum7 = "q";
        }
        if (num7 == 13) {
            cardNum7 = "k";
        }
        if (num7 == 14) {
            cardNum7 = "a";
        }
        card7 = new Card(cardSuit7, cardNum7, num7);
        cardHolder7 = cardSuit7 + cardNum7;
        if (cardHolder7.equals(cardHolder6) | cardHolder7.equals(cardHolder5) | cardHolder7.equals(cardHolder1) | cardHolder7.equals(cardHolder2) | cardHolder7.equals(cardHolder3) | cardHolder7.equals(cardHolder4)){
            draw_seventh_card();
        }
        //((ImageView) findViewById(R.id.flop3)).setImageResource(getResources().getIdentifier(cardHolder7,"drawable",getPackageName()));
    }

    protected void draw_eighth_card() {
        //Turn's Card
        Random s8 = new Random();
        Random n8 = new Random();
        suit8 = s8.nextInt(4);
        num8 = n8.nextInt(13);
        num8 = num8 + 2;
        if (suit8 == 0) {
            cardSuit8 = "s";
        }
        if (suit8 == 1) {
            cardSuit8 = "h";
        }
        if (suit8 == 2) {
            cardSuit8 = "c";
        }
        if (suit8 == 3) {
            cardSuit8 = "d";
        }
        if (num8 == 2) {
            cardNum8 = "2";
        }
        if (num8 == 3) {
            cardNum8 = "3";
        }
        if (num8 == 4) {
            cardNum8 = "4";
        }
        if (num8 == 5) {
            cardNum8 = "5";
        }
        if (num8 == 6) {
            cardNum8 = "6";
        }
        if (num8 == 7) {
            cardNum8 = "7";
        }
        if (num8 == 8) {
            cardNum8 = "8";
        }
        if (num8 == 9) {
            cardNum8 = "9";
        }
        if (num8 == 10) {
            cardNum8 = "10";
        }
        if (num8 == 11) {
            cardNum8 = "j";
        }
        if (num8 == 12) {
            cardNum8 = "q";
        }
        if (num8 == 13) {
            cardNum8 = "k";
        }
        if (num8 == 14) {
            cardNum8 = "a";
        }
        card8 = new Card(cardSuit8, cardNum8, num8);
        cardHolder8 = cardSuit8 + cardNum8;
        if (cardHolder8.equals(cardHolder7) | cardHolder8.equals(cardHolder6) | cardHolder8.equals(cardHolder5) | cardHolder8.equals(cardHolder1) | cardHolder8.equals(cardHolder2) | cardHolder8.equals(cardHolder3) | cardHolder8.equals(cardHolder4)){
            draw_eighth_card();
        }
        //((ImageView) findViewById(R.id.turn)).setImageResource(getResources().getIdentifier(cardHolder8,"drawable",getPackageName()));
    }

    protected void draw_ninth_card() {
        //Flop's First Card
        Random s9 = new Random();
        Random n9 = new Random();
        suit9 = s9.nextInt(4);
        num9 = n9.nextInt(13);
        num9 = num9 + 2;
        if (suit9 == 0) {
            cardSuit9 = "s";
        }
        if (suit9 == 1) {
            cardSuit9 = "h";
        }
        if (suit9 == 2) {
            cardSuit9 = "c";
        }
        if (suit9 == 3) {
            cardSuit9 = "d";
        }
        if (num9 == 2) {
            cardNum9 = "2";
        }
        if (num9 == 3) {
            cardNum9 = "3";
        }
        if (num9 == 4) {
            cardNum9 = "4";
        }
        if (num9 == 5) {
            cardNum9 = "5";
        }
        if (num9 == 6) {
            cardNum9 = "6";
        }
        if (num9 == 7) {
            cardNum9 = "7";
        }
        if (num9 == 8) {
            cardNum9 = "8";
        }
        if (num9 == 9) {
            cardNum9 = "9";
        }
        if (num9 == 10) {
            cardNum9 = "10";
        }
        if (num9 == 11) {
            cardNum9 = "j";
        }
        if (num9 == 12) {
            cardNum9 = "q";
        }
        if (num9 == 13) {
            cardNum9 = "k";
        }
        if (num9 == 14) {
            cardNum9 = "a";
        }
        card9 = new Card(cardSuit9, cardNum9, num9);
        cardHolder9 = cardSuit9 + cardNum9;
        if (cardHolder9.equals(cardHolder8) | cardHolder9.equals(cardHolder7) | cardHolder9.equals(cardHolder6) | cardHolder9.equals(cardHolder5) | cardHolder9.equals(cardHolder1) | cardHolder9.equals(cardHolder2) | cardHolder9.equals(cardHolder3) | cardHolder9.equals(cardHolder4)){
            draw_ninth_card();
        }
        //((ImageView) findViewById(R.id.river)).setImageResource(getResources().getIdentifier(cardHolder9,"drawable",getPackageName()));
    }
}