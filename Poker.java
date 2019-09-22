package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Poker extends ComputerAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poker);
    }

    @Override
    protected void onStart() {
        super.onStart();
        quitButton();
        checkMoney();
    }

    protected void quitButton() {
        Button quitButton = (Button) findViewById(R.id.quitPoker);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    public void openMain(){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }

    protected void onResume() {
        super.onResume();
    }
}


