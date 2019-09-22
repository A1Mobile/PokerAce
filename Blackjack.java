package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class Blackjack extends PlayBlackJack implements RewardedVideoAdListener {
    private RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        final Button split = findViewById(R.id.splitButton);
        final Button hit = findViewById(R.id.hitButton);
        final Button stand = findViewById(R.id.standButton);
        final Button freeMoney = findViewById(R.id.freeMoney);

        maxBetAmount = totalMoney/10;
        betMultiplier = maxBetAmount/50;

        TextView totalMoneyText = findViewById(R.id.totalMoneyTextBlackjack);
        totalMoneyText.setText("$" + totalMoney);

        split.setVisibility(View.INVISIBLE);
        hit.setVisibility(View.INVISIBLE);
        stand.setVisibility(View.INVISIBLE);

        freeMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }
                else {
                    noFreeMoney();
                }
            }
        });

        playBlackjack();
    }

    public void noFreeMoney() {
        Toast.makeText(this, "Free Money Ad failed to load - Sorry, try again later", Toast.LENGTH_LONG).show();
    }

    private void loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-6280210391658207/1114053462",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        TextView totalMoneyText = findViewById(R.id.totalMoneyTextBlackjack);
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
