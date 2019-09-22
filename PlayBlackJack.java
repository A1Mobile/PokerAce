package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

//set a get result at the very end of computer action
//before computer action for check if the player hands are all busted and check if the player split by using the counters and seeing if they are equal to zero

public class PlayBlackJack extends DrawCards {
    protected SeekBar betSeekBar;
    protected int betMultiplier;
    protected int progressHolder;
    protected int progressValue;
    protected int maxBetAmount;
    protected Button tipButton;
    protected Button betButton;
    protected int betAmount = 0;
    protected Card[] cardHolderBJ = new Card[15];
    protected int cardCounter = 0;
    protected int insuranceAmount = 0;
    protected String insuranceCheck = "True";
    protected int rightCount = 0;
    protected int rightCountAce = 0;
    protected int leftCount = 0;
    protected int leftCountAce = 0;
    protected int computerCount = 0;
    protected int computerCountAce = 0;
    protected int winnings = 0;
    protected String blackjack = "False";
    protected int doubleAmount = 0;
    protected int aceCounter = 0;
    protected String computerSideBonus = "False";
    protected String rightSideBonus = "False";
    protected String leftSideBonus = "False";
    protected String doubleLeftSide = "False";
    protected String doubleRightSide = "False";
    protected String INT = "int";

    //right side
    protected String cardHolderBJ1 = "";
    protected String cardHolderBJ2 = "";
    protected String cardHolderBJ3 = "";
    protected String cardHolderBJ4 = "";
    protected String cardHolderBJ5 = "";

    //left side
    protected String cardHolderBJ0 = "";
    protected String cardHolderBJ6 = "";
    protected String cardHolderBJ7 = "";
    protected String cardHolderBJ8 = "";
    protected String cardHolderBJ9 = "";

    //Computer's hand
    protected String cardHolderBJ10 = "";
    protected String cardHolderBJ11 = "";
    protected String cardHolderBJ12 = "";
    protected String cardHolderBJ13 = "";
    protected String cardHolderBJ14 = "";


    protected void playBlackjack() {
        betBar();
        quitButton();
        //tipDealer();
        playerBet();
    }

    protected void playerBet() {
        final Button betButton = (Button) findViewById(R.id.betButton);
        final Button split = findViewById(R.id.splitButton);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);
        final TextView dealerText = findViewById(R.id.dealerText);

        betButton.setTextColor(getResources().getColor(R.color.green));
        betButton.setVisibility(View.VISIBLE);
        split.setClickable(false);
        hit.setClickable(false);
        stand.setClickable(false);
        betButton.setClickable(true);

        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAmount = progressValue;

