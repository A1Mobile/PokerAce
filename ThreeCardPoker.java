package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class ThreeCardPoker extends Slots implements RewardedVideoAdListener {
    private RewardedVideoAd rewardedVideoAdSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_card_poker);

        final Button freeMoneySlots = findViewById(R.id.freeMoneySlots);

        rewardedVideoAdSlots = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAdSlots.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        freeMoneySlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedVideoAdSlots.isLoaded()) {
                    rewardedVideoAdSlots.show();
                }
                else {
                    noFreeMoney();
                }
            }
        });

        TextView totalMoneyText = findViewById(R.id.totalMoneyTextSlots);
        totalMoneyText.setText("$" + totalMoney);

        maxBetAmount = totalMoney/20;
        betMultiplier = maxBetAmount/50;
    }

    protected void onStart() {
        super.onStart();
        playSlots();
    }

    public void noFreeMoney() {
        Toast.makeText(this, "Free Money Ad failed to load - Sorry, try again later", Toast.LENGTH_LONG).show();
    }

    private void loadRewardedVideoAd() {
        rewardedVideoAdSlots.loadAd("ca-app-pub-6280210391658207/1114053462",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        TextView totalMoneyText = findViewById(R.id.totalMoneyTextSlots);
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
