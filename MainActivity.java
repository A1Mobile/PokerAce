package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends DrawCards implements RewardedVideoAdListener {
    private ImageButton pokerButton;
    private ImageButton blackjackButton;
    private ImageButton threeCardPokerButton;
    private static int newPlayer;
    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialAd1;
    private InterstitialAd interstitialAd2;
    private RewardedVideoAd rewardedVideoAdMain;
    protected int entered;
    protected String INT = "int";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readTotalMoney();

        final Button freeMoneyMain = findViewById(R.id.freeMoneyMain);

        rewardedVideoAdMain = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAdMain.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //Changed
        MobileAds.initialize(this, "ca-app-pub-6280210391658207~7088294285");
        interstitialAd = new InterstitialAd(this);
        //Changed
        interstitialAd.setAdUnitId("ca-app-pub-6280210391658207/5533437363");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(new Intent(MainActivity.this, Poker.class));
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        //Changed
        interstitialAd1 = new InterstitialAd(this);
        interstitialAd1.setAdUnitId("ca-app-pub-6280210391658207/5533437363");
        interstitialAd1.loadAd(new AdRequest.Builder().build());
        interstitialAd1.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(new Intent(MainActivity.this, Blackjack.class));
                interstitialAd1.loadAd(new AdRequest.Builder().build());
            }
        });
        //Changed
        interstitialAd2 = new InterstitialAd(this);
        interstitialAd2.setAdUnitId("ca-app-pub-6280210391658207/5533437363");
        interstitialAd2.loadAd(new AdRequest.Builder().build());
        interstitialAd2.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(new Intent(MainActivity.this, ThreeCardPoker.class));
                interstitialAd2.loadAd(new AdRequest.Builder().build());
            }
        });

        ImageButton pokerButton = (ImageButton) findViewById(R.id.pokerButton);
        pokerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                else {
                    openPoker();
                }
            }
        });

        ImageButton blackjackButton = (ImageButton) findViewById(R.id.blackjackButton);
        blackjackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd1.isLoaded()) {
                    interstitialAd1.show();
                }
                else {
                    openBlackjack();
                }
            }
        });

        ImageButton threeCardPokerButton = (ImageButton) findViewById(R.id.slotsButton);
        threeCardPokerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd2.isLoaded()) {
                    interstitialAd2.show();
                }
                else {
                    openThreeCardPoker();
                }
            }
        });

        /* Button reportProblemButton = findViewById(R.id.reportProblem);
        reportProblemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportProblem();
            }
        }); */

        Button storeButton = findViewById(R.id.store);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportProblem();
            }
        });

        freeMoneyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedVideoAdMain.isLoaded()) {
                    rewardedVideoAdMain.show();
                }
                else {
                    noFreeMoney();
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        readTotalMoney();
        TextView totalMoneyText = (TextView) findViewById(R.id.totalMoneyTextMain);
        totalMoneyText.setText("$" + totalMoney);
    }

    protected void onPause() {
        super.onPause();
        saveTotalMoney();
    }

    protected void onStop() {
        super.onStop();
        saveTotalMoney();
    }

    protected void onDestroy() {
        super.onDestroy();
        saveTotalMoney();
    }

    public void saveTotalMoney() {
        SharedPreferences sharedPreferences = getSharedPreferences("TotalMoney", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(INT, totalMoney);
        editor.apply();
    }

    public void readTotalMoney() {
        SharedPreferences sharedPreferences = getSharedPreferences("TotalMoney", MODE_PRIVATE);
        totalMoney = sharedPreferences.getInt(INT, 15000);

        TextView totalMoneyText = (TextView) findViewById(R.id.totalMoneyTextMain);
        totalMoneyText.setText("$" + totalMoney);
    }

    public void openPoker(){
        Intent openPoker = new Intent(this, Poker.class);
        startActivity(openPoker);
        finish();
    }

    public void openBlackjack() {
        Intent openBlackjack = new Intent(this, Blackjack.class);
        startActivity(openBlackjack);
        finish();
    }

    public void openThreeCardPoker() {
        Intent openThreeCardPoker = new Intent(this, ThreeCardPoker.class);
        startActivity(openThreeCardPoker);
        finish();
    }

    public void openReportProblem() {
        Intent openReportProblem = new Intent(this, ReportProblem.class);
        startActivity(openReportProblem);
        finish();
    }

    public void noFreeMoney() {
        Toast.makeText(this, "Free Money Ad failed to load - Sorry, try again later", Toast.LENGTH_LONG).show();
    }

    //Changed
    private void loadRewardedVideoAd() {
        rewardedVideoAdMain.loadAd("ca-app-pub-6280210391658207/1114053462",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        TextView totalMoneyText = findViewById(R.id.totalMoneyTextMain);
        Toast.makeText(this, "Thank you for watching! $5,000 was added to your total money!", Toast.LENGTH_LONG).show();
        // Reward the user.
        totalMoney = totalMoney + 5000;
        totalMoneyText.setText("$" + totalMoney);
        saveTotalMoney();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "Reward Video Failed - Left Application",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {

    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "$5,000 rewarded at end of ad", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoStarted() {

    }

    public void onRewardedVideoCompleted() {

    }
}