                if (betAmount > totalMoney) {
                    dealerText.bringToFront();
                    dealerText.setText("Not enough money");
                }
                else {
                    betButton.setClickable(false);
                    betSeekBar.setClickable(false);
                    betSeekBar.setVisibility(View.INVISIBLE);

                    startGame();
                }
            }
        });
    }

    protected void startGame() {
        TextView betTextBlackjack = findViewById(R.id.betText);
        TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView dealerText = findViewById(R.id.dealerText);

        betAmount = progressValue;
        if (betAmount == 0) {
            betAmount = 50;
        }
        if (betAmount <= totalMoney) {
            totalMoney = totalMoney - betAmount;
            betTextBlackjack.setText("Bet $" + betAmount);
            totalMoneyTextBlackjack.setText("$" + totalMoney);
            saveTotalMoney();

            fillCards();
            ((ImageView) findViewById(R.id.playerCardLeft1)).setImageResource(getResources().getIdentifier(cardHolderBJ0, "drawable", getPackageName()));
            ((ImageView) findViewById(R.id.playerCardLeft2)).setImageResource(getResources().getIdentifier(cardHolderBJ1, "drawable", getPackageName()));
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(getResources().getIdentifier(cardHolderBJ10, "drawable", getPackageName()));
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier("gray_back", "drawable", getPackageName()));
            blackjackAction();
        }
        else {
            dealerText.setText("Not enough money");
            playBlackjack();
        }
    }

    protected void blackjackAction() {
        final Button betButton = findViewById(R.id.betButton);
        final Button split = findViewById(R.id.splitButton);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);
        final TextView dealerText = findViewById(R.id.dealerText);
        final TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView rightCountText = findViewById(R.id.rightSideCount);

        computerCount = 0;
        computerCountAce = 0;
        rightCountAce = 0;
        leftCountAce = 0;
        rightCount = 0;
        leftCount = 0;

        if (cardHolderBJ[10].getFaceValue() == 11 | cardHolderBJ[11].getFaceValue() == 11) {
            if (cardHolderBJ[10].getFaceValue() == 11) {
                computerCountAce = computerCountAce + 1;
            }
            else {
                computerCountAce = computerCountAce + cardHolderBJ[10].getFaceValue();
            }
            if (cardHolderBJ[11].getFaceValue() == 11) {
                computerCountAce = computerCountAce + 1;
            }
            else {
                computerCountAce = computerCountAce + cardHolderBJ[11].getFaceValue();
            }
        }

        if (cardHolderBJ[10].getFaceValue() == 11 & cardHolderBJ[11].getFaceValue() == 11) {
            computerCount = 12;
        }
        else {
            computerCount = cardHolderBJ[10].getFaceValue() + cardHolderBJ[11].getFaceValue();
        }

        if (cardHolderBJ[0].getFaceValue() == 11 & cardHolderBJ[1].getFaceValue() == 11) {
            rightCount = 12;
        }
        else {
            rightCount = cardHolderBJ[0].getFaceValue() + cardHolderBJ[1].getFaceValue();
        }

        //Check if blackjack first and check if computer has blackjack to see tie
        if (rightCount == 21) {
            dealerText.bringToFront();
            dealerText.setText("You got a blackjack!");
            blackjack = "True";
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (computerCount == 21) {
                        dealerText.bringToFront();
                        dealerText.setText("Dealer blackjack - push");
                        totalMoney = totalMoney + betAmount;
                        totalMoneyTextBlackjack.setText("$" + totalMoney);
                        ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolderBJ11, "drawable", getPackageName()));
                        saveTotalMoney();
                    }
                    else {
                        winnings = betAmount * 5;
                        winnings = winnings/2;
                        dealerText.bringToFront();
                        dealerText.setText("Player wins with blackjack! +$" + winnings);
                        totalMoney = totalMoney + winnings;
                        totalMoneyTextBlackjack.setText("$" + totalMoney);
                        ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolderBJ11, "drawable", getPackageName()));
                        saveTotalMoney();
                    }
                }
            }, 1000);
        }
        if (blackjack.equals("False")) {
            if (cardHolderBJ[10].getFaceValue() == 11 & insuranceCheck.equals("True")) {
                stand.setVisibility(View.VISIBLE);
                stand.setClickable(true);
                betButton.setClickable(true);
                stand.setText("No");
                dealerText.bringToFront();
                dealerText.setText("Insurance?");
                insuranceAmount = betAmount/2;
                betButton.setText("Yes $" + insuranceAmount);
                if (insuranceAmount <= totalMoney) {
                    betButton.setTextColor(getResources().getColor(R.color.green));
                    stand.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    stand.setTextColor(getResources().getColor(R.color.green));
                    betButton.setTextColor(getResources().getColor(R.color.red));
                }

                betButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (insuranceAmount <= totalMoney) {
                            stand.setClickable(false);
                            betButton.setClickable(false);
                            totalMoney = totalMoney - insuranceAmount;
                            totalMoneyTextBlackjack.setText("$" + totalMoney);
                            saveTotalMoney();
                            if (cardHolderBJ[11].getFaceValue() == 10) {
                                dealerText.bringToFront();
                                dealerText.setText("Blackjack! Win money back.");
                                totalMoney = totalMoney + betAmount + insuranceAmount;
                                totalMoneyTextBlackjack.setText("$" + totalMoney);
                                ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolderBJ11, "drawable", getPackageName()));
                                saveTotalMoney();
                                restartBlackjack();
                            } else {
                                dealerText.bringToFront();
                                dealerText.setText("No Blackjack. Lose Insurance.");
                                insuranceCheck = "False";
                                blackjackAction();
                            }
                        }
                    }
                });

                stand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cardHolderBJ[11].getFaceValue() == 10) {
                            dealerText.bringToFront();
                            dealerText.setText("Blackjack, sorry you lost.");
                            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolderBJ11, "drawable", getPackageName()));
                            restartBlackjack();
                        } else {
                            dealerText.bringToFront();
                            dealerText.setText("No Blackjack! Good choice.");
                            insuranceCheck = "False";
                            blackjackAction();
                        }
                    }
                });
            } else {
                if (cardHolderBJ[10].getFaceValue() == 10 & cardHolderBJ[11].getFaceValue() == 11) {
                    dealerText.bringToFront();
                    dealerText.setText("Dealer Blackjack. Sorry you lost.");
                    ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolderBJ11, "drawable", getPackageName()));
                    restartBlackjack();
                } else {
                    split.setVisibility(View.VISIBLE);
                    hit.setVisibility(View.VISIBLE);
                    stand.setVisibility(View.VISIBLE);
                    betButton.setText("Double");
                    split.setText("Split");
                    hit.setText("Hit");
                    stand.setText("Stand");
                    rightCountText.setBackground(getResources().getDrawable(R.drawable.bordergreen));
                    //check if there is an ace present, and if so create two different counts
                    if (cardHolderBJ[0].getFaceValue() == 11 | cardHolderBJ[1].getFaceValue() == 11) {
                        rightCountAce = 0;
                        if (cardHolderBJ[0].getFaceValue() == 11) {
                            rightCountAce = rightCountAce + 1;
                        } else {
                            rightCountAce = rightCountAce + cardHolderBJ[0].getFaceValue();
                        }
                        if (cardHolderBJ[1].getFaceValue() == 11) {
                            rightCountAce = rightCountAce + 1;
                        } else {
                            rightCountAce = rightCountAce + cardHolderBJ[1].getFaceValue();
                        }
                        rightCountText.setText("" + rightCountAce + "/" + rightCount);
                    } else {
                        rightCountText.setText("" + rightCount);
                    }

                    //Check if you can split (if not set split button color to red) if no split then run through normal; if split then go to split action and make sure all following code ends
                    if (cardHolderBJ[0].getFaceValue() == cardHolderBJ[1].getFaceValue()) {
                        if (betAmount <= totalMoney) {
                            split.setClickable(true);
                            split.setTextColor(getResources().getColor(R.color.green));
                        }
                    } else {
                        split.setClickable(false);
                        split.setTextColor(getResources().getColor(R.color.red));
                    }

                    if (betAmount <= totalMoney) {
                        betButton.setClickable(true);
                        betButton.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        betButton.setClickable(false);
                        betButton.setTextColor(getResources().getColor(R.color.red));
                    }
                    //Put all clickable buttons to green and check if enough money to double
                    hit.setClickable(true);
                    hit.setTextColor(getResources().getColor(R.color.green));
                    stand.setClickable(true);
                    stand.setTextColor(getResources().getColor(R.color.green));

                    hit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            betButton.setClickable(false);
                            split.setClickable(false);
                            betButton.setTextColor(getResources().getColor(R.color.red));
                            hit.setTextColor(getResources().getColor(R.color.red));
                            stand.setTextColor(getResources().getColor(R.color.red));
                            split.setTextColor(getResources().getColor(R.color.red));
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    hit1();
                                }
                            }, 500);
                        }
                    });
                    stand.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            betButton.setClickable(false);
                            split.setClickable(false);
                            betButton.setTextColor(getResources().getColor(R.color.red));
                            hit.setTextColor(getResources().getColor(R.color.red));
                            stand.setTextColor(getResources().getColor(R.color.red));
                            split.setTextColor(getResources().getColor(R.color.red));
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    stand1();
                                }
                            }, 500);
                        }
                    });
                    betButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            betButton.setClickable(false);
                            split.setClickable(false);
                            betButton.setTextColor(getResources().getColor(R.color.red));
                            hit.setTextColor(getResources().getColor(R.color.red));
                            stand.setTextColor(getResources().getColor(R.color.red));
                            split.setTextColor(getResources().getColor(R.color.red));
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    double1();
                                }
                            }, 500);
                        }
                    });
                    split.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            betButton.setClickable(false);
                            split.setClickable(false);
                            betButton.setTextColor(getResources().getColor(R.color.red));
                            hit.setTextColor(getResources().getColor(R.color.red));
                            stand.setTextColor(getResources().getColor(R.color.red));
                            split.setTextColor(getResources().getColor(R.color.red));
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    split1();
                                }
                            }, 500);
                        }
                    });

                    if (cardHolderBJ[0].getFaceValue() == cardHolderBJ[1].getFaceValue()) {
                        if (betAmount <= totalMoney) {
                            split.setClickable(true);
                            split.setTextColor(getResources().getColor(R.color.green));
                        }
                    } else {
                        split.setClickable(false);
                        split.setTextColor(getResources().getColor(R.color.red));
                    }

                    if (betAmount <= totalMoney) {
                        betButton.setClickable(true);
                        betButton.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        betButton.setClickable(false);
                        betButton.setTextColor(getResources().getColor(R.color.red));
                    }
                }
            }
        }
        if (blackjack.equals("True")) {
            restartBlackjack();
        }
    }

    protected void hit1() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardMiddle)).setImageResource(getResources().getIdentifier(cardHolderBJ2, "drawable", getPackageName()));

        if (cardHolderBJ[2].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[2].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[0].getFaceValue();
        }
        if (cardHolderBJ[2].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[2].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 0; i <= 2; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("" + rightCount);
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce);
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
            stand1();
        }
        else {
            hit.setClickable(true);
            hit.setTextColor(getResources().getColor(R.color.green));
            stand.setClickable(true);
            stand.setTextColor(getResources().getColor(R.color.green));

            hit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hit2();
                        }
                    }, 500);
                }
            });
            stand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stand1();
                        }
                    }, 500);
                }
            });
        }
    }

    protected void hit2() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardRight1)).setImageResource(getResources().getIdentifier(cardHolderBJ3, "drawable", getPackageName()));

        if (cardHolderBJ[3].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[3].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[0].getFaceValue() + cardHolderBJ[2].getFaceValue();
        }
        if (cardHolderBJ[3].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[3].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 0; i <= 3; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("" + rightCount);
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce);
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
            stand1();
        }
        else {
            hit.setClickable(true);
            hit.setTextColor(getResources().getColor(R.color.green));
            stand.setClickable(true);
            stand.setTextColor(getResources().getColor(R.color.green));

            hit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hit3();
                        }
                    }, 500);
                }
            });
            stand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stand1();
                        }
                    }, 500);
                }
            });
        }
    }

    protected void hit3() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardRight2)).setImageResource(getResources().getIdentifier(cardHolderBJ4, "drawable", getPackageName()));

        if (cardHolderBJ[4].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[4].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[0].getFaceValue() + cardHolderBJ[2].getFaceValue() + cardHolderBJ[3].getFaceValue();
        }
        if (cardHolderBJ[4].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[4].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 0; i <= 4; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("Bonus");
            rightSideBonus = "True";
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("Bonus");
            rightSideBonus = "True";
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("Bonus");
            rightSideBonus = "True";
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
        }
        stand1();
    }

    protected void stand1() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        rightCountText.setBackground(getResources().getDrawable(R.drawable.border));
        getComputerActionBlackjack();
    }

    protected void double1() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);

        doubleRightSide = "True";
        doubleAmount = betAmount * 2;
        totalMoney = totalMoney - betAmount;
        totalMoneyTextBlackjack.setText("$" + totalMoney);
        betText.setText("Double $" + doubleAmount);
        saveTotalMoney();
        ((ImageView) findViewById(R.id.playerCardMiddle)).setImageResource(getResources().getIdentifier(cardHolderBJ2, "drawable", getPackageName()));

        if (cardHolderBJ[2].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[2].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[0].getFaceValue();
        }
        if (cardHolderBJ[2].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[2].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 0; i <= 2; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("" + rightCount);
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce);
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
        }
        stand1();
    }

    protected void split1() {
        rightCountAce = 0;
        rightCount = 0;
        leftCountAce = 0;
        leftCount = 0;
        TextView splitLeftText = findViewById(R.id.splitLeftText);
        TextView splitRightText = findViewById(R.id.splitRightText);
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView leftCountText = findViewById(R.id.leftSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button betButton = (Button) findViewById(R.id.betButton);
        final Button split = findViewById(R.id.splitButton);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);
        final TextView dealerText = findViewById(R.id.dealerText);
        TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);

        betText.setText("");
        ((ImageView) findViewById(R.id.playerCardLeft1)).setImageResource(getResources().getIdentifier(cardHolderBJ1, "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.playerCardLeft2)).setImageResource(0);
        ((ImageView) findViewById(R.id.playerCardRight1)).setImageResource(getResources().getIdentifier(cardHolderBJ0, "drawable", getPackageName()));
        ((ImageView) findViewById(R.id.playerCardRight2)).setImageResource(getResources().getIdentifier(cardHolderBJ2, "drawable", getPackageName()));

        splitLeftText.setText("Bet $" + betAmount);
        splitRightText.setText("Bet $" + betAmount);
        totalMoney = totalMoney - betAmount;
        totalMoneyTextBlackjack.setText("$" + totalMoney);
        saveTotalMoney();

        if (cardHolderBJ[1].getFaceValue() == 11 & cardHolderBJ[2].getFaceValue() == 11) {
            rightCount = 12;
        }
        else {
            rightCount = cardHolderBJ[1].getFaceValue() + cardHolderBJ[2].getFaceValue();
        }

        if (cardHolderBJ[1].getFaceValue() == 11 | cardHolderBJ[2].getFaceValue() == 11) {
            rightCountAce = 0;
            if (cardHolderBJ[1].getFaceValue() == 11) {
                rightCountAce = rightCountAce + 1;
            }
            else {
                rightCountAce = rightCountAce + cardHolderBJ[1].getFaceValue();
            }
            if (cardHolderBJ[2].getFaceValue() == 11) {
                rightCountAce = rightCountAce + 1;
            }
            else {
                rightCountAce = rightCountAce + cardHolderBJ[2].getFaceValue();
            }
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        } else {
            rightCountText.setText("" + rightCount);
        }

        if (betAmount <= totalMoney) {
            betButton.setClickable(true);
            betButton.setTextColor(getResources().getColor(R.color.green));
        }
        else {
            betButton.setClickable(false);
            betButton.setTextColor(getResources().getColor(R.color.red));
        }

        //Put all clickable buttons to green and check if enough money to double
        hit.setClickable(true);
        hit.setTextColor(getResources().getColor(R.color.green));
        stand.setClickable(true);
        stand.setTextColor(getResources().getColor(R.color.green));

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                split.setClickable(false);
                betButton.setTextColor(getResources().getColor(R.color.red));
                hit.setTextColor(getResources().getColor(R.color.red));
                stand.setTextColor(getResources().getColor(R.color.red));
                split.setTextColor(getResources().getColor(R.color.red));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hitSplit1();
                    }
                }, 500);
            }
        });
        stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                split.setClickable(false);
                betButton.setTextColor(getResources().getColor(R.color.red));
                hit.setTextColor(getResources().getColor(R.color.red));
                stand.setTextColor(getResources().getColor(R.color.red));
                split.setTextColor(getResources().getColor(R.color.red));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        standSplit1();
                    }
                }, 500);
            }
        });
        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                split.setClickable(false);
                betButton.setTextColor(getResources().getColor(R.color.red));
                hit.setTextColor(getResources().getColor(R.color.red));
                stand.setTextColor(getResources().getColor(R.color.red));
                split.setTextColor(getResources().getColor(R.color.red));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleSplit1();
                    }
                }, 500);
            }
        });

        if (betAmount <= totalMoney) {
            betButton.setClickable(true);
            betButton.setTextColor(getResources().getColor(R.color.green));
        }
        else {
            betButton.setClickable(false);
            betButton.setTextColor(getResources().getColor(R.color.red));
        }
    }

    protected void hitSplit1() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardRight3)).setImageResource(getResources().getIdentifier(cardHolderBJ3, "drawable", getPackageName()));

        if (cardHolderBJ[3].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[3].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[2].getFaceValue();
        }
        if (cardHolderBJ[3].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[3].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 1; i <= 3; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }
        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("" + rightCount);
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce);
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
            standSplit1();
        } else {
            hit.setClickable(true);
            hit.setTextColor(getResources().getColor(R.color.green));
            stand.setClickable(true);
            stand.setTextColor(getResources().getColor(R.color.green));

            hit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hitSplit2();
                        }
                    }, 500);
                }
            });
            stand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            standSplit1();
                        }
                    }, 500);
                }
            });
        }
    }

    protected void hitSplit2() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardRight4)).setImageResource(getResources().getIdentifier(cardHolderBJ4, "drawable", getPackageName()));

        if (cardHolderBJ[4].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[4].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[2].getFaceValue() + cardHolderBJ[3].getFaceValue();
        }
        if (cardHolderBJ[4].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[4].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 1; i <= 4; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("" + rightCount);
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce);
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
            standSplit1();
        } else {
            hit.setClickable(true);
            hit.setTextColor(getResources().getColor(R.color.green));
            stand.setClickable(true);
            stand.setTextColor(getResources().getColor(R.color.green));

            hit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hitSplit3();
                        }
                    }, 500);
                }
            });
            stand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            standSplit1();
                        }
                    }, 500);
                }
            });
        }
    }

    protected void hitSplit3() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardRight5)).setImageResource(getResources().getIdentifier(cardHolderBJ5, "drawable", getPackageName()));

        if (cardHolderBJ[5].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[5].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[2].getFaceValue() + cardHolderBJ[3].getFaceValue() + cardHolderBJ[4].getFaceValue();
        }
        if (cardHolderBJ[5].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[5].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 1; i <= 5; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("Bonus");
            rightSideBonus = "True";
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("Bonus");
            rightSideBonus = "True";
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("Bonus");
            rightSideBonus = "True";
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
        }
        standSplit1();
    }

    protected void standSplit1() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        rightCountText.setBackground(getResources().getDrawable(R.drawable.border));
        getSplitLeftAction();
    }

    protected void getSplitLeftAction() {
        leftCountAce = 0;
        leftCount = 0;
        TextView splitLeftText = findViewById(R.id.splitLeftText);
        TextView splitRightText = findViewById(R.id.splitRightText);
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView leftCountText = findViewById(R.id.leftSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button betButton = (Button) findViewById(R.id.betButton);
        final Button split = findViewById(R.id.splitButton);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);
        final TextView dealerText = findViewById(R.id.dealerText);

        ((ImageView) findViewById(R.id.playerCardLeft2)).setImageResource(getResources().getIdentifier(cardHolderBJ6, "drawable", getPackageName()));

        splitLeftText.setText("Bet $" + betAmount);
        leftCountText.setVisibility(View.VISIBLE);

        if (cardHolderBJ[0].getFaceValue() == 11 & cardHolderBJ[6].getFaceValue() == 11) {
            leftCount = 12;
        }
        else {
            leftCount = cardHolderBJ[0].getFaceValue() + cardHolderBJ[6].getFaceValue();
        }

        if (cardHolderBJ[0].getFaceValue() == 11 | cardHolderBJ[6].getFaceValue() == 11) {
            leftCountAce = 0;
            if (cardHolderBJ[0].getFaceValue() == 11) {
                leftCountAce = leftCountAce + 1;
            }
            else {
                leftCountAce = leftCountAce + cardHolderBJ[0].getFaceValue();
            }
            if (cardHolderBJ[6].getFaceValue() == 11) {
                leftCountAce = leftCountAce + 1;
            }
            else {
                leftCountAce = leftCountAce + cardHolderBJ[6].getFaceValue();
            }
            leftCountText.setText("" + leftCountAce + "/" + leftCount);
        } else {
            leftCountText.setText("" + leftCount);
        }

        if (betAmount <= totalMoney) {
            betButton.setClickable(true);
            betButton.setTextColor(getResources().getColor(R.color.green));
        }
        else {
            betButton.setClickable(false);
            betButton.setTextColor(getResources().getColor(R.color.red));
        }

        //Put all clickable buttons to green and check if enough money to double
        hit.setClickable(true);
        hit.setTextColor(getResources().getColor(R.color.green));
        stand.setClickable(true);
        stand.setTextColor(getResources().getColor(R.color.green));

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                split.setClickable(false);
                betButton.setTextColor(getResources().getColor(R.color.red));
                hit.setTextColor(getResources().getColor(R.color.red));
                stand.setTextColor(getResources().getColor(R.color.red));
                split.setTextColor(getResources().getColor(R.color.red));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hitLeftSplit1();
                    }
                }, 500);
            }
        });
        stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                split.setClickable(false);
                betButton.setTextColor(getResources().getColor(R.color.red));
                hit.setTextColor(getResources().getColor(R.color.red));
                stand.setTextColor(getResources().getColor(R.color.red));
                split.setTextColor(getResources().getColor(R.color.red));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        standSplit2();
                    }
                }, 500);
            }
        });
        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betButton.setClickable(false);
                split.setClickable(false);
                betButton.setTextColor(getResources().getColor(R.color.red));
                hit.setTextColor(getResources().getColor(R.color.red));
                stand.setTextColor(getResources().getColor(R.color.red));
                split.setTextColor(getResources().getColor(R.color.red));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleSplit2();
                    }
                }, 500);
            }
        });

        if (betAmount <= totalMoney) {
            betButton.setClickable(true);
            betButton.setTextColor(getResources().getColor(R.color.green));
        }
        else {
            betButton.setClickable(false);
            betButton.setTextColor(getResources().getColor(R.color.red));
        }
    }

    protected void hitLeftSplit1() {
        TextView leftCountText = findViewById(R.id.leftSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardLeft3)).setImageResource(getResources().getIdentifier(cardHolderBJ7, "drawable", getPackageName()));

        if (cardHolderBJ[7].getFaceValue() == 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + 1;
        }
        if (cardHolderBJ[7].getFaceValue() == 11 & leftCountAce == 0) {
            leftCountAce = 1 + cardHolderBJ[0].getFaceValue() + cardHolderBJ[6].getFaceValue();
        }
        if (cardHolderBJ[7].getFaceValue() != 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + cardHolderBJ[7].getFaceValue();
        }

        aceCounter = 0;
        leftCount = 0;
        for (int i = 0; i <= 2; i++) {
            switch (i) {
                case 0: cardCounter = 0; break;
                case 1: cardCounter = 6; break;
                case 2: cardCounter = 7; break;
            }

            if (cardHolderBJ[cardCounter].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    leftCount = leftCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        leftCount = leftCount + 1;
                    }
                }
            }
            else {
                leftCount = leftCount + cardHolderBJ[cardCounter].getFaceValue();
            }
        }

        if (leftCount <= 21 & leftCountAce == 0) {
            leftCountText.setText("" + leftCount);
        }
        if (leftCount <= 21 & leftCountAce != 0) {
            leftCountText.setText("" + leftCountAce + "/" + leftCount);
        }
        if (leftCount > 21 & leftCountAce != 0) {
            leftCountText.setText("" + leftCountAce);
        }
        if (leftCount > 21 & (leftCountAce > 21 | leftCountAce == 0)) {
            leftCountText.setText("Bust");
            standSplit2();
        } else {
            hit.setClickable(true);
            hit.setTextColor(getResources().getColor(R.color.green));
            stand.setClickable(true);
            stand.setTextColor(getResources().getColor(R.color.green));

            hit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hitLeftSplit2();
                        }
                    }, 500);
                }
            });
            stand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            standSplit2();
                        }
                    }, 500);
                }
            });
        }
    }

    protected void hitLeftSplit2() {
        TextView leftCountText = findViewById(R.id.leftSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardLeft4)).setImageResource(getResources().getIdentifier(cardHolderBJ8, "drawable", getPackageName()));

        if (cardHolderBJ[8].getFaceValue() == 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + 1;
        }
        if (cardHolderBJ[8].getFaceValue() == 11 & leftCountAce == 0) {
            leftCountAce = 1 + cardHolderBJ[0].getFaceValue() + cardHolderBJ[6].getFaceValue() + cardHolderBJ[7].getFaceValue();
        }
        if (cardHolderBJ[8].getFaceValue() != 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + cardHolderBJ[8].getFaceValue();
        }

        aceCounter = 0;
        leftCount = 0;
        for (int i = 0; i <= 3; i++) {
            switch (i) {
                case 0: cardCounter = 0; break;
                case 1: cardCounter = 6; break;
                case 2: cardCounter = 7; break;
                case 3: cardCounter = 8; break;
            }

            if (cardHolderBJ[cardCounter].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    leftCount = leftCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        leftCount = leftCount + 1;
                    }
                }
            }
            else {
                leftCount = leftCount + cardHolderBJ[cardCounter].getFaceValue();
            }
        }

        if (leftCount <= 21 & leftCountAce == 0) {
            leftCountText.setText("" + leftCount);
        }
        if (leftCount <= 21 & leftCountAce != 0) {
            leftCountText.setText("" + leftCountAce + "/" + leftCount);
        }
        if (leftCount > 21 & leftCountAce != 0) {
            leftCountText.setText("" + leftCountAce);
        }
        if (leftCount > 21 & (leftCountAce > 21 | leftCountAce == 0)) {
            leftCountText.setText("Bust");
            standSplit2();
        } else {
            hit.setClickable(true);
            hit.setTextColor(getResources().getColor(R.color.green));
            stand.setClickable(true);
            stand.setTextColor(getResources().getColor(R.color.green));

            hit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hitLeftSplit3();
                        }
                    }, 500);
                }
            });
            stand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hit.setTextColor(getResources().getColor(R.color.red));
                    stand.setTextColor(getResources().getColor(R.color.red));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            standSplit2();
                        }
                    }, 500);
                }
            });
        }
    }

    protected void hitLeftSplit3() {
        TextView leftCountText = findViewById(R.id.leftSideCount);
        TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);

        ((ImageView) findViewById(R.id.playerCardLeft5)).setImageResource(getResources().getIdentifier(cardHolderBJ9, "drawable", getPackageName()));

        if (cardHolderBJ[9].getFaceValue() == 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + 1;
        }
        if (cardHolderBJ[9].getFaceValue() == 11 & leftCountAce == 0) {
            leftCountAce = 1 + cardHolderBJ[0].getFaceValue() + cardHolderBJ[6].getFaceValue() + cardHolderBJ[7].getFaceValue() + cardHolderBJ[8].getFaceValue();
        }
        if (cardHolderBJ[9].getFaceValue() != 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + cardHolderBJ[9].getFaceValue();
        }

        aceCounter = 0;
        leftCount = 0;
        for (int i = 0; i <= 4; i++) {
            switch (i) {
                case 0: cardCounter = 0; break;
                case 1: cardCounter = 6; break;
                case 2: cardCounter = 7; break;
                case 3: cardCounter = 8; break;
                case 4: cardCounter = 9; break;
            }

            if (cardHolderBJ[cardCounter].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    leftCount = leftCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        leftCount = leftCount + 1;
                    }
                }
            }
            else {
                leftCount = leftCount + cardHolderBJ[cardCounter].getFaceValue();
            }
        }

        if (leftCount <= 21 & leftCountAce == 0) {
            leftCountText.setText("Bonus");
            leftSideBonus = "True";
        }
        if (leftCount <= 21 & leftCountAce != 0) {
            leftCountText.setText("Bonus");
            leftSideBonus = "True";
        }
        if (leftCount > 21 & leftCountAce != 0) {
            leftCountText.setText("Bonus");
            leftSideBonus = "True";
        }
        if (leftCount > 21 & (leftCountAce > 21 | leftCountAce == 0)) {
            leftCountText.setText("Bust");
        }
        standSplit2();
    }

    protected void standSplit2() {
        TextView leftCountText = findViewById(R.id.leftSideCount);
        leftCountText.setBackground(getResources().getDrawable(R.drawable.border));
        getComputerActionBlackjack();
    }

    protected void doubleSplit1() {
        TextView rightCountText = findViewById(R.id.rightSideCount);
        TextView rightBetText = findViewById(R.id.splitRightText);
        TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);

        doubleRightSide = "True";
        doubleAmount = betAmount * 2;
        totalMoney = totalMoney - betAmount;
        totalMoneyTextBlackjack.setText("$" + totalMoney);
        rightBetText.setText("Double $" + doubleAmount);
        saveTotalMoney();

        ((ImageView) findViewById(R.id.playerCardRight3)).setImageResource(getResources().getIdentifier(cardHolderBJ3, "drawable", getPackageName()));

        if (cardHolderBJ[3].getFaceValue() == 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + 1;
        }
        if (cardHolderBJ[3].getFaceValue() == 11 & rightCountAce == 0) {
            rightCountAce = 1 + cardHolderBJ[1].getFaceValue() + cardHolderBJ[2].getFaceValue();
        }
        if (cardHolderBJ[3].getFaceValue() != 11 & rightCountAce != 0) {
            rightCountAce = rightCountAce + cardHolderBJ[3].getFaceValue();
        }

        aceCounter = 0;
        rightCount = 0;
        for (int i = 1; i <= 3; i++) {
            if (cardHolderBJ[i].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    rightCount = rightCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        rightCount = rightCount + 1;
                    }
                }
            }
            else {
                rightCount = rightCount + cardHolderBJ[i].getFaceValue();
            }
        }

        if (rightCount <= 21 & rightCountAce == 0) {
            rightCountText.setText("" + rightCount);
        }
        if (rightCount <= 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce + "/" + rightCount);
        }
        if (rightCount > 21 & rightCountAce != 0) {
            rightCountText.setText("" + rightCountAce);
        }
        if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
            rightCountText.setText("Bust");
        }
        standSplit1();
    }

    protected void doubleSplit2() {
        TextView leftCountText = findViewById(R.id.leftSideCount);
        TextView leftBetText = findViewById(R.id.splitLeftText);
        TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);

        doubleLeftSide = "True";
        doubleAmount = betAmount * 2;
        totalMoney = totalMoney - betAmount;
        totalMoneyTextBlackjack.setText("$" + totalMoney);
        leftBetText.setText("Double $" + doubleAmount);
        saveTotalMoney();

        ((ImageView) findViewById(R.id.playerCardLeft3)).setImageResource(getResources().getIdentifier(cardHolderBJ7, "drawable", getPackageName()));

        if (cardHolderBJ[7].getFaceValue() == 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + 1;
        }
        if (cardHolderBJ[7].getFaceValue() == 11 & leftCountAce == 0) {
            leftCountAce = 1 + cardHolderBJ[0].getFaceValue() + cardHolderBJ[6].getFaceValue();
        }
        if (cardHolderBJ[7].getFaceValue() != 11 & leftCountAce != 0) {
            leftCountAce = leftCountAce + cardHolderBJ[7].getFaceValue();
        }

        aceCounter = 0;
        leftCount = 0;
        for (int i = 0; i <= 2; i++) {
            switch (i) {
                case 0: cardCounter = 0; break;
                case 1: cardCounter = 6; break;
                case 2: cardCounter = 7; break;
            }

            if (cardHolderBJ[cardCounter].getFaceValue() == 11) {
                aceCounter++;
                if (aceCounter == 1) {
                    leftCount = leftCount + 11;
                }
                else {
                    if (aceCounter >= 2) {
                        leftCount = leftCount + 1;
                    }
                }
            }
            else {
                leftCount = leftCount + cardHolderBJ[cardCounter].getFaceValue();
            }
        }

        if (leftCount <= 21 & leftCountAce == 0) {
            leftCountText.setText("" + leftCount);
        }
        if (leftCount <= 21 & leftCountAce != 0) {
            leftCountText.setText("" + leftCountAce + "/" + leftCount);
        }
        if (leftCount > 21 & leftCountAce != 0) {
            leftCountText.setText("" + leftCountAce);
        }
        if (leftCount > 21 & (leftCountAce > 21 | leftCountAce == 0)) {
            leftCountText.setText("Bust");
        }
        standSplit2();
    }

    protected void fillCards() {
        Random sBJ = new Random();
        Random nBJ = new Random();
        int numBJ = 0;
        int suitBJ = 0;
        String cardSuit = "";
        String cardNum = "";

        for (int i = 0; i < 15; i++) {
            suitBJ = sBJ.nextInt(4);
            numBJ = nBJ.nextInt(13);
            numBJ = numBJ + 2;

            if (suitBJ == 0) {
                cardSuit = "s";
            }
            if (suitBJ == 1) {
                cardSuit = "h";
            }
            if (suitBJ == 2) {
                cardSuit = "c";
            }
            if (suitBJ == 3) {
                cardSuit = "d";
            }
            if (numBJ == 2) {
                cardNum = "2";
            }
            if (numBJ == 3) {
                cardNum = "3";
            }
            if (numBJ == 4) {
                cardNum = "4";
            }
            if (numBJ == 5) {
                cardNum = "5";
            }
            if (numBJ == 6) {
                cardNum = "6";
            }
            if (numBJ == 7) {
                cardNum = "7";
            }
            if (numBJ == 8) {
                cardNum = "8";
            }
            if (numBJ == 9) {
                cardNum = "9";
            }
            if (numBJ == 10) {
                cardNum = "10";
            }
            if (numBJ == 11) {
                cardNum = "j";
                numBJ = 10;
            }
            if (numBJ == 12) {
                cardNum = "q";
                numBJ = 10;
            }
            if (numBJ == 13) {
                cardNum = "k";
                numBJ = 10;
            }
            if (numBJ == 14) {
                cardNum = "a";
                numBJ = 11;
            }

            cardHolderBJ[i] = new Card(cardSuit, cardNum, numBJ);;
        }

        for (int i = 0; i < 15; i++) {
            String holderNumber = "";
            String holderSuit = "";

            holderNumber = cardHolderBJ[i].getFaceName();
            holderSuit = cardHolderBJ[i].getSuit();

            switch(i) {
                case 0: cardHolderBJ0 = holderSuit + holderNumber; break;
                case 1: cardHolderBJ1 = holderSuit + holderNumber; break;
                case 2: cardHolderBJ2 = holderSuit + holderNumber; break;
                case 3: cardHolderBJ3 = holderSuit + holderNumber; break;
                case 4: cardHolderBJ4 = holderSuit + holderNumber; break;
                case 5: cardHolderBJ5 = holderSuit + holderNumber; break;
                case 6: cardHolderBJ6 = holderSuit + holderNumber; break;
                case 7: cardHolderBJ7 = holderSuit + holderNumber; break;
                case 8: cardHolderBJ8 = holderSuit + holderNumber; break;
                case 9: cardHolderBJ9 = holderSuit + holderNumber; break;
                case 10: cardHolderBJ10 = holderSuit + holderNumber; break;
                case 11: cardHolderBJ11 = holderSuit + holderNumber; break;
                case 12: cardHolderBJ12 = holderSuit + holderNumber; break;
                case 13: cardHolderBJ13 = holderSuit + holderNumber; break;
                case 14: cardHolderBJ14 = holderSuit + holderNumber; break;
            }
        }
    }

    protected void betBar() {
        betSeekBar = (SeekBar) findViewById(R.id.betBar);
        betButton = (Button) findViewById(R.id.betButton);
        betSeekBar.setVisibility(View.VISIBLE);
        if (progressValue == 0) {
            progressValue = 50;
        }
        betButton.setText("Bet $" + progressValue);
        betSeekBar.setMax(betMultiplier);
        betSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = ((progress + 1)*50);
                        progressHolder = progress;
                        betButton.setText("Bet $" + progressValue);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        progressValue = ((progressHolder + 1)*50);
                        betButton.setText("Bet $" + progressValue);
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        progressValue = ((progressHolder + 1)*50);
                        betButton.setText("Bet $" + progressValue);
                    }
                }
        );
    }

    /* protected void tipDealer() {
        tipButton = (Button) findViewById(R.id.tipDealerBlackjack);
        final TextView computerThankYou = (TextView) findViewById(R.id.dealerText);

        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computerThankYou.setTextSize(20);
                computerThankYou.bringToFront();
                computerThankYou.setText("Thank you and good luck! -$100");
                totalMoney = totalMoney - 100;
                final TextView totalMoneyText = findViewById(R.id.totalMoneyTextBlackjack);
                totalMoneyText.setText("$" + totalMoney);
                saveTotalMoney();
            }
        });
    } */

    protected void quitButton() {
        Button quitButton = (Button) findViewById(R.id.quitBlackjack);
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

    protected void getComputerActionBlackjack() {
        final TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView dealerCountText = findViewById(R.id.dealerCount);

        ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolderBJ11, "drawable", getPackageName()));

        if (cardHolderBJ[10].getFaceValue() == 11 | cardHolderBJ[11].getFaceValue() == 11) {
            dealerCountText.setText("" + computerCountAce + "/" + computerCount);
        } else {
            dealerCountText.setText("" + computerCount);
        }

        Handler handler = new Handler();
        if ((computerCount < 16 | (computerCountAce <=6 & computerCountAce != 0)) | (computerCount > 21 & computerCountAce < 16 & computerCountAce !=0)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((ImageView) findViewById(R.id.computerCard3)).setImageResource(getResources().getIdentifier(cardHolderBJ12, "drawable", getPackageName()));

                    if (cardHolderBJ[12].getFaceValue() == 11 & computerCountAce != 0) {
                        computerCountAce = computerCountAce + 1;
                    }
                    if (cardHolderBJ[12].getFaceValue() == 11 & computerCountAce == 0) {
                        computerCountAce = 1 + cardHolderBJ[10].getFaceValue() + cardHolderBJ[11].getFaceValue();
                    }
                    if (cardHolderBJ[12].getFaceValue() != 11 & computerCountAce != 0) {
                        computerCountAce = computerCountAce + cardHolderBJ[12].getFaceValue();
                    }

                    aceCounter = 0;
                    computerCount = 0;
                    for (int i = 10; i <= 12; i++) {
                        if (cardHolderBJ[i].getFaceValue() == 11) {
                            aceCounter++;
                            if (aceCounter == 1) {
                                computerCount = computerCount + 11;
                            }
                            else {
                                if (aceCounter >= 2) {
                                    computerCount = computerCount + 1;
                                }
                            }
                        }
                        else {
                            computerCount = computerCount + cardHolderBJ[i].getFaceValue();
                        }
                    }

                    if (computerCount <= 21 & computerCountAce == 0) {
                        dealerCountText.setText("" + computerCount);
                    }
                    if (computerCount <= 21 & computerCountAce != 0) {
                        dealerCountText.setText("" + computerCountAce + "/" + computerCount);
                    }
                    if (computerCount > 21 & computerCountAce != 0) {
                        dealerCountText.setText("" + computerCountAce);
                    }
                    if (computerCount > 21 & (computerCountAce > 21 | computerCountAce == 0)) {
                        dealerCountText.setText("Bust");
                    }

                    dealerHit1();
                }
            }, 2500);
        }
        else {
            dealerCountText.setBackground(getResources().getDrawable(R.drawable.border));
            getBlackjackResults();
        }
    }

    protected void dealerHit1() {
        final TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView dealerCountText = findViewById(R.id.dealerCount);

        Handler handler = new Handler();
        if ((computerCount < 16 | (computerCountAce <=6 & computerCountAce != 0)) | (computerCount > 21 & computerCountAce < 16 & computerCountAce !=0)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((ImageView) findViewById(R.id.computerCard4)).setImageResource(getResources().getIdentifier(cardHolderBJ13, "drawable", getPackageName()));

                    if (cardHolderBJ[13].getFaceValue() == 11 & computerCountAce != 0) {
                        computerCountAce = computerCountAce + 1;
                    }
                    if (cardHolderBJ[13].getFaceValue() == 11 & computerCountAce == 0) {
                        computerCountAce = 1 + cardHolderBJ[10].getFaceValue() + cardHolderBJ[11].getFaceValue() + cardHolderBJ[12].getFaceValue();
                    }
                    if (cardHolderBJ[13].getFaceValue() != 11 & computerCountAce != 0) {
                        computerCountAce = computerCountAce + cardHolderBJ[13].getFaceValue();
                    }

                    aceCounter = 0;
                    computerCount = 0;
                    for (int i = 10; i <= 13; i++) {
                        if (cardHolderBJ[i].getFaceValue() == 11) {
                            aceCounter++;
                            if (aceCounter == 1) {
                                computerCount = computerCount + 11;
                            }
                            else {
                                if (aceCounter >= 2) {
                                    computerCount = computerCount + 1;
                                }
                            }
                        }
                        else {
                            computerCount = computerCount + cardHolderBJ[i].getFaceValue();
                        }
                    }

                    if (computerCount <= 21 & computerCountAce == 0) {
                        dealerCountText.setText("" + computerCount);
                    }
                    if (computerCount <= 21 & computerCountAce != 0) {
                        dealerCountText.setText("" + computerCountAce + "/" + computerCount);
                    }
                    if (computerCount > 21 & computerCountAce != 0) {
                        dealerCountText.setText("" + computerCountAce);
                    }
                    if (computerCount > 21 & (computerCountAce > 21 | computerCountAce == 0)) {
                        dealerCountText.setText("Bust");
                    }

                    dealerHit2();
                }
            }, 2500);
        }
        else {
            dealerCountText.setBackground(getResources().getDrawable(R.drawable.border));
            getBlackjackResults();
        }
    }

    protected void dealerHit2() {
        final TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView dealerCountText = findViewById(R.id.dealerCount);

        Handler handler = new Handler();
        if ((computerCount < 16 | (computerCountAce <=6 & computerCountAce != 0)) | (computerCount > 21 & computerCountAce < 16 & computerCountAce !=0)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((ImageView) findViewById(R.id.computerCard5)).setImageResource(getResources().getIdentifier(cardHolderBJ14, "drawable", getPackageName()));

                    if (cardHolderBJ[14].getFaceValue() == 11 & computerCountAce != 0) {
                        computerCountAce = computerCountAce + 1;
                    }
                    if (cardHolderBJ[14].getFaceValue() == 11 & computerCountAce == 0) {
                        computerCountAce = 1 + cardHolderBJ[10].getFaceValue() + cardHolderBJ[11].getFaceValue() + cardHolderBJ[12].getFaceValue() + cardHolderBJ[13].getFaceValue();
                    }
                    if (cardHolderBJ[14].getFaceValue() != 11 & computerCountAce != 0) {
                        computerCountAce = computerCountAce + cardHolderBJ[14].getFaceValue();
                    }

                    aceCounter = 0;
                    computerCount = 0;
                    for (int i = 10; i <= 14; i++) {
                        if (cardHolderBJ[i].getFaceValue() == 11) {
                            aceCounter++;
                            if (aceCounter == 1) {
                                computerCount = computerCount + 11;
                            }
                            else {
                                if (aceCounter >= 2) {
                                    computerCount = computerCount + 1;
                                }
                            }
                        }
                        else {
                            computerCount = computerCount + cardHolderBJ[i].getFaceValue();
                        }
                    }

                    if (computerCount <= 21 & computerCountAce == 0) {
                        dealerCountText.setText("Bonus");
                        computerSideBonus = "True";
                    }
                    if (computerCount <= 21 & computerCountAce != 0) {
                        dealerCountText.setText("Bonus");
                        computerSideBonus = "True";
                    }
                    if (computerCount > 21 & computerCountAce != 0) {
                        dealerCountText.setText("Bonus");
                        computerSideBonus = "True";
                    }
                    if (computerCount > 21 & (computerCountAce > 21 | computerCountAce == 0)) {
                        dealerCountText.setText("Bust");
                    }

                    dealerCountText.setBackground(getResources().getDrawable(R.drawable.border));
                    getBlackjackResults();
                }
            }, 2500);
        }
        else {
            dealerCountText.setBackground(getResources().getDrawable(R.drawable.border));
            getBlackjackResults();
        }
    }

    protected void getBlackjackResults() {
        final TextView dealerText = findViewById(R.id.dealerText);
        final TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView rightCountText = findViewById(R.id.rightSideCount);
        final TextView leftCountText = findViewById(R.id.leftSideCount);
        final TextView dealerCountText = findViewById(R.id.dealerCount);
        final TextView rightSideBet = findViewById(R.id.splitRightText);
        final TextView leftSideBet = findViewById(R.id.splitLeftText);
        final TextView betText = findViewById(R.id.betText);

        winnings = 0;
        if (computerSideBonus.equals("True") & rightSideBonus.equals("True")) {
            if (doubleRightSide.equals("True")) {
                winnings = betAmount * 2;
            }
            else {
                winnings = betAmount * 1;
            }
            totalMoney = totalMoney + winnings;
            if (leftCount != 0) {
                rightSideBet.setText("Tie. Push.");
            }
            else {
                betText.setText("Tie. Push.");
            }
        }
        else {
            if (computerSideBonus.equals("True") | rightSideBonus.equals("True")) {
                if (computerSideBonus.equals("True")) {
                    if (leftCount != 0) {
                        rightSideBet.setText("Lose");
                    }
                    else {
                        betText.setText("Lose");
                    }
                }
                if (rightSideBonus.equals("True")) {
                    winnings = betAmount + ((betAmount * 3)/2);
                    totalMoney = totalMoney + winnings;
                    if (leftCount != 0) {
                        rightSideBet.setText("Win 5-card bonus $" + winnings);
                    }
                    else {
                        betText.setText("Win 5-card bonus $" + winnings);
                    }
                }
            }
            else {
                if (rightCount > 21 & (rightCountAce > 21 | rightCountAce == 0)) {
                    if (leftCount != 0) {
                        rightSideBet.setText("Lose");
                    }
                    else {
                        betText.setText("Lose");
                    }
                }
                else {
                    if (computerCount > 21 & (computerCountAce > 21 | computerCountAce == 0)) {
                        if (doubleRightSide.equals("True")) {
                            winnings = betAmount * 4;
                        }
                        else {
                            winnings = betAmount * 2;
                        }
                        totalMoney = totalMoney + winnings;
                        if (leftCount != 0) {
                            rightSideBet.setText("Win $" + winnings);
                        }
                        else {
                            betText.setText("Win $" + winnings);
                        }
                    }
                    else {
                        if (computerCount > 21) {
                            //Use computerCountAce
                            if (rightCount > 21) {
                                //Use rightCountAce
                                if (computerCountAce > rightCountAce | computerCountAce < rightCountAce) {
                                    if (computerCountAce > rightCountAce) {
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Lose");
                                        }
                                        else {
                                            betText.setText("Lose");
                                        }
                                    }
                                    if (computerCountAce < rightCountAce) {
                                        if (doubleRightSide.equals("True")) {
                                            winnings = betAmount * 4;
                                        }
                                        else {
                                            winnings = betAmount * 2;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Win $" + winnings);
                                        }
                                        else {
                                            betText.setText("Win $" + winnings);
                                        }
                                    }
                                }
                                else {
                                    if (doubleRightSide.equals("True")) {
                                        winnings = betAmount * 2;
                                    }
                                    else {
                                        winnings = betAmount * 1;
                                    }
                                    totalMoney = totalMoney + winnings;
                                    if (leftCount != 0) {
                                        rightSideBet.setText("Tie. Push.");
                                    }
                                    else {
                                        betText.setText("Tie. Push.");
                                    }
                                }
                            }
                            else {
                                //Use rightCount
                                if (computerCountAce > rightCount | computerCountAce < rightCount) {
                                    if (computerCountAce > rightCount) {
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Lose");
                                        }
                                        else {
                                            betText.setText("Lose");
                                        }
                                    }
                                    if (computerCountAce < rightCount) {
                                        if (doubleRightSide.equals("True")) {
                                            winnings = betAmount * 4;
                                        }
                                        else {
                                            winnings = betAmount * 2;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Win $" + winnings);
                                        }
                                        else {
                                            betText.setText("Win $" + winnings);
                                        }
                                    }
                                }
                                else {
                                    if (doubleRightSide.equals("True")) {
                                        winnings = betAmount * 2;
                                    }
                                    else {
                                        winnings = betAmount * 1;
                                    }
                                    totalMoney = totalMoney + winnings;
                                    if (leftCount != 0) {
                                        rightSideBet.setText("Tie. Push.");
                                    }
                                    else {
                                        betText.setText("Tie. Push.");
                                    }
                                }
                            }
                        }
                        else {
                            //Use computerCount
                            if (rightCount > 21) {
                                //Use rightCountAce
                                if (computerCount > rightCountAce | computerCount < rightCountAce) {
                                    if (computerCount > rightCountAce) {
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Lose");
                                        }
                                        else {
                                            betText.setText("Lose");
                                        }
                                    }
                                    if (computerCount < rightCountAce) {
                                        if (doubleRightSide.equals("True")) {
                                            winnings = betAmount * 4;
                                        }
                                        else {
                                            winnings = betAmount * 2;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Win $" + winnings);
                                        }
                                        else {
                                            betText.setText("Win $" + winnings);
                                        }
                                    }
                                }
                                else {
                                    if (doubleRightSide.equals("True")) {
                                        winnings = betAmount * 2;
                                    }
                                    else {
                                        winnings = betAmount * 1;
                                    }
                                    totalMoney = totalMoney + winnings;
                                    if (leftCount != 0) {
                                        rightSideBet.setText("Tie. Push.");
                                    }
                                    else {
                                        betText.setText("Tie. Push.");
                                    }
                                }
                            }
                            else {
                                //Use rightCount
                                if (computerCount > rightCount | computerCount < rightCount) {
                                    if (computerCount > rightCount) {
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Lose");
                                        }
                                        else {
                                            betText.setText("Lose");
                                        }
                                    }
                                    if (computerCount < rightCount) {
                                        if (doubleRightSide.equals("True")) {
                                            winnings = betAmount * 4;
                                        }
                                        else {
                                            winnings = betAmount * 2;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        if (leftCount != 0) {
                                            rightSideBet.setText("Win $" + winnings);
                                        }
                                        else {
                                            betText.setText("Win $" + winnings);
                                        }
                                    }
                                }
                                else {
                                    if (doubleRightSide.equals("True")) {
                                        winnings = betAmount * 2;
                                    }
                                    else {
                                        winnings = betAmount * 1;
                                    }
                                    totalMoney = totalMoney + winnings;
                                    if (leftCount != 0) {
                                        rightSideBet.setText("Tie. Push.");
                                    }
                                    else {
                                        betText.setText("Tie. Push.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (leftCount != 0) {
            winnings = 0;
            if (computerSideBonus.equals("True") & leftSideBonus.equals("True")) {
                if (doubleLeftSide.equals("True")) {
                    winnings = betAmount * 2;
                }
                else {
                    winnings = betAmount * 1;
                }
                totalMoney = totalMoney + winnings;
                leftSideBet.setText("Tie. Push.");
            }
            else {
                if (computerSideBonus.equals("True") | leftSideBonus.equals("True")) {
                    if (computerSideBonus.equals("True")) {
                        leftSideBet.setText("Lose");
                    }
                    if (leftSideBonus.equals("True")) {
                        winnings = betAmount + ((betAmount * 3)/2);
                        totalMoney = totalMoney + winnings;
                        leftSideBet.setText("Win 5-card bonus $" + winnings);
                    }
                }
                else {
                    if (leftCount > 21 & (leftCountAce > 21 | leftCountAce == 0)) {
                        leftSideBet.setText("Lose");
                    }
                    else {
                        if (computerCount > 21 & (computerCountAce > 21 | computerCountAce == 0)) {
                            if (doubleLeftSide.equals("True")) {
                                winnings = betAmount * 4;
                            }
                            else {
                                winnings = betAmount * 2;
                            }
                            totalMoney = totalMoney + winnings;
                            leftSideBet.setText("Win $" + winnings);
                        }
                        else {
                            if (computerCount > 21) {
                                //Use computerCountAce
                                if (leftCount > 21) {
                                    //Use leftCountAce
                                    if (computerCountAce > leftCountAce | computerCountAce < leftCountAce) {
                                        if (computerCountAce > leftCountAce) {
                                            leftSideBet.setText("Lose");
                                        }
                                        if (computerCountAce < leftCountAce) {
                                            if (doubleLeftSide.equals("True")) {
                                                winnings = betAmount * 4;
                                            }
                                            else {
                                                winnings = betAmount * 2;
                                            }
                                            totalMoney = totalMoney + winnings;
                                            leftSideBet.setText("Win $" + winnings);
                                        }
                                    }
                                    else {
                                        if (doubleLeftSide.equals("True")) {
                                            winnings = betAmount * 2;
                                        }
                                        else {
                                            winnings = betAmount * 1;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        leftSideBet.setText("Tie. Push.");
                                    }
                                }
                                else {
                                    //Use leftCount
                                    if (computerCountAce > leftCount | computerCountAce < leftCount) {
                                        if (computerCountAce > leftCount) {
                                            leftSideBet.setText("Lose");
                                        }
                                        if (computerCountAce < leftCount) {
                                            if (doubleLeftSide.equals("True")) {
                                                winnings = betAmount * 4;
                                            }
                                            else {
                                                winnings = betAmount * 2;
                                            }
                                            totalMoney = totalMoney + winnings;
                                            leftSideBet.setText("Win $" + winnings);
                                        }
                                    }
                                    else {
                                        if (doubleLeftSide.equals("True")) {
                                            winnings = betAmount * 2;
                                        }
                                        else {
                                            winnings = betAmount * 1;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        leftSideBet.setText("Tie. Push.");
                                    }
                                }
                            }
                            else {
                                //Use computerCount
                                if (leftCount > 21) {
                                    //Use leftCountAce
                                    if (computerCount > leftCountAce | computerCount < leftCountAce) {
                                        if (computerCount > leftCountAce) {
                                            leftSideBet.setText("Lose");
                                        }
                                        if (computerCount < leftCountAce) {
                                            if (doubleLeftSide.equals("True")) {
                                                winnings = betAmount * 4;
                                            }
                                            else {
                                                winnings = betAmount * 2;
                                            }
                                            totalMoney = totalMoney + winnings;
                                            leftSideBet.setText("Win $" + winnings);
                                        }
                                    }
                                    else {
                                        if (doubleLeftSide.equals("True")) {
                                            winnings = betAmount * 2;
                                        }
                                        else {
                                            winnings = betAmount * 1;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        leftSideBet.setText("Tie. Push.");
                                    }
                                }
                                else {
                                    //Use leftCount
                                    if (computerCount > leftCount | computerCount < leftCount) {
                                        if (computerCount > leftCount) {
                                            leftSideBet.setText("Lose");
                                        }
                                        if (computerCount < leftCount) {
                                            if (doubleLeftSide.equals("True")) {
                                                winnings = betAmount * 4;
                                            }
                                            else {
                                                winnings = betAmount * 2;
                                            }
                                            totalMoney = totalMoney + winnings;
                                            leftSideBet.setText("Win $" + winnings);
                                        }
                                    }
                                    else {
                                        if (doubleLeftSide.equals("True")) {
                                            winnings = betAmount * 2;
                                        }
                                        else {
                                            winnings = betAmount * 1;
                                        }
                                        totalMoney = totalMoney + winnings;
                                        leftSideBet.setText("Tie. Push.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        totalMoneyTextBlackjack.setText("$" + totalMoney);
        saveTotalMoney();
        restartBlackjack();
    }

    protected void restartBlackjack() {
        final TextView dealerText = findViewById(R.id.dealerText);
        final TextView totalMoneyTextBlackjack = findViewById(R.id.totalMoneyTextBlackjack);
        final TextView rightCountText = findViewById(R.id.rightSideCount);
        final TextView leftCountText = findViewById(R.id.leftSideCount);
        final TextView dealerCountText = findViewById(R.id.dealerCount);
        final TextView rightSideBet = findViewById(R.id.splitRightText);
        final TextView leftSideBet = findViewById(R.id.splitLeftText);
        final TextView betText = findViewById(R.id.betText);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);
        final Button split = findViewById(R.id.splitButton);
        final Button betButton = findViewById(R.id.betButton);

        hit.setVisibility(View.GONE);
        stand.setVisibility(View.GONE);
        split.setVisibility(View.GONE);
        betButton.setVisibility(View.GONE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dealerText.setText("");
                leftCountText.setText("");
                rightCountText.setText("");
                rightSideBet.setText("");
                leftSideBet.setText("");
                betText.setText("");
                dealerCountText.setText("");
                leftCountText.setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.playerCardLeft1)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardLeft2)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardLeft3)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardLeft4)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardLeft5)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardRight1)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardRight2)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardRight3)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardRight4)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardRight5)).setImageResource(0);
                ((ImageView) findViewById(R.id.playerCardMiddle)).setImageResource(0);
                ((ImageView) findViewById(R.id.computerCard1)).setImageResource(0);
                ((ImageView) findViewById(R.id.computerCard2)).setImageResource(0);
                ((ImageView) findViewById(R.id.computerCard3)).setImageResource(0);
                ((ImageView) findViewById(R.id.computerCard4)).setImageResource(0);
                ((ImageView) findViewById(R.id.computerCard5)).setImageResource(0);
                betAmount = 0;
                cardCounter = 0;
                insuranceAmount = 0;
                insuranceCheck = "True";
                rightCount = 0;
                rightCountAce = 0;
                leftCount = 0;
                leftCountAce = 0;
                computerCount = 0;
                computerCountAce = 0;
                winnings = 0;
                blackjack = "False";
                doubleAmount = 0;
                aceCounter = 0;
                computerSideBonus = "False";
                rightSideBonus = "False";
                leftSideBonus = "False";
                doubleLeftSide = "False";
                doubleRightSide = "False";
                playBlackjack();
            }
        }, 3500);
    }

    public void saveTotalMoney() {
        SharedPreferences sharedPreferences = getSharedPreferences("TotalMoney", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(INT, totalMoney);
        editor.apply();
    }
}

