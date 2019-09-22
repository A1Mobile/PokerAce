package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.min;
import static java.lang.StrictMath.round;

public class ComputerAction extends GetResults {
    protected int randomHolder;
    protected int handStrengthPreFlop;
    protected String checkBack = "True";
    protected int whoseBlind = 0;
    protected int playerStack = 5000;
    protected int computerStack = 5000;
    protected int playerMoneyInPot = 0;
    protected int computerMoneyInPot = 0;
    protected int moneyInPot = 0;
    protected int callHolder = 0;
    //Turn = 0 for computer's hand; Turn = 1 for player's hand; Turn = 2 for move on; Turn = 3 for fold
    protected int turn = 1;
    protected String whoFolded = "Player";
    protected String allIn = "False";
    protected int RaiseAmount;
    protected static SeekBar seekBarRaise;
    protected static Button raise;
    protected int tooMuch;
    String player = "Player";
    protected int raiseAmount;
    protected String raiseCheck = "True";
    protected int minRaiseNumber = 0;
    protected int computerMoneyInPotPreFlop = 0;
    protected int playerMoneyInPotPreFlop = 0;
    protected int computerMoneyInPotAfter = 0;
    protected int playerMoneyInPotAfter = 0;
    protected String whoWon = "";
    protected int justHolder = 0;
    protected Integer[] comparisonHolderPlayer = new Integer[5];
    protected Integer[] comparisonHolderComputer = new Integer[5];
    protected String raiseButtonColor = "True";
    protected String onPreFlop = "True";
    protected int bigBlind = 50;
    protected int smallBlind = 25;
    protected int progressHolder = 0;
    protected int progressValue = 0;
    protected int seekBarCounter = 0;
    protected int allInMoneyCount = 0;
    protected String INT = "int";

