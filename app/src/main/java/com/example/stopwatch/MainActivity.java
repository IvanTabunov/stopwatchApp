package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.ActionMode;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextView textViewTimer;
    private int seconds = 0;
    private boolean IsRunning = false;

    private boolean wasRunning = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTimer = findViewById(R.id.textViewTimer);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            IsRunning = savedInstanceState.getBoolean("IsRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }


        runTimer();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("IsRunning", IsRunning);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = IsRunning;
        IsRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IsRunning = wasRunning;
    }

    public void onClickStartTimer(View view) {
        IsRunning = true;
    }

    public void onClickPauseTimer(View view) {
        IsRunning = false;
    }

    public void onClickRestartTimer(View view) {
        IsRunning = false;
        seconds = 0;
    }

    private void runTimer(){
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);
                textViewTimer.setText(time);

                if(IsRunning){
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });




    }

}