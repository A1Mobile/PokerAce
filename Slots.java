package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class Slots extends DrawCards {
    protected static SeekBar betSeekBar;
    protected static Button betButton;
    protected int minBetAmount = 50;
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

    protected Card slot1;
    protected Card slot2;
    protected Card slot3;

    protected String cardHolder1;
    protected String cardHolder2;
    protected String cardHolder3;

    protected int progressHolder;
    protected int progressValue;
    protected int betAmount;
    protected int winnings;
    protected int handStrengthSlots = 0;
    protected int[] slotsArray = new int[3];
    protected Button tipButton;
    protected int bonus;
    protected String bonusHolder1;
    protected String bonusHolder2;
    protected int bonusNumber1;
    protected int bonusNumber2;
    protected int pairOdds = 1;
    protected int flushOdds = 1;
    protected int straightOdds = 2;
    protected int threeOfAKindOdds = 4;
    protected int straightFlushOdds = 5;
    protected int miniRoyalOdds = 25;
    protected int count = 0;
    protected static int maxBetAmount;
    protected static int betMultiplier;
    protected String INT = "int";

    protected void playSlots() {
        final TextView pairOddsText = (TextView) findViewById(R.id.pairOdds);
        final TextView flushOddsText = (TextView) findViewById(R.id.flushOdds);
        final TextView straightOddsText = (TextView) findViewById(R.id.straightOdds);
        final TextView threeOfAKindOddsText = (TextView) findViewById(R.id.threeOfAKindOdds);
        final TextView straightFlushOddsText = (TextView) findViewById(R.id.straightFlushOdds);
        final TextView miniRoyalOddsText = (TextView) findViewById(R.id.miniRoyalOdds);

        pairOdds = 1;
        flushOdds = 1;
        straightOdds = 2;
        threeOfAKindOdds = 4;
        straightFlushOdds = 5;
        miniRoyalOdds = 25;

        pairOddsText.setText("Pair " + pairOdds + ":6");
        flushOddsText.setText("Flush " + flushOdds + ":2");
        straightOddsText.setText("Straight " + straightOdds + ":3");
        threeOfAKindOddsText.setText("Three of a Kind " + threeOfAKindOdds + ":1");
        straightFlushOddsText.setText("Straight Flush " + straightFlushOdds + ":1");
        miniRoyalOddsText.setText("Mini Royal " + miniRoyalOdds + ":1");

        betBar();
        //tipDealer();
        quitButton();
        playerBet();
    }

    protected void playerBet() {
        final Button betButton = (Button) findViewById(R.id.bet);
        final Button autoPlay = (Button) findViewById(R.id.autoPlay);
        betButton.setClickable(true);
        autoPlay.setClickable(true);
        autoPlay.setTextColor(getResources().getColor(R.color.red));

        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                autoPlay.setClickable(false);
                startGame();
            }
        });

        autoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                autoPlay.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        autoPlay.setClickable(true);
                    }
                }, 10000);

                count = count + 1;
                autoPlay();
            }
        });
    }

    protected void autoPlay() {
        final TextView pairOddsText = (TextView) findViewById(R.id.pairOdds);
        final TextView flushOddsText = (TextView) findViewById(R.id.flushOdds);
        final TextView straightOddsText = (TextView) findViewById(R.id.straightOdds);
        final TextView threeOfAKindOddsText = (TextView) findViewById(R.id.threeOfAKindOdds);
        final TextView straightFlushOddsText = (TextView) findViewById(R.id.straightFlushOdds);
        final TextView miniRoyalOddsText = (TextView) findViewById(R.id.miniRoyalOdds);

        pairOdds = 1;
        flushOdds = 1;
        straightOdds = 2;
        threeOfAKindOdds = 4;
        straightFlushOdds = 5;
        miniRoyalOdds = 25;

        pairOddsText.setText("Pair " + pairOdds + ":6");
        flushOddsText.setText("Flush " + flushOdds + ":2");
        straightOddsText.setText("Straight " + straightOdds + ":3");
        threeOfAKindOddsText.setText("Three of a Kind " + threeOfAKindOdds + ":1");
        straightFlushOddsText.setText("Straight Flush " + straightFlushOdds + ":1");
        miniRoyalOddsText.setText("Mini Royal " + miniRoyalOdds + ":1");

        final Button autoPlay = (Button) findViewById(R.id.autoPlay);

        if (count == 1) {
            startGame();
            autoPlay.setTextColor(getResources().getColor(R.color.green));
        }
        if (count == 2) {
            count = 0;
            autoPlay.setTextColor(getResources().getColor(R.color.red));
        }
    }

    protected void startGame() {
        TextView betText = (TextView) findViewById(R.id.betText);
        final TextView bonus1Text = (TextView) findViewById(R.id.multiplier1);
        final TextView bonus2Text = (TextView) findViewById(R.id.multiplier2);
        final TextView pairOddsText = (TextView) findViewById(R.id.pairOdds);
        final TextView flushOddsText = (TextView) findViewById(R.id.flushOdds);
        final TextView straightOddsText = (TextView) findViewById(R.id.straightOdds);
        final TextView threeOfAKindOddsText = (TextView) findViewById(R.id.threeOfAKindOdds);
        final TextView straightFlushOddsText = (TextView) findViewById(R.id.straightFlushOdds);
        final TextView miniRoyalOddsText = (TextView) findViewById(R.id.miniRoyalOdds);
        TextView computerText = findViewById(R.id.computerText);
        Handler handler = new Handler();
        betAmount = progressValue;
        if (betAmount == 0) {
            betAmount = minBetAmount;
        }

        if (betAmount <= totalMoney) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 100);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 400);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 700);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 1000);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 1300);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 1600);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    draw_bonus1_slot();
                    draw_bonus2_slot();
                    bonus1Text.setText("Multiplier X" + bonusNumber1);
                    bonus2Text.setText("Multiplier X" + bonusNumber2);
                }
            }, 1900);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bonus = bonusNumber1 * bonusNumber2;
                    pairOdds = pairOdds * bonus;
                    flushOdds = flushOdds * bonus;
                    straightOdds = straightOdds * bonus;
                    threeOfAKindOdds = threeOfAKindOdds * bonus;
                    straightFlushOdds = straightFlushOdds * bonus;
                    miniRoyalOdds = miniRoyalOdds * bonus;

                    pairOddsText.setText("Pair " + pairOdds + ":6");
                    flushOddsText.setText("Flush " + flushOdds + ":2");
                    straightOddsText.setText("Straight " + straightOdds + ":3");
                    threeOfAKindOddsText.setText("Three of a Kind " + threeOfAKindOdds + ":1");
                    straightFlushOddsText.setText("Straight Flush " + straightFlushOdds + ":1");
                    miniRoyalOddsText.setText("Mini Royal " + miniRoyalOdds + ":1");
                }
            }, 1950);

            totalMoney = totalMoney - betAmount;
            final TextView totalMoneyText = findViewById(R.id.totalMoneyTextSlots);
            totalMoneyText.setText("$" + totalMoney);
            saveTotalMoney();

            betText.setText("Bet $" + betAmount);

            handler.postDelayed(new Runnable() {
                public void run() {
                    draw_first_slot();
                }
            }, 3700);

            handler.postDelayed(new Runnable() {
                public void run() {
                    draw_second_slot();
                }
            }, 4400);

            handler.postDelayed(new Runnable() {
                public void run() {
                    draw_third_slot();
                }
            }, 5100);

            handler.postDelayed(new Runnable() {
                public void run() {
                    getResultsSlots();
                }
            }, 5400);
        }
        else {
            Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TextView computerText = findViewById(R.id.computerText);
                    computerText.setTextSize(20);
                    computerText.setText("Not enough money");
                    count = 0;
                    playSlots();
                }
            }, 250);
        }
    }

    protected void getResultsSlots() {
        TextView computerText = (TextView) findViewById(R.id.computerText);
        slotsArray[0] = slot1.getFaceValue();
        slotsArray[1] = slot2.getFaceValue();
        slotsArray[2] = slot3.getFaceValue();
        Arrays.sort(slotsArray);

        if (slot1.getFaceValue() == slot2.getFaceValue() | slot1.getFaceValue() == slot3.getFaceValue() | slot2.getFaceValue() == slot3.getFaceValue()) {
            handStrengthSlots = 1;
        }

        if (slot1.getSuit() == slot2.getSuit() & slot2.getSuit() == slot3.getSuit()) {
            handStrengthSlots = 2;
        }

        if ((slotsArray[0] + 1) == slotsArray[1] & (slotsArray[1] + 1) == slotsArray[2]) {
            handStrengthSlots = 3;
        }

        if (slot1.getFaceValue() == slot2.getFaceValue() & slot2.getFaceValue() == slot3.getFaceValue()) {
            handStrengthSlots = 4;
        }

        if ((slotsArray[0] + 1) == slotsArray[1] & (slotsArray[1] + 1) == slotsArray[2] & slot1.getSuit() == slot2.getSuit() & slot2.getSuit() == slot3.getSuit()) {
            handStrengthSlots = 5;
        }

        if (handStrengthSlots == 5 & slotsArray[0] == 14 & slotsArray[1] == 13 & slotsArray[2] == 12) {
            handStrengthSlots = 6;
        }

        if (slotsArray[2] == 14) {
            slotsArray[2] = 1;
            Arrays.sort(slotsArray);
            if ((slotsArray[0] + 1) == slotsArray[1] & (slotsArray[1] + 1) == slotsArray[2] & handStrengthSlots < 3) {
                handStrengthSlots = 3;
            }
            if ((slotsArray[0] + 1) == slotsArray[1] & (slotsArray[1] + 1) == slotsArray[2] & slot1.getSuit() == slot2.getSuit() & slot2.getSuit() == slot3.getSuit() & handStrengthSlots < 5) {
                handStrengthSlots = 5;
            }
        }

        if (handStrengthSlots == 1) {
            winnings = betAmount * bonus;
            winnings = winnings/6;
        }
        if (handStrengthSlots == 2) {
            winnings = betAmount * bonus;
            winnings = winnings/2;
        }
        if (handStrengthSlots == 3) {
            winnings = betAmount * bonus;
            winnings = winnings * 2;
            winnings = winnings/3;
        }
        if (handStrengthSlots == 4) {
            winnings = betAmount * 4;
            winnings = winnings * bonus;
        }
        if (handStrengthSlots == 5) {
            winnings = betAmount * 5;
            winnings = winnings * bonus;
        }
        if (handStrengthSlots == 6) {
            winnings = betAmount * 25;
            winnings = winnings * bonus;
        }

        totalMoney = totalMoney + winnings;
        final TextView totalMoneyText = findViewById(R.id.totalMoneyTextSlots);
        totalMoneyText.setText("$" + totalMoney);
        saveTotalMoney();

        if (winnings == 0) {
            computerText.setTextSize(20);
            computerText.setText("Sorry, you lost. Play Again?");
        } else {
            if (handStrengthSlots == 1) {
                computerText.setTextSize(20);
                computerText.setText("You won $" + winnings + " with a pair!!!");
            }
            if (handStrengthSlots == 2) {
                computerText.setTextSize(20);
                computerText.setText("You won $" + winnings + " with a flush!!!");
            }
            if (handStrengthSlots == 3) {
                computerText.setTextSize(20);
                computerText.setText("You won $" + winnings + " with a straight!!!");
            }
            if (handStrengthSlots == 4) {
                computerText.setTextSize(15);
                computerText.setText("You won $" + winnings + " with a three of a kind!!!");
            }
            if (handStrengthSlots == 5) {
                computerText.setTextSize(15);
                computerText.setText("You won $" + winnings + " with a straight flush!!!");
            }
            if (handStrengthSlots == 6) {
                computerText.setTextSize(15);
                computerText.setText("You won $" + winnings + " with a mini royal!!!");
            }
        }
        if (handStrengthSlots == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    TextView betText = (TextView) findViewById(R.id.betText);
                    TextView computerText = (TextView) findViewById(R.id.computerText);
                    TextView bonus1Text = (TextView) findViewById(R.id.multiplier1);
                    TextView bonus2Text = (TextView) findViewById(R.id.multiplier2);
                    ((ImageView) findViewById(R.id.firstSlot)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.secondSlot)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.thirdSlot)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.firstBonus)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.secondBonus)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    bonus1Text.setText("Multiplier");
                    bonus2Text.setText("Multiplier");
                    betText.setText("");
                    computerText.setText("");
                    winnings = 0;
                    handStrengthSlots = 0;
                    betAmount = 0;
                    if (count == 0) {
                        playSlots();
                    }
                    else {
                        autoPlay();
                    }
                }
            }, 2000);
        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    TextView betText = (TextView) findViewById(R.id.betText);
                    TextView computerText = (TextView) findViewById(R.id.computerText);
                    TextView bonus1Text = (TextView) findViewById(R.id.multiplier1);
                    TextView bonus2Text = (TextView) findViewById(R.id.multiplier2);
                    ((ImageView) findViewById(R.id.firstSlot)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.secondSlot)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.thirdSlot)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.firstBonus)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.secondBonus)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
                    bonus1Text.setText("Multiplier");
                    bonus2Text.setText("Multiplier");
                    betText.setText("");
                    computerText.setText("");
                    winnings = 0;
                    handStrengthSlots = 0;
                    betAmount = 0;
                    if (count == 0) {
                        playSlots();
                    }
                    else {
                        autoPlay();
                    }
                }
            }, 4000);
        }
    }

    protected void draw_bonus1_slot() {
        Random bonusRandom = new Random();
        bonusNumber1 = bonusRandom.nextInt(5);
        bonusNumber1 = bonusNumber1 + 1;

        switch (bonusNumber1) {
            case 1: bonusHolder1 = "nothing"; break;
            case 2: bonusHolder1 = "bonus2"; break;
            case 3: bonusHolder1 = "bonus3"; break;
            case 4: bonusHolder1 = "bonus4"; break;
            case 5: bonusHolder1 = "bonus5"; break;
        }
        ((ImageView) findViewById(R.id.firstBonus)).setImageResource(getResources().getIdentifier(bonusHolder1,"drawable",getPackageName()));
    }

    protected void draw_bonus2_slot() {
        Random bonusRandom = new Random();
        bonusNumber2 = bonusRandom.nextInt(5);
        bonusNumber2 = bonusNumber2 + 1;

        switch (bonusNumber2) {
            case 1: bonusHolder2 = "nothing"; break;
            case 2: bonusHolder2 = "bonus2"; break;
            case 3: bonusHolder2 = "bonus3"; break;
            case 4: bonusHolder2 = "bonus4"; break;
            case 5: bonusHolder2 = "bonus5"; break;
        }
        ((ImageView) findViewById(R.id.secondBonus)).setImageResource(getResources().getIdentifier(bonusHolder2,"drawable",getPackageName()));
    }

    protected void draw_first_slot() {
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
        slot1 = new Card(cardSuit1, cardNum1, num1);
        cardHolder1 = cardSuit1 + cardNum1;
        ((ImageView) findViewById(R.id.firstSlot)).setImageResource(getResources().getIdentifier(cardHolder1,"drawable",getPackageName()));
    }

    protected void draw_second_slot() {
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
        slot2 = new Card(cardSuit2, cardNum2, num2);
        cardHolder2 = cardSuit2 + cardNum2;
        while (cardHolder2.equals(cardHolder1)){
            draw_second_slot();
        }
        ((ImageView) findViewById(R.id.secondSlot)).setImageResource(getResources().getIdentifier(cardHolder2,"drawable",getPackageName()));
    }

    protected void draw_third_slot() {
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
        slot3 = new Card(cardSuit3, cardNum3, num3);
        cardHolder3 = cardSuit3 + cardNum3;
        while (cardHolder3.equals(cardHolder1) | cardHolder3.equals(cardHolder2)){
            draw_third_slot();
        }
        ((ImageView) findViewById(R.id.thirdSlot)).setImageResource(getResources().getIdentifier(cardHolder3,"drawable",getPackageName()));

    }

    /* protected void tipDealer() {
        tipButton = (Button) findViewById(R.id.tipDealerSlots);
        final TextView computerThankYou = (TextView) findViewById(R.id.computerText);

        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computerThankYou.setTextSize(20);
                computerThankYou.setText("Thank you and good luck! -$100");
                totalMoney = totalMoney - 100;
                final TextView totalMoneyText = findViewById(R.id.totalMoneyTextSlots);
                totalMoneyText.setText("$" + totalMoney);
                saveTotalMoney();
            }
        });
    } */

    protected void quitButton() {
        Button quitButton = (Button) findViewById(R.id.quitThreeCardPoker);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    public void openMain() {
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }

    protected void betBar() {
        betSeekBar = (SeekBar) findViewById(R.id.betAmount);
        betButton = (Button) findViewById(R.id.bet);
        if (progressHolder == 0) {
            progressHolder = 50;
        }
        betButton.setText("Bet $" + progressHolder);
        betSeekBar.setMax(betMultiplier);
        betSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = ((progress + 1)*50);
                        progressHolder = ((progress + 1)*50);
                        betButton.setText("Bet $" + progressValue);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        progressValue = progressHolder;
                        betButton.setText("Bet $" + progressValue);
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        progressValue = progressHolder;
                        betButton.setText("Bet $" + progressValue);
                    }
                }
        );
    }

    public void saveTotalMoney() {
        SharedPreferences sharedPreferences = getSharedPreferences("TotalMoney", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(INT, totalMoney);
        editor.apply();
    }
}