    //Starts action preflop
    protected void startAction() {
        final Button playerCallButton = (Button) findViewById(R.id.call);
        final Button playerRaiseButton = (Button) findViewById(R.id.raise);
        final Button playerFoldButton = (Button) findViewById(R.id.fold);
        final TextView playerName = findViewById(R.id.player);
        final TextView computerName = findViewById(R.id.computer);

        if (turn == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ComputerPreFlop();
                    TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
                    callHolder = (playerMoneyInPot - computerMoneyInPot);
                    computerActionText.setText("To call $" + callHolder);
                    computerName.setTextColor(getResources().getColor(R.color.green));
                }
            }, 2000);
        }

        if (turn == 1) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            raiseButton();
            playerCallButton.setClickable(true);
            playerFoldButton.setClickable(true);
            int callHolder = 0;
            callHolder = computerMoneyInPot - playerMoneyInPot;
            if (callHolder == 0) {
                playerCallButton.setText("Check");
            }
            else {
                playerCallButton.setText("Call");
            }

            if (computerStack == 0) {
                raiseButtonColor = "False";
            }

            playerName.setTextColor(getResources().getColor(R.color.green));
            playerCallButton.setTextColor(getResources().getColor(R.color.green));
            playerFoldButton.setTextColor(getResources().getColor(R.color.green));
            if (raiseButtonColor == "True") {
                playerRaiseButton.setTextColor(getResources().getColor(R.color.green));
            }

            playerCallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerCalls();
                    startAction();
                }
            });

            playerRaiseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (computerStack != 0) {
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerName.setTextColor(getResources().getColor(R.color.black));
                        playerCallButton.setTextColor(getResources().getColor(R.color.red));
                        playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                        playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                        raiseButtonColor = "True";
                        playerRaises();
                        startAction();
                    }
                }
            });

            playerFoldButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerFolds();
                    startAction();
                }
            });

            if (computerStack == 0) {
                playerRaiseButton.setClickable(false);
            }
            else {
                playerRaiseButton.setClickable(true);
            }
        }

        if (turn == 2 & allIn.equals("False")) {
            computerName.setTextColor(getResources().getColor(R.color.black));

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.flop1)).setImageResource(getResources().getIdentifier(cardHolder5,"drawable",getPackageName()));
                    ((ImageView) findViewById(R.id.flop2)).setImageResource(getResources().getIdentifier(cardHolder6,"drawable",getPackageName()));
                    ((ImageView) findViewById(R.id.flop3)).setImageResource(getResources().getIdentifier(cardHolder7,"drawable",getPackageName()));

                    final TextView playerActionText = findViewById(R.id.playerActionText);
                    final TextView computerActionText = findViewById(R.id.computerActionText);

                    playerActionText.setText("");
                    computerActionText.setText("");
                }
            }, 2000);   //2 seconds
            turn = whoseBlind;
            checkBack = "True";
            onPreFlop = "False";
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActionFlop();
                }
            }, 2050);
        }

        if (turn == 2 & allIn.equals("True")) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(getResources().getIdentifier(cardHolder3, "drawable", getPackageName()));
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolder4, "drawable", getPackageName()));

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.flop1)).setImageResource(getResources().getIdentifier(cardHolder5,"drawable",getPackageName()));
                    ((ImageView) findViewById(R.id.flop2)).setImageResource(getResources().getIdentifier(cardHolder6,"drawable",getPackageName()));
                    ((ImageView) findViewById(R.id.flop3)).setImageResource(getResources().getIdentifier(cardHolder7,"drawable",getPackageName()));
                }
            }, 2000);   //2 seconds

            //Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.turn)).setImageResource(getResources().getIdentifier(cardHolder8,"drawable",getPackageName()));
                }
            }, 6000);   //4 seconds

            //Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.river)).setImageResource(getResources().getIdentifier(cardHolder9,"drawable",getPackageName()));
                }
            }, 10000);   //6 seconds

            //Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    getResults();
                }
            }, 12000);   //2 seconds

            //endGame();
        }

        if (turn == 3) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    getFoldResults();
                }
            }, 2000); //2 seconds
        }
    }


    //Starts action after the flop
    protected void startActionFlop() {
        final Button playerCallButton = (Button) findViewById(R.id.call);
        final Button playerRaiseButton = (Button) findViewById(R.id.raise);
        final Button playerFoldButton = (Button) findViewById(R.id.fold);
        final TextView playerName = findViewById(R.id.player);
        final TextView computerName = findViewById(R.id.computer);

        if (turn == 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ComputerFlop();
                    TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
                    callHolder = (playerMoneyInPot - computerMoneyInPot);
                    computerActionText.setText("To call $" + callHolder);
                    computerName.setTextColor(getResources().getColor(R.color.green));
                }
            }, 2000);
        }

        if (turn == 0) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            raiseButton();
            playerCallButton.setClickable(true);
            playerFoldButton.setClickable(true);
            int callHolder = 0;
            callHolder = computerMoneyInPot - playerMoneyInPot;
            if (callHolder == 0) {
                playerCallButton.setText("Check");
            }
            else {
                playerCallButton.setText("Call");
            }

            if (computerStack == 0) {
                playerRaiseButton.setClickable(false);
                raiseButtonColor = "False";
            }
            else {
                playerRaiseButton.setClickable(true);
            }

            playerName.setTextColor(getResources().getColor(R.color.green));
            playerCallButton.setTextColor(getResources().getColor(R.color.green));
            playerFoldButton.setTextColor(getResources().getColor(R.color.green));
            if (raiseButtonColor == "True") {
                playerRaiseButton.setTextColor(getResources().getColor(R.color.green));
            }

            playerCallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerCallsAfter();
                    startActionFlop();
                }
            });

            playerRaiseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (computerStack != 0) {
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerName.setTextColor(getResources().getColor(R.color.black));
                        playerCallButton.setTextColor(getResources().getColor(R.color.red));
                        playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                        playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                        raiseButtonColor = "True";
                        playerRaisesAfter();
                        startActionFlop();
                    }
                }
            });

            playerFoldButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerFolds();
                    startActionFlop();
                }
            });
        }

        if (turn == 2 & allIn.equals("False")) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.turn)).setImageResource(getResources().getIdentifier(cardHolder8,"drawable",getPackageName()));
                    final TextView playerActionText = findViewById(R.id.playerActionText);
                    final TextView computerActionText = findViewById(R.id.computerActionText);

                    playerActionText.setText("");
                    computerActionText.setText("");
                }
            }, 2000);   //2 seconds
            turn = whoseBlind;
            checkBack = "True";
            playerMoneyInPotAfter = 0;
            computerMoneyInPotAfter = 0;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActionTurn();
                }
            }, 2050);
        }

        if (turn == 2 & allIn.equals("True")) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(getResources().getIdentifier(cardHolder3, "drawable", getPackageName()));
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolder4, "drawable", getPackageName()));

            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.turn)).setImageResource(getResources().getIdentifier(cardHolder8,"drawable",getPackageName()));
                }
            }, 3000);   //2 seconds

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.river)).setImageResource(getResources().getIdentifier(cardHolder9,"drawable",getPackageName()));
                }
            }, 6000);   //2 seconds

            Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                public void run() {
                    getResults();
                }
            }, 8000);   //2 seconds
            //endGame();
        }

        if (turn == 3) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    getFoldResults();
                }
            }, 2000); //2 seconds
        }
    }

    protected void startActionTurn() {
        final Button playerCallButton = (Button) findViewById(R.id.call);
        final Button playerRaiseButton = (Button) findViewById(R.id.raise);
        final Button playerFoldButton = (Button) findViewById(R.id.fold);
        final TextView playerName = findViewById(R.id.player);
        final TextView computerName = findViewById(R.id.computer);

        if (turn == 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ComputerTurn();
                    TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
                    callHolder = (playerMoneyInPot - computerMoneyInPot);
                    computerActionText.setText("To call $" + callHolder);
                    computerName.setTextColor(getResources().getColor(R.color.green));
                }
            }, 2000);
        }

        if (turn == 0) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            raiseButton();
            playerCallButton.setClickable(true);
            playerFoldButton.setClickable(true);
            int callHolder = 0;
            callHolder = computerMoneyInPot - playerMoneyInPot;
            if (callHolder == 0) {
                playerCallButton.setText("Check");
            }
            else {
                playerCallButton.setText("Call");
            }

            if (computerStack == 0) {
                playerRaiseButton.setClickable(false);
                raiseButtonColor = "False";
            }
            else {
                playerRaiseButton.setClickable(true);
            }

            playerName.setTextColor(getResources().getColor(R.color.green));
            playerCallButton.setTextColor(getResources().getColor(R.color.green));
            playerFoldButton.setTextColor(getResources().getColor(R.color.green));
            if (raiseButtonColor == "True") {
                playerRaiseButton.setTextColor(getResources().getColor(R.color.green));
            }


            playerCallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerCallsAfter();
                    startActionTurn();
                }
            });

            playerRaiseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (computerStack != 0) {
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerName.setTextColor(getResources().getColor(R.color.black));
                        playerCallButton.setTextColor(getResources().getColor(R.color.red));
                        playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                        playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                        raiseButtonColor = "True";
                        playerRaisesAfter();
                        startActionTurn();
                    }
                }
            });

            playerFoldButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerFolds();
                    startActionTurn();
                }
            });
        }

        if (turn == 2 & allIn.equals("False")) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.river)).setImageResource(getResources().getIdentifier(cardHolder9,"drawable",getPackageName()));
                    final TextView playerActionText = findViewById(R.id.playerActionText);
                    final TextView computerActionText = findViewById(R.id.computerActionText);

                    playerActionText.setText("");
                    computerActionText.setText("");
                }
            }, 2000);   //2 seconds
            turn = whoseBlind;
            checkBack = "True";
            playerMoneyInPotAfter = 0;
            computerMoneyInPotAfter = 0;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActionRiver();
                }
            }, 2050);
        }

        if (turn == 2 & allIn.equals("True")) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(getResources().getIdentifier(cardHolder3, "drawable", getPackageName()));
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolder4, "drawable", getPackageName()));

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.river)).setImageResource(getResources().getIdentifier(cardHolder9,"drawable",getPackageName()));
                }
            }, 3000);   //2 seconds

            Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                public void run() {
                    getResults();
                }
            }, 5000);   //2 seconds
            //endGame();
        }

        if (turn == 3) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    getFoldResults();
                }
            }, 2000); //2 seconds
        }
    }

    protected void startActionRiver() {
        final Button playerCallButton = (Button) findViewById(R.id.call);
        final Button playerRaiseButton = (Button) findViewById(R.id.raise);
        final Button playerFoldButton = (Button) findViewById(R.id.fold);
        final TextView playerName = findViewById(R.id.player);
        final TextView computerName = findViewById(R.id.computer);

        if (turn == 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ComputerRiver();
                    TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
                    callHolder = (playerMoneyInPot - computerMoneyInPot);
                    computerActionText.setText("To call $" + callHolder);
                    computerName.setTextColor(getResources().getColor(R.color.green));
                }
            }, 2000);
        }

        if (turn == 0) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            raiseButton();
            playerCallButton.setClickable(true);
            playerFoldButton.setClickable(true);
            int callHolder = 0;
            callHolder = computerMoneyInPot - playerMoneyInPot;
            if (callHolder == 0) {
                playerCallButton.setText("Check");
            }
            else {
                playerCallButton.setText("Call");
            }

            if (computerStack == 0) {
                playerRaiseButton.setClickable(false);
                raiseButtonColor = "False";
            }
            else {
                playerRaiseButton.setClickable(true);
            }

            playerName.setTextColor(getResources().getColor(R.color.green));
            playerCallButton.setTextColor(getResources().getColor(R.color.green));
            playerFoldButton.setTextColor(getResources().getColor(R.color.green));
            if (raiseButtonColor == "True") {
                playerRaiseButton.setTextColor(getResources().getColor(R.color.green));
            }

            playerCallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerCallsAfter();
                    startActionRiver();
                }
            });

            playerRaiseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (computerStack != 0) {
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerCallButton.setClickable(false);
                        playerFoldButton.setClickable(false);
                        playerRaiseButton.setClickable(false);
                        playerName.setTextColor(getResources().getColor(R.color.black));
                        playerCallButton.setTextColor(getResources().getColor(R.color.red));
                        playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                        playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                        raiseButtonColor = "True";
                        playerRaisesAfter();
                        startActionRiver();
                    }
                }
            });

            playerFoldButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerCallButton.setClickable(false);
                    playerFoldButton.setClickable(false);
                    playerRaiseButton.setClickable(false);
                    playerName.setTextColor(getResources().getColor(R.color.black));
                    playerCallButton.setTextColor(getResources().getColor(R.color.red));
                    playerFoldButton.setTextColor(getResources().getColor(R.color.red));
                    playerRaiseButton.setTextColor(getResources().getColor(R.color.red));
                    raiseButtonColor = "True";
                    playerFolds();
                    startActionRiver();
                }
            });
        }

        if (turn == 2) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((ImageView) findViewById(R.id.computerCard1)).setImageResource(getResources().getIdentifier(cardHolder3, "drawable", getPackageName()));
                    ((ImageView) findViewById(R.id.computerCard2)).setImageResource(getResources().getIdentifier(cardHolder4, "drawable", getPackageName()));

                    getResults();
                }
            }, 2000);   //2 seconds
        }

        if (turn == 3) {
            computerName.setTextColor(getResources().getColor(R.color.black));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    getFoldResults();
                }
            }, 2000); //2 seconds
        }
    }

    //Gets fold results
    protected void getFoldResults() {
        if (whoFolded == player) {
            TextView potSize1 = (TextView) findViewById(R.id.potSize);
            TextView playerActionText1 = (TextView) findViewById(R.id.playerActionText);
            TextView computerActionText1 = (TextView) findViewById(R.id.computerActionText);
            TextView computerStackText1 = (TextView) findViewById(R.id.computerStack);
            TextView playerStackText1 = (TextView) findViewById(R.id.playerStack);

            potSize1.setText("Pot: $0");
            playerActionText1.setText("You Lost");
            computerActionText1.setText("Computer Won");

            if (playerMoneyInPot > computerMoneyInPot) {
                tooMuch = playerMoneyInPot - computerMoneyInPot;
                playerStack = playerStack + tooMuch;
                totalMoney = totalMoney + tooMuch;
                moneyInPot = moneyInPot - tooMuch;
                saveTotalMoney();
            }

            computerStack = computerStack + moneyInPot;
            computerStackText1.setText("$" + computerStack);
            playerStackText1.setText("$" + playerStack);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    endGame();
                }
            }, 3000); //4 seconds
        } else {
            TextView potSize2 = (TextView) findViewById(R.id.potSize);
            TextView playerActionText2 = (TextView) findViewById(R.id.playerActionText);
            TextView computerActionText2 = (TextView) findViewById(R.id.computerActionText);
            TextView computerStackText2 = (TextView) findViewById(R.id.computerStack);
            TextView playerStackText2 = (TextView) findViewById(R.id.playerStack);

            potSize2.setText("Pot: $0");
            playerActionText2.setText("You Won");
            computerActionText2.setText("Computer Lost");

            if (computerMoneyInPot > playerMoneyInPot) {
                tooMuch = computerMoneyInPot - playerMoneyInPot;
                computerStack = computerStack + tooMuch;
                moneyInPot = moneyInPot - tooMuch;
            }

            playerStack = playerStack + moneyInPot;
            totalMoney = totalMoney + moneyInPot;
            computerStackText2.setText("$" + computerStack);
            playerStackText2.setText("$" + playerStack);
            saveTotalMoney();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    endGame();
                }
            }, 3000); //4 seconds
        }
    }

    protected void playerCalls() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView playerActionText = (TextView) findViewById(R.id.playerActionText);
        TextView playerStackText = (TextView) findViewById(R.id.playerStack);

        callHolder = (computerMoneyInPot - playerMoneyInPot);
        if (callHolder >= playerStack) {
            callHolder = playerStack;
            playerMoneyInPotPreFlop = playerMoneyInPotPreFlop + callHolder;
            playerActionText.setText("All In $" + playerMoneyInPotPreFlop);
            allIn = "True";
        } else {
            if (callHolder != 0) {
                playerMoneyInPotPreFlop = playerMoneyInPotPreFlop + callHolder;
                playerActionText.setText("Call $" + playerMoneyInPotPreFlop);
            }
            if (callHolder == 0) {
                playerActionText.setText("Check");
            }
        }

        totalMoney = totalMoney - callHolder;
        moneyInPot = moneyInPot + callHolder;
        playerMoneyInPot = playerMoneyInPot + callHolder;
        playerStack = playerStack - callHolder;
        saveTotalMoney();

        playerStackText.setText("$" + playerStack);
        potSize.setText("Pot: $" + moneyInPot);

        if (checkBack.equals("True")) {
            turn = 0;
            checkBack = "False";
        } else {
            turn = 2;
        }
    }

    protected void playerRaises() {
        seekBarRaise = (SeekBar) findViewById(R.id.seekBar);
        raiseAmount = progressValue;
        callHolder = (computerMoneyInPot - playerMoneyInPot);
        if (raiseAmount < callHolder * 2 & raiseAmount != 0) {
            startAction();
        } else {
            callHolder = raiseAmount - playerMoneyInPotPreFlop;
            TextView potSize = (TextView) findViewById(R.id.potSize);
            TextView playerActionText = (TextView) findViewById(R.id.playerActionText);
            TextView playerStackText = (TextView) findViewById(R.id.playerStack);

            if (callHolder >= playerStack) {
                callHolder = playerStack;
                playerMoneyInPotPreFlop = playerMoneyInPotPreFlop + callHolder;
                playerActionText.setText("All In $" + playerMoneyInPotPreFlop);
                allIn = "True";
                allInMoneyCount = playerMoneyInPotPreFlop - computerMoneyInPotPreFlop;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    turn = 2;
                }
            } else {
                playerMoneyInPotPreFlop = playerMoneyInPotPreFlop + callHolder;
                if (computerMoneyInPot - playerMoneyInPot == 0 & onPreFlop == "False") {
                    playerActionText.setText("Bet $" + playerMoneyInPotPreFlop);
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    playerActionText.setText("Raise to $" + playerMoneyInPotPreFlop);
                    checkBack = "False";
                    turn = 0;
                }
            }

            moneyInPot = moneyInPot + callHolder;
            playerMoneyInPot = playerMoneyInPot + callHolder;
            playerStack = playerStack - callHolder;
            totalMoney = totalMoney - callHolder;
            saveTotalMoney();

            playerStackText.setText("$" + playerStack);
            potSize.setText("Pot: $" + moneyInPot);
        }
    }

    protected void playerRaisesAfter() {
        seekBarRaise = (SeekBar) findViewById(R.id.seekBar);
        raiseAmount = progressValue;
        callHolder = (computerMoneyInPot - playerMoneyInPot);
        if (raiseAmount < callHolder * 2 & raiseAmount != 0) {
            startAction();
        } else {
            callHolder = raiseAmount - playerMoneyInPotAfter;
            TextView potSize = (TextView) findViewById(R.id.potSize);
            TextView playerActionText = (TextView) findViewById(R.id.playerActionText);
            TextView playerStackText = (TextView) findViewById(R.id.playerStack);

            if (callHolder >= playerStack) {
                callHolder = playerStack;
                playerMoneyInPotAfter = playerMoneyInPotAfter + callHolder;
                playerActionText.setText("All In $" + playerMoneyInPotAfter);
                allIn = "True";
                allInMoneyCount = playerMoneyInPotAfter - computerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 1;
                }
                else {
                    turn = 2;
                }
            } else {
                playerMoneyInPotAfter = playerMoneyInPotAfter + callHolder;
                if (computerMoneyInPot - playerMoneyInPot == 0 & onPreFlop == "False") {
                    playerActionText.setText("Bet $" + playerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 1;
                }
                else {
                    playerActionText.setText("Raise to $" + playerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 1;
                }
            }

            moneyInPot = moneyInPot + callHolder;
            playerMoneyInPot = playerMoneyInPot + callHolder;
            playerStack = playerStack - callHolder;
            totalMoney = totalMoney - callHolder;
            saveTotalMoney();

            playerStackText.setText("$" + playerStack);
            potSize.setText("Pot: $" + moneyInPot);
        }
    }

    protected void playerCallsAfter() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView playerActionText = (TextView) findViewById(R.id.playerActionText);
        TextView playerStackText = (TextView) findViewById(R.id.playerStack);

        callHolder = (computerMoneyInPot - playerMoneyInPot);
        if (callHolder >= playerStack) {
            callHolder = playerStack;
            playerMoneyInPotAfter = playerMoneyInPotAfter + callHolder;
            playerActionText.setText("All In $" + playerMoneyInPotAfter);
            allIn = "True";
        } else {
            if (callHolder != 0) {
                playerMoneyInPotAfter = playerMoneyInPotAfter + callHolder;
                playerActionText.setText("Call $" + playerMoneyInPotAfter);
            }
            if (callHolder == 0) {
                playerActionText.setText("Check");
            }
        }

        moneyInPot = moneyInPot + callHolder;
        playerMoneyInPot = playerMoneyInPot + callHolder;
        playerStack = playerStack - callHolder;
        totalMoney = totalMoney - callHolder;
        saveTotalMoney();

        playerStackText.setText("$" + playerStack);
        potSize.setText("Pot: $" + moneyInPot);

        if (checkBack.equals("True")) {
            turn = 1;
            checkBack = "False";
        } else {
            turn = 2;
        }
    }

    protected void playerFolds() {
        TextView playerActionText = (TextView) findViewById(R.id.playerActionText);
        playerActionText.setText("Fold");
        whoFolded = "Player";
        turn = 3;
    }

    protected void raiseButton() {
        final int toCall;
        toCall = computerMoneyInPot - playerMoneyInPot;
        TextView playerActionToCall = (TextView) findViewById(R.id.playerActionText);

        playerActionToCall.setText("To call $" + toCall);

        seekBarRaise = (SeekBar) findViewById(R.id.seekBar);
        raise = (Button) findViewById(R.id.raise);
        if ((computerMoneyInPot - playerMoneyInPot) >= playerMoneyInPot) {
            raiseCheck = "False";
        } else {
            raiseCheck = "True";
        }
        // if (raiseCheck == "True") {
        if (minRaiseNumber > playerStack) {
            minRaiseNumber = playerStack;
        }
        minRaiseNumber = (2 * (computerMoneyInPot - playerMoneyInPot));
        if (playerStack < 25) {
            minRaiseNumber = playerStack;
        }
        if (minRaiseNumber == 0) {
            minRaiseNumber = 25;
        }

        int callHolder = computerMoneyInPot - playerMoneyInPot;
        int raiseTo = 0;
        raiseTo = minRaiseNumber + callHolder;
        seekBarRaise.setProgress(0);

        seekBarCounter = (playerStack - minRaiseNumber)/25;
        seekBarRaise.setMax(seekBarCounter);
        seekBarRaise.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int callHolder = computerMoneyInPot - playerMoneyInPot;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (onPreFlop.equals("True")) {
                            if (progress == seekBarCounter) {
                                progressValue = playerStack + playerMoneyInPotPreFlop;
                            }
                            else {
                                progressValue = (progress * 25) + minRaiseNumber + playerMoneyInPotPreFlop;
                            }
                        }
                        else {
                            if (progress == seekBarCounter) {
                                progressValue = playerStack + playerMoneyInPotAfter;
                            }
                            else {
                                progressValue = (progress * 25) + minRaiseNumber + playerMoneyInPotAfter;
                            }
                        }
                        progressHolder = (progress*25);
                        if (callHolder == 0 & onPreFlop.equals("False")) {
                            raise.setText("Bet $" + progressValue);
                        }
                        else {
                            if (progressValue >= playerStack) {
                                raise.setText("All In");
                            }
                            else {
                                raise.setText("Raise to $" + progressValue);
                            }
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        if (onPreFlop.equals("True")) {
                            if ((progressHolder/25) == seekBarCounter) {
                                progressValue = playerStack + playerMoneyInPotPreFlop;
                            }
                            else {
                                progressValue = progressHolder + minRaiseNumber + playerMoneyInPotPreFlop;
                            }
                        }
                        else {
                            if ((progressHolder/25) == seekBarCounter) {
                                progressValue = playerStack + playerMoneyInPotAfter;
                            }
                            else {
                                progressValue = progressHolder + minRaiseNumber + playerMoneyInPotAfter;
                            }
                        }
                        if (callHolder == 0 & onPreFlop.equals("False")) {
                            raise.setText("Bet $" + progressValue);
                        }
                        else {
                            if (progressValue >= playerStack) {
                                raise.setText("All In");
                            }
                            else {
                                raise.setText("Raise to $" + progressValue);
                            }
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (onPreFlop.equals("True")) {
                            if ((progressHolder/25) == seekBarCounter) {
                                progressValue = playerStack + playerMoneyInPotPreFlop;
                            }
                            else {
                                progressValue = progressHolder + minRaiseNumber + playerMoneyInPotPreFlop;
                            }
                        }
                        else {
                            if ((progressHolder/25) == seekBarCounter) {
                                progressValue = playerStack + playerMoneyInPotAfter;
                            }
                            else {
                                progressValue = progressHolder + minRaiseNumber + playerMoneyInPotAfter;
                            }
                        }
                        if (callHolder == 0 & onPreFlop.equals("False")) {
                            raise.setText("Bet $" + progressValue);
                        }
                        else {
                            if (progressValue >= playerStack) {
                                raise.setText("All In");
                            }
                            else {
                                raise.setText("Raise to $" + progressValue);
                            }
                        }
                    }
                }
        );
        seekBarRaise.setProgress(0);
        if (callHolder == 0 & onPreFlop.equals("False")) {
            raise.setText("Bet");
        }
        else {
            if (progressValue >= playerStack) {
                raise.setText("All In");
            }
            else {
                raise.setText("Raise");
            }
        }
        seekBarRaise.setProgress(1);
        seekBarRaise.setProgress(0);
    }

    //Collects the blinds at the start of the game (View view1, View view2, View view3)
    protected void collectBlinds() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView playerActionText = (TextView) findViewById(R.id.playerActionText);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        TextView playerStackText = (TextView) findViewById(R.id.playerStack);
        if (whoseBlind == 0) {
            if (smallBlind >= playerStack) {
                smallBlind = playerStack;
                turn = 2;
                allIn = "True";
            }
            if (bigBlind >= computerStack) {
                bigBlind = computerStack;
                turn = 2;
                allIn = "True";
            }
            whoseBlind = 1;
            playerMoneyInPot = playerMoneyInPot + smallBlind;
            computerMoneyInPot = computerMoneyInPot + bigBlind;
            moneyInPot = playerMoneyInPot + computerMoneyInPot;
            computerStack = computerStack - bigBlind;
            playerStack = playerStack - smallBlind;
            totalMoney = totalMoney - smallBlind;
            saveTotalMoney();
            playerMoneyInPotPreFlop = playerMoneyInPotPreFlop + smallBlind;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + bigBlind;
            playerActionText.setText("Small Blind $" + smallBlind);
            computerActionText.setText("Big Blind $" + bigBlind);
            computerStackText.setText("$" + computerStack);
            playerStackText.setText("$" + playerStack);
            ((ImageView) findViewById(R.id.dealerChipPlayer)).setImageResource(R.drawable.dealerchip);
        } else {
            if (bigBlind >= playerStack) {
                bigBlind = playerStack;
                turn = 2;
                allIn = "True";
            }
            if (smallBlind >= computerStack) {
                smallBlind = computerStack;
                turn = 2;
                allIn = "True";
            }
            whoseBlind = 0;
            playerMoneyInPot = playerMoneyInPot + bigBlind;
            computerMoneyInPot = computerMoneyInPot + smallBlind;
            moneyInPot = playerMoneyInPot + computerMoneyInPot;
            computerStack = computerStack - smallBlind;
            playerStack = playerStack - bigBlind;
            totalMoney = totalMoney - bigBlind;
            saveTotalMoney();
            playerMoneyInPotPreFlop = playerMoneyInPotPreFlop + bigBlind;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + smallBlind;
            playerActionText.setText("Big Blind $" + bigBlind);
            computerActionText.setText("Small Blind $" + smallBlind);
            computerStackText.setText("$" + computerStack);
            playerStackText.setText("$" + playerStack);
            ((ImageView) findViewById(R.id.dealerChipComputer)).setImageResource(R.drawable.dealerchip);
        }
        potSize.setText("Pot: $" + moneyInPot);
        if (whoseBlind == 1) {
            turn = 1;
        }
        if (whoseBlind == 0) {
            turn = 0;
        }
    }

    //Checks hand strength of computer's cards pre-flop (1-8) higher number better hand
    protected void CheckHandStrengthPreFlop() {
        handStrengthPreFlop = 0;
        //Checks for jacks, queens, kings, and aces (aces +2) for the first card
        if (card3.getFaceValue() == 11 | card3.getFaceValue() == 12 | card3.getFaceValue() == 13 | card3.getFaceValue() == 14) {
            handStrengthPreFlop = handStrengthPreFlop + 1;
            if (card3.getFaceValue() == 14) {
                handStrengthPreFlop = handStrengthPreFlop + 1;
            }
        }
        //Checks for jacks, queens, kings, and aces (aces +2) for the second card
        if (card4.getFaceValue() == 11 | card4.getFaceValue() == 12 | card4.getFaceValue() == 13 | card4.getFaceValue() == 14) {
            handStrengthPreFlop = handStrengthPreFlop + 1;
            if (card4.getFaceValue() == 14) {
                handStrengthPreFlop = handStrengthPreFlop + 1;
            }
        }
        //Checks the closeness of the cards
        if (java.lang.Math.abs(card3.getFaceValue() - card4.getFaceValue()) == 1) {
            handStrengthPreFlop = handStrengthPreFlop + 2;
        }
        //Checks the closeness of the cards
        if (java.lang.Math.abs(card3.getFaceValue() - card4.getFaceValue()) == 2) {
            handStrengthPreFlop = handStrengthPreFlop + 1;
        }
        //Checks if the suit of the cards are the same
        if (card3.getSuit().equals(card4.getSuit())) {
            handStrengthPreFlop = handStrengthPreFlop + 2;
        }
        //Checks for pocket pairs
        if (card3.getFaceValue() == card4.getFaceValue()) {
            if (card3.getFaceValue() == 10 | card3.getFaceValue() == 11 | card3.getFaceValue() == 12 | card3.getFaceValue() == 13 | card3.getFaceValue() == 14) {
                handStrengthPreFlop = 8;
            } else {
                handStrengthPreFlop = 6;
            }
        }
    }

    protected void ComputerPreFlop() {
        CheckHandStrengthPreFlop();

        if (handStrengthPreFlop == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction0();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction2();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 2) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction4();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 3) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction5();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 4) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction6();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 5) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction7();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 6) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction7();
                }
            }, 3000);
        }
        if (handStrengthPreFlop == 7 | handStrengthPreFlop == 8) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerAction7();
                }
            }, 3000);
        }
        //startAction();
    }

    protected void ComputerFlop() {
        getHandStrengthComputerFlop();
        int bluff = 0;
        Random bluffRandom = new Random();

        bluff = bluffRandom.nextInt(3);
        if (callHolder >= computerMoneyInPot | playerStack == 0) {
            bluff = 1;
        }
        if (bluff == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionFlopBluff();
                }
            }, 3000);
        }
        if (computerHandStrength == 1 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionFlop1();
                }
            }, 3000);
        }
        if (computerHandStrength == 2 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionFlop2();
                }
            }, 3000);
        }
        if (computerHandStrength >= 3 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionFlop3();
                }
            }, 3000);
        }
    }

    protected void ComputerTurn() {
        getHandStrengthComputerTurn();
        int bluff = 0;
        Random bluffRandom = new Random();

        bluff = bluffRandom.nextInt(3);
        if (callHolder >= computerMoneyInPot | playerStack == 0) {
            bluff = 1;
        }
        if (bluff == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionTurnBluff();
                }
            }, 3000);
        }
        if (computerHandStrength == 1 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionTurn1();
                }
            }, 3000);
        }
        if (computerHandStrength == 2 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionTurn2();
                }
            }, 3000);
        }
        if (computerHandStrength >= 3 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionTurn3();
                }
            }, 3000);
        }
    }

    protected void ComputerRiver() {
        getHandStrengthComputer();
        int bluff = 0;
        Random bluffRandom = new Random();

        bluff = bluffRandom.nextInt(3);
        if (callHolder >= computerMoneyInPot | playerStack == 0) {
            bluff = 1;
        }
        if (bluff == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionRiverBluff();
                }
            }, 3000);
        }
        if (computerHandStrength == 1 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionRiver1();
                }
            }, 3000);
        }
        if (computerHandStrength == 2 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionRiver2();
                }
            }, 3000);
        }
        if (computerHandStrength >= 3 & bluff != 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerActionRiver3();
                }
            }, 3000);
        }
    }

    protected void computerActionRiverBluff() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);

        if (callHolder == 0) {
            callHolder = moneyInPot/2;
        }
        Random randomRaiseAmount1 = new Random();
        RaiseAmount = randomRaiseAmount1.nextInt(20);
        RaiseAmount = RaiseAmount + 1;
        if (callHolder < (moneyInPot/2)) {
            Random random = new Random();
            randomHolder = random.nextInt(2);

            if (randomHolder == 0) {
                callHolder = round(moneyInPot / 2);
            }
            if (randomHolder == 1) {
                callHolder = round((2 * moneyInPot) / 3);
            }
        }

        callHolder = callHolder * 2;
        if (RaiseAmount > 12) {
            callHolder = callHolder * (RaiseAmount / 12);
        }
        if (callHolder > computerStack) {
            callHolder = computerStack;
            allIn = "True";
        }
        moneyInPot = moneyInPot + callHolder;
        potSize.setText("Pot: $" + moneyInPot);
        computerStack = computerStack - callHolder;
        computerStackText.setText("$" + computerStack);
        computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
        if (computerStack == 0) {
            computerActionText.setText("All In $" + computerMoneyInPotAfter);
            allIn = "True";
            allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
            if (allInMoneyCount > 0) {
                checkBack = "False";
                turn = 0;
            }
            else {
                turn = 2;
            }
        } else {
            if (computerMoneyInPot - playerMoneyInPot == 0) {
                computerActionText.setText("Bet $" + computerMoneyInPotAfter);
                checkBack = "False";
                turn = 0;
            }
            else {
                computerActionText.setText("Raise to $" + computerMoneyInPotAfter);
                checkBack = "False";
                turn = 0;
            }
        }
        computerMoneyInPot = computerMoneyInPot + callHolder;
        startActionRiver();
    }

    protected void computerActionTurnBluff() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);

        if (callHolder == 0) {
            callHolder = moneyInPot/3;
        }
        Random randomRaiseAmount1 = new Random();
        RaiseAmount = randomRaiseAmount1.nextInt(20);
        RaiseAmount = RaiseAmount + 1;
        if (callHolder < (moneyInPot/2)) {
            Random random = new Random();
            randomHolder = random.nextInt(2);

            if (randomHolder == 0) {
                callHolder = round(moneyInPot / 2);
            }
            if (randomHolder == 1) {
                callHolder = round((2 * moneyInPot) / 3);
            }
        }

        callHolder = callHolder * 2;
        if (RaiseAmount > 12) {
            callHolder = callHolder * (RaiseAmount / 12);
        }
        if (callHolder > computerStack) {
            callHolder = computerStack;
            allIn = "True";
        }
        moneyInPot = moneyInPot + callHolder;
        potSize.setText("Pot: $" + moneyInPot);
        computerStack = computerStack - callHolder;
        computerStackText.setText("$" + computerStack);
        computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
        if (computerStack == 0) {
            computerActionText.setText("All In $" + computerMoneyInPotAfter);
            allIn = "True";
            allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
            if (allInMoneyCount > 0) {
                checkBack = "False";
                turn = 0;
            }
            else {
                turn = 2;
            }
        } else {
            if (computerMoneyInPot - playerMoneyInPot == 0) {
                computerActionText.setText("Bet $" + computerMoneyInPotAfter);
                checkBack = "False";
                turn = 0;
            }
            else {
                computerActionText.setText("Raise to $" + computerMoneyInPotAfter);
                checkBack = "False";
                turn = 0;
            }
        }
        computerMoneyInPot = computerMoneyInPot + callHolder;
        startActionTurn();
    }

    protected void computerActionFlopBluff() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);

        if (callHolder == 0) {
            callHolder = moneyInPot/3;
        }
        Random randomRaiseAmount1 = new Random();
        RaiseAmount = randomRaiseAmount1.nextInt(20);
        RaiseAmount = RaiseAmount + 1;
        if (callHolder < (moneyInPot/2)) {
            Random random = new Random();
            randomHolder = random.nextInt(2);

            if (randomHolder == 0) {
                callHolder = round(moneyInPot / 2);
            }
            if (randomHolder == 1) {
                callHolder = round((2 * moneyInPot) / 3);
            }
        }

        callHolder = callHolder * 2;
        if (RaiseAmount > 12) {
            callHolder = callHolder * (RaiseAmount / 12);
        }
        if (callHolder > computerStack) {
            callHolder = computerStack;
            allIn = "True";
        }
        moneyInPot = moneyInPot + callHolder;
        potSize.setText("Pot: $" + moneyInPot);
        computerStack = computerStack - callHolder;
        computerStackText.setText("$" + computerStack);
        computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
        if (computerStack == 0) {
            computerActionText.setText("All In $" + computerMoneyInPotAfter);
            allIn = "True";
            allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
            if (allInMoneyCount > 0) {
                checkBack = "False";
                turn = 0;
            }
            else {
                turn = 2;
            }
        } else {
            if (computerMoneyInPot - playerMoneyInPot == 0) {
                computerActionText.setText("Bet $" + computerMoneyInPotAfter);
                checkBack = "False";
                turn = 0;
            }
            else {
                computerActionText.setText("Raise to $" + computerMoneyInPotAfter);
                checkBack = "False";
                turn = 0;
            }
        }
        computerMoneyInPot = computerMoneyInPot + callHolder;
        startActionFlop();
    }

    protected void computerActionFlop1() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        int randomHolder = 0;

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 150 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotAfter);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 0;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startActionFlop();
    }

    protected void computerActionFlop2() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        int randomHolder = 0;

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 400 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotAfter);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 0;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startActionFlop();
    }

    protected void computerActionFlop3() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        Random randomRaise1 = new Random();
        randomHolder = randomRaise1.nextInt(4);

        if (callHolder >= computerStack | playerStack == 0) {
            randomHolder = 0;
        }
        if (randomHolder == 0) {
            if (callHolder >= computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                if (callHolder == 0) {
                    computerActionText.setText("Check");
                } else {
                    computerActionText.setText("Call $" + computerMoneyInPotAfter);
                }
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        }
        if (randomHolder != 0) {
            if (callHolder == 0) {
                callHolder = moneyInPot/4;
            }
            Random randomRaiseAmount1 = new Random();
            RaiseAmount = randomRaiseAmount1.nextInt(20);
            RaiseAmount = RaiseAmount + 1;
            if (callHolder < (moneyInPot/2)) {
                Random random = new Random();
                randomHolder = random.nextInt(2);

                if (randomHolder == 0) {
                    callHolder = round(moneyInPot / 2);
                }
                if (randomHolder == 1) {
                    callHolder = round((2 * moneyInPot) / 3);
                }
            }

            callHolder = callHolder * 2;
            if (RaiseAmount > 12) {
                callHolder = callHolder * (RaiseAmount / 12);
            }
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerStack = computerStack - callHolder;
            computerStackText.setText("$" + computerStack);
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
                allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    turn = 2;
                }
            } else {
                if (computerMoneyInPot - playerMoneyInPot == 0) {
                    computerActionText.setText("Bet $" + computerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    computerActionText.setText("Raise to $" + computerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 0;
                }
            }
            computerMoneyInPot = computerMoneyInPot + callHolder;
        }
        startActionFlop();
    }

    protected void computerActionTurn1() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        int randomHolder = 0;

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 100 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotAfter);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 0;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startActionTurn();
    }

    protected void computerActionTurn2() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        int randomHolder = 0;

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 500 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotAfter);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 0;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startActionTurn();
    }

    protected void computerActionTurn3() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        Random randomRaise1 = new Random();
        randomHolder = randomRaise1.nextInt(4);

        if (callHolder >= computerStack | playerStack == 0) {
            randomHolder = 0;
        }
        if (randomHolder == 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                if (callHolder == 0) {
                    computerActionText.setText("Check");
                } else {
                    computerActionText.setText("Call $" + computerMoneyInPotAfter);
                }
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        }
        if (randomHolder != 0) {
            if (callHolder == 0) {
                callHolder = moneyInPot/3;
            }
            Random randomRaiseAmount1 = new Random();
            RaiseAmount = randomRaiseAmount1.nextInt(20);
            RaiseAmount = RaiseAmount + 1;
            if (callHolder < (moneyInPot/2)) {
                Random random = new Random();
                randomHolder = random.nextInt(2);

                if (randomHolder == 0) {
                    callHolder = round(moneyInPot / 2);
                }
                if (randomHolder == 1) {
                    callHolder = round((2 * moneyInPot) / 3);
                }
            }

            callHolder = callHolder * 2;
            if (RaiseAmount > 12) {
                callHolder = callHolder * (RaiseAmount / 12);
            }
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerStack = computerStack - callHolder;
            computerStackText.setText("$" + computerStack);
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
                allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    turn = 2;
                }
            } else {
                if (computerMoneyInPot - playerMoneyInPot == 0) {
                    computerActionText.setText("Bet $" + computerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    computerActionText.setText("Raise to $" + computerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 0;
                }
            }
            computerMoneyInPot = computerMoneyInPot + callHolder;
            checkBack = "False";
            turn = 0;
        }
        startActionTurn();
    }

    protected void computerActionRiver1() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        int randomHolder = 0;

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 100 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotAfter);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 0;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startActionRiver();
    }

    protected void computerActionRiver2() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);
        int randomHolder = 0;

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 600 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotAfter);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 0;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startActionRiver();
    }

    protected void computerActionRiver3() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        Random randomRaise1 = new Random();
        randomHolder = randomRaise1.nextInt(4);

        if (callHolder >= computerStack | playerStack == 0) {
            randomHolder = 0;
        }
        if (randomHolder == 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
            } else {
                if (callHolder == 0) {
                    computerActionText.setText("Check");
                } else {
                    computerActionText.setText("Call $" + computerMoneyInPotAfter);
                }
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 0;
            } else {
                turn = 2;
            }
        }
        if (randomHolder != 0) {
            if (callHolder == 0) {
                callHolder = moneyInPot/3;
            }
            Random randomRaiseAmount1 = new Random();
            RaiseAmount = randomRaiseAmount1.nextInt(20);
            RaiseAmount = RaiseAmount + 1;
            if (callHolder < (moneyInPot/2)) {
                Random random = new Random();
                randomHolder = random.nextInt(2);

                if (randomHolder == 0) {
                    callHolder = round(moneyInPot / 2);
                }
                if (randomHolder == 1) {
                    callHolder = round((2 * moneyInPot) / 3);
                }
            }

            callHolder = callHolder * 2;
            if (RaiseAmount > 12) {
                callHolder = callHolder * (RaiseAmount / 12);
            }
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerStack = computerStack - callHolder;
            computerStackText.setText("$" + computerStack);
            computerMoneyInPotAfter = computerMoneyInPotAfter + callHolder;
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotAfter);
                allIn = "True";
                allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    turn = 2;
                }
            } else {
                if (computerMoneyInPot - playerMoneyInPot == 0) {
                    computerActionText.setText("Bet $" + computerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 0;
                }
                else {
                    computerActionText.setText("Raise to $" + computerMoneyInPotAfter);
                    checkBack = "False";
                    turn = 0;
                }
            }
            computerMoneyInPot = computerMoneyInPot + callHolder;
        }
        startActionRiver();
    }

    //If hand strength is 0, do this
    protected void computerAction0() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 100 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startAction();
    }

    protected void computerAction1() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 150 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startAction();
    }

    protected void computerAction2() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 250 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startAction();
    }

    protected void computerAction3() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 400 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startAction();
    }

    protected void computerAction4() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        if (callHolder < 500 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        } else {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }
        startAction();
    }

    protected void computerAction5() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        Random randomRaise1 = new Random();
        randomHolder = randomRaise1.nextInt(3);

        if (callHolder >= computerStack | playerStack == 0) {
            randomHolder = 1;
        }
        if (callHolder < 650 & randomHolder < 2 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        }

        if (callHolder >= 650 | callHolder == 0) {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }

        if (callHolder < 650 & randomHolder == 2 & callHolder != 0) {
            Random randomRaiseAmount1 = new Random();
            RaiseAmount = randomRaiseAmount1.nextInt(16);
            RaiseAmount = RaiseAmount + 1;
            if (callHolder < (moneyInPot/2)) {
                callHolder = moneyInPot/2;
            }
            callHolder = callHolder * 2;
            if (RaiseAmount > 12) {
                callHolder = callHolder * (RaiseAmount / 12);
            }
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerStackText.setText("$" + computerStack);
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
                allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 1;
                }
                else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Raise to $" + computerMoneyInPotPreFlop);
                checkBack = "False";
                turn = 1;
            }
        }
        startAction();
    }

    protected void computerAction6() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        Random randomRaise1 = new Random();
        randomHolder = randomRaise1.nextInt(3);

        if (callHolder >= computerStack | playerStack == 0) {
            randomHolder = 0;
        }
        if (callHolder < 1000 & randomHolder == 0 & callHolder != 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        }

        if (callHolder >= 1000 | callHolder == 0) {
            if (callHolder == 0) {
                computerActionText.setText("Check");
                if (checkBack.equals("True")) {
                    checkBack = "False";
                    turn = 1;
                } else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Fold");
                turn = 3;
                whoFolded = "Computer";
            }
        }

        if (callHolder < 1000 & randomHolder > 0 & callHolder != 0) {
            Random randomRaiseAmount1 = new Random();
            RaiseAmount = randomRaiseAmount1.nextInt(20);
            RaiseAmount = RaiseAmount + 1;
            if (callHolder < (moneyInPot/2)) {
                callHolder = moneyInPot/2;
            }
            callHolder = callHolder * 2;
            if (RaiseAmount > 12) {
                callHolder = callHolder * (RaiseAmount / 12);
            }
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerStackText.setText("$" + computerStack);
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
                allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 1;
                }
                else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Raise to $" + computerMoneyInPotPreFlop);
                checkBack = "False";
                turn = 1;
            }
        }
        startAction();
    }

    protected void computerAction7() {
        TextView potSize = (TextView) findViewById(R.id.potSize);
        TextView computerActionText = (TextView) findViewById(R.id.computerActionText);
        TextView computerStackText = (TextView) findViewById(R.id.computerStack);

        callHolder = playerMoneyInPot - computerMoneyInPot;
        computerActionText.setText("To call $" + callHolder);
        Random randomRaise1 = new Random();
        randomHolder = randomRaise1.nextInt(4);

        if (callHolder >= computerStack | playerStack == 0) {
            randomHolder = 0;
        }
        if (randomHolder == 0) {
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            computerStackText.setText("$" + computerStack);
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
            } else {
                if (callHolder == 0) {
                    computerActionText.setText("Check");
                    if (checkBack.equals("True")) {
                        checkBack = "False";
                        turn = 1;
                    } else {
                        turn = 2;
                    }
                } else {
                    computerActionText.setText("Call $" + computerMoneyInPotPreFlop);
                }
            }
            if (checkBack.equals("True")) {
                checkBack = "False";
                turn = 1;
            } else {
                turn = 2;
            }
        }
        if (randomHolder != 0) {
            if (callHolder == 0) {
                callHolder = 25;
            }
            Random randomRaiseAmount1 = new Random();
            RaiseAmount = randomRaiseAmount1.nextInt(20);
            RaiseAmount = RaiseAmount + 1;
            if (callHolder < (moneyInPot / 2)) {
                callHolder = moneyInPot / 2;
            }
            callHolder = callHolder * 2;
            if (RaiseAmount > 12) {
                callHolder = callHolder * (RaiseAmount / 12);
            }
            if (callHolder > computerStack) {
                callHolder = computerStack;
                allIn = "True";
            }
            moneyInPot = moneyInPot + callHolder;
            potSize.setText("Pot: $" + moneyInPot);
            computerMoneyInPot = computerMoneyInPot + callHolder;
            computerStack = computerStack - callHolder;
            computerStackText.setText("$" + computerStack);
            computerMoneyInPotPreFlop = computerMoneyInPotPreFlop + callHolder;
            if (computerStack == 0) {
                computerActionText.setText("All In $" + computerMoneyInPotPreFlop);
                allIn = "True";
                allInMoneyCount = computerMoneyInPotAfter - playerMoneyInPotAfter;
                if (allInMoneyCount > 0) {
                    checkBack = "False";
                    turn = 1;
                }
                else {
                    turn = 2;
                }
            } else {
                computerActionText.setText("Raise to $" + computerMoneyInPotPreFlop);
                checkBack = "False";
                turn = 1;
            }
        }
        startAction();
    }

    protected void resultsHighCard() {
        if (cardsInPlayerHand[0] > cardsInComputerHand[0] | cardsInPlayerHand[0] < cardsInComputerHand[0]) {
            if (cardsInPlayerHand[0] > cardsInComputerHand[0]) {
                whoWon = "Player";
            }
            if (cardsInPlayerHand[0] < cardsInComputerHand[0]) {
                whoWon = "Computer";
            }
        }
        else {
            if (cardsInPlayerHand[1] > cardsInComputerHand[1] | cardsInPlayerHand[1] < cardsInComputerHand[1]) {
                if (cardsInPlayerHand[1] > cardsInComputerHand[1]) {
                    whoWon = "Player";
                }
                if (cardsInPlayerHand[1] < cardsInComputerHand[1]) {
                    whoWon = "Computer";
                }
            }
            else {
                if (cardsInPlayerHand[2] > cardsInComputerHand[2] | cardsInPlayerHand[2] < cardsInComputerHand[2]) {
                    if (cardsInPlayerHand[2] > cardsInComputerHand[2]) {
                        whoWon = "Player";
                    }
                    if (cardsInPlayerHand[2] < cardsInComputerHand[2]) {
                        whoWon = "Computer";
                    }
                }
                else {
                    if (cardsInPlayerHand[3] > cardsInComputerHand[3] | cardsInPlayerHand[3] < cardsInComputerHand[3]) {
                        if (cardsInPlayerHand[3] > cardsInComputerHand[3]) {
                            whoWon = "Player";
                        }
                        if (cardsInPlayerHand[3] < cardsInComputerHand[3]) {
                            whoWon = "Computer";
                        }
                    }
                    else {
                        if (cardsInPlayerHand[4] > cardsInComputerHand[4] | cardsInPlayerHand[4] < cardsInComputerHand[4]) {
                            if (cardsInPlayerHand[4] > cardsInComputerHand[4]) {
                                whoWon = "Player";
                            }
                            if (cardsInPlayerHand[4] < cardsInComputerHand[4]) {
                                whoWon = "Computer";
                            }
                        }
                        else {
                            whoWon = "Tie";
                        }
                    }
                }
            }
        }
    }

    protected void resultsPair() {
        if (playerPairCard > computerPairCard | playerPairCard < computerPairCard) {
            if (playerPairCard > computerPairCard) {
                whoWon = "Player";
            }
            if (playerPairCard < computerPairCard) {
                whoWon = "Computer";
            }
        }
        else {
            justHolder = 0;
            for (int i = 0; i < 5; i++) {
                if (cardsInPlayerHand[i] == playerPairCard) {
                    justHolder = justHolder + 1;
                }
                comparisonHolderPlayer[i] = cardsInPlayerHand[i];
            }
            if (justHolder == 0) {
                comparisonHolderPlayer[3] = playerPairCard;
                comparisonHolderPlayer[4] = playerPairCard;
            }
            if (justHolder == 1) {
                comparisonHolderPlayer[4] = playerPairCard;
            }
            // -------------------------------
            justHolder = 0;
            for (int i = 0; i < 5; i++) {
                if (cardsInComputerHand[i] == computerPairCard) {
                    justHolder = justHolder + 1;
                }
                comparisonHolderComputer[i] = cardsInComputerHand[i];
            }
            if (justHolder == 0) {
                comparisonHolderComputer[3] = computerPairCard;
                comparisonHolderComputer[4] = computerPairCard;
            }
            if (justHolder == 1) {
                comparisonHolderComputer[4] = computerPairCard;
            }
            // -------------------------------
            if (comparisonHolderPlayer[0] > comparisonHolderComputer[0] | comparisonHolderPlayer[0] < comparisonHolderComputer[0]) {
                if (comparisonHolderPlayer[0] > comparisonHolderComputer[0]) {
                    whoWon = "Player";
                }
                if (comparisonHolderPlayer[0] < comparisonHolderComputer[0]) {
                    whoWon = "Computer";
                }
            }
            else {
                if (comparisonHolderPlayer[1] > comparisonHolderComputer[1] | comparisonHolderPlayer[1] < comparisonHolderComputer[1]) {
                    if (comparisonHolderPlayer[1] > comparisonHolderComputer[1]) {
                        whoWon = "Player";
                    }
                    if (comparisonHolderPlayer[1] < comparisonHolderComputer[1]) {
                        whoWon = "Computer";
                    }
                }
                else {
                    if (comparisonHolderPlayer[2] > comparisonHolderComputer[2] | comparisonHolderPlayer[2] < comparisonHolderComputer[2]) {
                        if (comparisonHolderPlayer[2] > comparisonHolderComputer[2]) {
                            whoWon = "Player";
                        }
                        if (comparisonHolderPlayer[2] < comparisonHolderComputer[2]) {
                            whoWon = "Computer";
                        }
                    }
                    else {
                        if (comparisonHolderPlayer[3] > comparisonHolderComputer[3] | comparisonHolderPlayer[3] < comparisonHolderComputer[3]) {
                            if (comparisonHolderPlayer[3] > comparisonHolderComputer[3]) {
                                whoWon = "Player";
                            }
                            if (comparisonHolderPlayer[3] < comparisonHolderComputer[3]) {
                                whoWon = "Computer";
                            }
                        }
                        else {
                            if (comparisonHolderPlayer[4] > comparisonHolderComputer[4] | comparisonHolderPlayer[4] < comparisonHolderComputer[4]) {
                                if (comparisonHolderPlayer[4] > comparisonHolderComputer[4]) {
                                    whoWon = "Player";
                                }
                                if (comparisonHolderPlayer[4] < comparisonHolderComputer[4]) {
                                    whoWon = "Computer";
                                }
                            }
                            else {
                                whoWon = "Tie";
                            }
                        }
                    }
                }
            }
        }
    }

    protected void resultsTwoPair() {
        if (playerTwoPairCard1 > computerTwoPairCard1 | playerTwoPairCard1 < computerTwoPairCard1) {
            if (playerTwoPairCard1 > computerTwoPairCard1) {
                whoWon = "Player";
            }
            if (playerTwoPairCard1 < computerTwoPairCard1) {
                whoWon = "Computer";
            }
        }
        else {
            if (playerTwoPairCard2 > computerTwoPairCard2 | playerTwoPairCard2 < computerTwoPairCard2) {
                if (playerTwoPairCard2 > computerTwoPairCard2) {
                    whoWon = "Player";
                }
                if (playerTwoPairCard2 < computerTwoPairCard2) {
                    whoWon = "Computer";
                }
            }
            else {
                for (int i = 0; i < 7; i++) {
                    if (cardsInPlayerHand[i] == playerTwoPairCard1 | cardsInPlayerHand[i] == playerTwoPairCard2) {
                        cardsInPlayerHand[i] = -10;
                    }
                }
                Arrays.sort(cardsInPlayerHand, Collections.reverseOrder());

                for (int i = 0; i < 7; i++) {
                    if (cardsInComputerHand[i] == computerTwoPairCard1 | cardsInComputerHand[i] == computerTwoPairCard2) {
                        cardsInComputerHand[i] = -10;
                    }
                }
                Arrays.sort(cardsInComputerHand, Collections.reverseOrder());

                if (cardsInPlayerHand[0] > cardsInComputerHand[0] | cardsInPlayerHand[0] < cardsInComputerHand[0]) {
                    if (cardsInPlayerHand[0] > cardsInComputerHand[0]) {
                        whoWon = "Player";
                    }
                    if (cardsInPlayerHand[0] < cardsInComputerHand[0]) {
                        whoWon = "Computer";
                    }
                }
                else {
                    whoWon = "Tie";
                }
            }
        }
    }

    protected void resultsThreeOfAKind() {
        if (playerThreeOfAKindCard > computerThreeOfAKindCard | playerThreeOfAKindCard < computerThreeOfAKindCard) {
            if (playerThreeOfAKindCard > computerThreeOfAKindCard) {
                whoWon = "Player";
            }
            if (playerThreeOfAKindCard < computerThreeOfAKindCard) {
                whoWon = "Computer";
            }
        }
        else {
            for (int i = 0; i < 7; i++) {
                if (cardsInPlayerHand[i] == playerThreeOfAKindCard) {
                    cardsInPlayerHand[i] = -10;
                }
            }
            Arrays.sort(cardsInPlayerHand, Collections.reverseOrder());

            for (int i = 0; i < 7; i++) {
                if (cardsInComputerHand[i] == computerThreeOfAKindCard) {
                    cardsInComputerHand[i] = -10;
                }
            }
            Arrays.sort(cardsInComputerHand, Collections.reverseOrder());

            if (cardsInPlayerHand[0] > cardsInComputerHand[0] | cardsInPlayerHand[0] < cardsInComputerHand[0]) {
                if (cardsInPlayerHand[0] > cardsInComputerHand[0]) {
                    whoWon = "Player";
                }
                if (cardsInPlayerHand[0] < cardsInComputerHand[0]) {
                    whoWon = "Computer";
                }
            }
            else {
                if (cardsInPlayerHand[1] > cardsInComputerHand[1] | cardsInPlayerHand[1] < cardsInComputerHand[1]) {
                    if (cardsInPlayerHand[1] > cardsInComputerHand[1]) {
                        whoWon = "Player";
                    }
                    if (cardsInPlayerHand[1] < cardsInComputerHand[1]) {
                        whoWon = "Computer";
                    }
                }
                else {
                    whoWon = "Tie";
                }
            }
        }
    }

    protected void resultsStraight() {
        if (playerStraightCardHigh > computerStraightCardHigh & playerStraightCardLow > computerStraightCardLow) {
            whoWon = "Player";
        }
        if (playerStraightCardHigh < computerStraightCardHigh & playerStraightCardLow < computerStraightCardLow) {
            whoWon = "Computer";
        }

        if (playerStraightCardHigh == computerStraightCardHigh & playerStraightCardLow == computerStraightCardLow) {
            whoWon = "Tie";
        }
    }

    protected void resultsFlush() {
        if (playerFlushCard1 > computerFlushCard1 | playerFlushCard1 < computerFlushCard1) {
            if (playerFlushCard1 > computerFlushCard1) {
                whoWon = "Player";
            }
            if (playerFlushCard1 < computerFlushCard1) {
                whoWon = "Computer";
            }
        }
        else {
            if (playerFlushCard2 > computerFlushCard2 | playerFlushCard2 < computerFlushCard2) {
                if (playerFlushCard2 > computerFlushCard2) {
                    whoWon = "Player";
                }
                if (playerFlushCard2 < computerFlushCard2) {
                    whoWon = "Computer";
                }
            }
            else {
                if (playerFlushCard3 > computerFlushCard3 | playerFlushCard3 < computerFlushCard3) {
                    if (playerFlushCard3 > computerFlushCard3) {
                        whoWon = "Player";
                    }
                    if (playerFlushCard3 < computerFlushCard3) {
                        whoWon = "Computer";
                    }
                }
                else {
                    if (playerFlushCard4 > computerFlushCard4 | playerFlushCard4 < computerFlushCard4) {
                        if (playerFlushCard4 > computerFlushCard4) {
                            whoWon = "Player";
                        }
                        if (playerFlushCard4 < computerFlushCard4) {
                            whoWon = "Computer";
                        }
                    }
                    else {
                        if (playerFlushCard5 > computerFlushCard5 | playerFlushCard5 < computerFlushCard5) {
                            if (playerFlushCard5 > computerFlushCard5) {
                                whoWon = "Player";
                            }
                            if (playerFlushCard5 < computerFlushCard5) {
                                whoWon = "Computer";
                            }
                        }
                        else {
                            whoWon = "Tie";
                        }
                    }
                }
            }
        }
    }

    protected void resultsFullHouse() {
        if (playerFullHouseThreeOfAKind > computerFullHouseThreeOfAKind) {
            whoWon = "Player";
        }
        else {
            if (playerFullHouseThreeOfAKind < computerFullHouseThreeOfAKind) {
                whoWon = "Computer";
            }
            else {
                if (playerFullHousePair > computerFullHousePair) {
                    whoWon = "Player";
                }
                else {
                    if (playerFullHousePair > computerFullHousePair) {
                        whoWon = "Computer";
                    }
                    else {
                        whoWon = "Tie";
                    }
                }
            }
        }
    }

    protected void resultsFourOfAKind() {
        if (playerFourOfAKindCard > computerFourOfAKindCard) {
            whoWon = "Player";
        }
        else {
            if (playerFourOfAKindCard < computerFourOfAKindCard) {
                whoWon = "Computer";
            }
            else {
                whoWon = "Tie";
            }
        }
    }

    protected void resultsStraightFlush() {
        if (playerStraightFlushCardHigh > computerStraightFlushCardHigh & playerStraightFlushCardLow > computerStraightFlushCardLow) {
            whoWon = "Player";
        }
        else {
            if (playerStraightFlushCardHigh < computerStraightFlushCardHigh & playerStraightFlushCardLow < computerStraightFlushCardLow) {
                whoWon = "Computer";
            }
            else {
                if (playerStraightFlushCardHigh == computerStraightFlushCardHigh & playerStraightFlushCardLow == computerStraightFlushCardLow) {
                    whoWon = "Tie";
                }
            }
        }
    }

    protected void resultsRoyalFlush() {
        whoWon = "Tie";
    }

    protected void getResults() {
        getHandStrengthComputer();
        getHandStrengthPlayer();

        if (computerHandStrength > playerHandStrength) {
            whoWon = "Computer";
        }

        if (playerHandStrength > computerHandStrength) {
            whoWon = "Player";
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 1) {
            resultsHighCard();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 2) {
            resultsPair();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 3) {
            resultsTwoPair();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 4) {
            resultsThreeOfAKind();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 5) {
            resultsStraight();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 6) {
            resultsFlush();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 7) {
            resultsFullHouse();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 8) {
            resultsFourOfAKind();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 9) {
            resultsStraightFlush();
        }

        if (playerHandStrength == computerHandStrength & playerHandStrength == 10) {
            resultsRoyalFlush();
        }

        if (whoWon == "Player") {
            TextView potSize1 = (TextView) findViewById(R.id.potSize);
            TextView playerActionText1 = (TextView) findViewById(R.id.playerActionText);
            TextView computerActionText1 = (TextView) findViewById(R.id.computerActionText);
            TextView computerStackText1 = (TextView) findViewById(R.id.computerStack);
            TextView playerStackText1 = (TextView) findViewById(R.id.playerStack);

            if (computerMoneyInPot > playerMoneyInPot) {
                tooMuch = computerMoneyInPot - playerMoneyInPot;
                computerStack = computerStack + tooMuch;
                moneyInPot = moneyInPot - tooMuch;
            }

            playerActionText1.setText("You Won");
            computerActionText1.setText("Computer Lost");

            playerStack = playerStack + moneyInPot;
            totalMoney = totalMoney + moneyInPot;
            saveTotalMoney();

            computerStackText1.setText("$" + computerStack);
            playerStackText1.setText("$" + playerStack);

            int x = playerHandStrength;
            switch(x) {
                case 1:
                    potSize1.setText("Player won $" + moneyInPot + " with high card");
                    break;
                case 2:
                    potSize1.setText("Player won $" + moneyInPot + " with pair");
                    break;
                case 3:
                    potSize1.setText("Player won $" + moneyInPot + " with two pair");
                    break;
                case 4:
                    potSize1.setText("Player won $" + moneyInPot + " with three of a kind");
                    break;
                case 5:
                    potSize1.setText("Player won $" + moneyInPot + " with a straight");
                    break;
                case 6:
                    potSize1.setText("Player won $" + moneyInPot + " with a flush");
                    break;
                case 7:
                    potSize1.setText("Player won $" + moneyInPot + " with a full house");
                    break;
                case 8:
                    potSize1.setText("Player won $" + moneyInPot + " with a four of a kind");
                    break;
                case 9:
                    potSize1.setText("Player won $" + moneyInPot + " with a straight flush");
                    break;
                case 10:
                    potSize1.setText("Player won $" + moneyInPot + " with a royal flush");
                    break;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    endGame();
                }
            }, 5000);
        }

        if (whoWon == "Computer") {
            TextView potSize1 = (TextView) findViewById(R.id.potSize);
            TextView playerActionText1 = (TextView) findViewById(R.id.playerActionText);
            TextView computerActionText1 = (TextView) findViewById(R.id.computerActionText);
            TextView computerStackText1 = (TextView) findViewById(R.id.computerStack);
            TextView playerStackText1 = (TextView) findViewById(R.id.playerStack);

            if (playerMoneyInPot > computerMoneyInPot) {
                tooMuch = playerMoneyInPot - computerMoneyInPot;
                playerStack = playerStack + tooMuch;
                totalMoney = totalMoney + tooMuch;
                moneyInPot = moneyInPot - tooMuch;
                saveTotalMoney();
            }

            playerActionText1.setText("You Lost");
            computerActionText1.setText("Computer Won");

            computerStack = computerStack + moneyInPot;

            computerStackText1.setText("$" + computerStack);
            playerStackText1.setText("$" + playerStack);

            int x = computerHandStrength;
            switch(x) {
                case 1:
                    potSize1.setText("Computer won $" + moneyInPot + " with high card");
                    break;
                case 2:
                    potSize1.setText("Computer won $" + moneyInPot + " with pair");
                    break;
                case 3:
                    potSize1.setText("Computer won $" + moneyInPot + " with two pair");
                    break;
                case 4:
                    potSize1.setText("Computer won $" + moneyInPot + " with three of a kind");
                    break;
                case 5:
                    potSize1.setText("Computer won $" + moneyInPot + " with a straight");
                    break;
                case 6:
                    potSize1.setText("Computer won $" + moneyInPot + " with a flush");
                    break;
                case 7:
                    potSize1.setText("Computer won $" + moneyInPot + " with a full house");
                    break;
                case 8:
                    potSize1.setText("Computer won $" + moneyInPot + " with a four of a kind");
                    break;
                case 9:
                    potSize1.setText("Computer won $" + moneyInPot + " with a straight flush");
                    break;
                case 10:
                    potSize1.setText("Computer won $" + moneyInPot + " with a royal flush");
                    break;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    endGame();
                }
            }, 5000);
        }

        if (whoWon == "Tie") {
            TextView potSize1 = (TextView) findViewById(R.id.potSize);
            TextView playerActionText1 = (TextView) findViewById(R.id.playerActionText);
            TextView computerActionText1 = (TextView) findViewById(R.id.computerActionText);
            TextView computerStackText1 = (TextView) findViewById(R.id.computerStack);
            TextView playerStackText1 = (TextView) findViewById(R.id.playerStack);

            if (playerMoneyInPot > computerMoneyInPot) {
                tooMuch = playerMoneyInPot - computerMoneyInPot;
                playerStack = playerStack + tooMuch;
                totalMoney = totalMoney + tooMuch;
                moneyInPot = moneyInPot - tooMuch;
                saveTotalMoney();
            }

            if (computerMoneyInPot > playerMoneyInPot) {
                tooMuch = computerMoneyInPot - playerMoneyInPot;
                computerStack = computerStack + tooMuch;
                moneyInPot = moneyInPot - tooMuch;
            }

            playerActionText1.setText("Tie");
            computerActionText1.setText("Tie");

            computerStack = computerStack + moneyInPot/2;
            playerStack = playerStack + moneyInPot/2;
            totalMoney = totalMoney + moneyInPot/2;
            saveTotalMoney();

            computerStackText1.setText("$" + computerStack);
            playerStackText1.setText("$" + playerStack);

            int x = playerHandStrength;
            switch(x) {
                case 1:
                    potSize1.setText("Tied with high card");
                    break;
                case 2:
                    potSize1.setText("Tied with pair");
                    break;
                case 3:
                    potSize1.setText("Tied with two pair");
                    break;
                case 4:
                    potSize1.setText("Tied with three of a kind");
                    break;
                case 5:
                    potSize1.setText("Tied with a straight");
                    break;
                case 6:
                    potSize1.setText("Tied with a flush");
                    break;
                case 7:
                    potSize1.setText("Tied with a full house");
                    break;
                case 8:
                    potSize1.setText("Tied with a four of a kind");
                    break;
                case 9:
                    potSize1.setText("Tied with a straight flush");
                    break;
                case 10:
                    potSize1.setText("Tied with a royal flush");
                    break;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    endGame();
                }
            }, 5000);
        }
    }

    protected void endGame() {
        String emptyCard = "gray_back";
        if (playerStack != 0 & computerStack != 0) {
            checkBack = "True";
            handStrengthPreFlop = 0;
            callHolder = 0;
            moneyInPot = 0;
            computerMoneyInPot = 0;
            computerMoneyInPotAfter = 0;
            computerMoneyInPotPreFlop = 0;
            playerMoneyInPotAfter = 0;
            playerMoneyInPot = 0;
            playerMoneyInPotPreFlop = 0;
            allIn = "False";
            RaiseAmount = 0;
            raiseAmount = 0;
            tooMuch = 0;
            minRaiseNumber = 0;
            whoWon = "";
            raiseButtonColor = "True";
            onPreFlop = "True";
            smallBlind = 25;
            bigBlind = 50;
            ((ImageView) findViewById(R.id.playerCard1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.playerCard2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop3)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.turn)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.river)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.dealerChipComputer)).setImageResource(0);
            ((ImageView) findViewById(R.id.dealerChipPlayer)).setImageResource(0);
            final TextView playerActionText = findViewById(R.id.playerActionText);
            final TextView computerActionText = findViewById(R.id.computerActionText);
            playerActionText.setText("");
            computerActionText.setText("");
            playPoker();
        }
        if (playerStack == 0) {
            //End Game and save score
            checkBack = "True";
            handStrengthPreFlop = 0;
            callHolder = 0;
            moneyInPot = 0;
            computerMoneyInPot = 0;
            computerMoneyInPotAfter = 0;
            computerMoneyInPotPreFlop = 0;
            playerMoneyInPotAfter = 0;
            playerMoneyInPot = 0;
            playerMoneyInPotPreFlop = 0;
            allIn = "False";
            RaiseAmount = 0;
            raiseAmount = 0;
            tooMuch = 0;
            minRaiseNumber = 0;
            whoWon = "";
            raiseButtonColor = "True";
            onPreFlop = "True";
            smallBlind = 25;
            bigBlind = 50;
            seekBarCounter = 0;
            ((ImageView) findViewById(R.id.playerCard1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.playerCard2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop3)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.turn)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.river)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.dealerChipComputer)).setImageResource(0);
            ((ImageView) findViewById(R.id.dealerChipPlayer)).setImageResource(0);
            final TextView playerActionText = findViewById(R.id.playerActionText);
            final TextView computerActionText = findViewById(R.id.computerActionText);
            playerActionText.setText("");
            computerActionText.setText("");
            final Button quitPoker = findViewById(R.id.quitPoker);
            quitPoker.setTextColor(getResources().getColor(R.color.green));

            final Button rebuy = findViewById(R.id.raise);
            final SeekBar rebuyBar = findViewById(R.id.seekBar);

            rebuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (progressValue <= totalMoney) {
                        playerStack = progressValue;
                        quitPoker.setTextColor(getResources().getColor(R.color.black));
                        rebuy.setClickable(false);
                        playPoker();
                    }
                    else {
                        displayToastRebuy();
                    }
                }
            });

            rebuyBar.setMax(4);
            quitPoker.setTextColor(getResources().getColor(R.color.green));
            if (totalMoney < 1000) {
                rebuy.setTextColor(getResources().getColor(R.color.red));
                rebuy.setClickable(false);
            }
            else {
                rebuy.setTextColor(getResources().getColor(R.color.green));
                rebuy.setClickable(true);
            }
            rebuyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                     progressValue = ((progress + 1)* 1000);
                     progressHolder = progress;
                    if (totalMoney < progressValue) {
                        rebuy.setTextColor(getResources().getColor(R.color.red));
                        rebuy.setClickable(false);
                    }
                    else {
                        rebuy.setTextColor(getResources().getColor(R.color.green));
                        rebuy.setClickable(true);
                    }
                    rebuy.setText("Rebuy $" + progressValue);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    progressValue = ((progressHolder + 1)* 1000);
                    if (totalMoney < progressValue) {
                        rebuy.setTextColor(getResources().getColor(R.color.red));
                        rebuy.setClickable(false);
                    }
                    else {
                        rebuy.setTextColor(getResources().getColor(R.color.green));
                        rebuy.setClickable(true);
                    }
                    rebuy.setText("Rebuy $" + progressValue);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    progressValue = ((progressHolder + 1)* 1000);
                    if (totalMoney < progressValue) {
                        rebuy.setTextColor(getResources().getColor(R.color.red));
                        rebuy.setClickable(false);
                    }
                    else {
                        rebuy.setTextColor(getResources().getColor(R.color.green));
                        rebuy.setClickable(true);
                    }
                    rebuy.setText("Rebuy $" + progressValue);
                }
            });
            rebuyBar.setProgress(0);
        }

        if (computerStack == 0) {
            //End Game and save score
            checkBack = "True";
            handStrengthPreFlop = 0;
            callHolder = 0;
            moneyInPot = 0;
            computerMoneyInPot = 0;
            computerMoneyInPotAfter = 0;
            computerMoneyInPotPreFlop = 0;
            playerMoneyInPotAfter = 0;
            playerMoneyInPot = 0;
            playerMoneyInPotPreFlop = 0;
            allIn = "False";
            RaiseAmount = 0;
            raiseAmount = 0;
            tooMuch = 0;
            minRaiseNumber = 0;
            whoWon = "";
            raiseButtonColor = "True";
            onPreFlop = "True";
            smallBlind = 25;
            bigBlind = 50;
            seekBarCounter = 0;
            ((ImageView) findViewById(R.id.playerCard1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.playerCard2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.computerCard1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.computerCard2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop1)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop2)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.flop3)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.turn)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.river)).setImageResource(R.drawable.gray_back);
            ((ImageView) findViewById(R.id.dealerChipComputer)).setImageResource(0);
            ((ImageView) findViewById(R.id.dealerChipPlayer)).setImageResource(0);
            final TextView playerActionText = findViewById(R.id.playerActionText);
            final TextView computerActionText = findViewById(R.id.computerActionText);
            playerActionText.setText("");
            computerActionText.setText("");
            final Button quitPoker = findViewById(R.id.quitPoker);
            quitPoker.setTextColor(getResources().getColor(R.color.green));
        }
    }

    protected void checkMoney() {
        TextView playerStackText = findViewById(R.id.playerStack);

        if (totalMoney < 5000) {
            playerStack = totalMoney;
            playerStackText.setText("$" + playerStack);
        }
        playPoker();
    }

    protected void playPoker() {
        draw_first_card();
        draw_second_card();
        draw_third_card();
        draw_fourth_card();
        draw_fifth_card();
        draw_sixth_card();
        draw_seventh_card();
        draw_eighth_card();
        draw_ninth_card();
        collectBlinds();
        ((ImageView) findViewById(R.id.flop1)).setImageResource(0);
        ((ImageView) findViewById(R.id.flop2)).setImageResource(0);
        ((ImageView) findViewById(R.id.flop3)).setImageResource(0);
        ((ImageView) findViewById(R.id.turn)).setImageResource(0);
        ((ImageView) findViewById(R.id.river)).setImageResource(0);
        turn = whoseBlind;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAction();
            }
        }, 250);
    }

    protected void displayToastRebuy() {
        Toast.makeText(this, "Not enough money to rebuy", Toast.LENGTH_LONG).show();
    }

    public void saveTotalMoney() {
        SharedPreferences sharedPreferences = getSharedPreferences("TotalMoney", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(INT, totalMoney);
        editor.apply();
    }
}
