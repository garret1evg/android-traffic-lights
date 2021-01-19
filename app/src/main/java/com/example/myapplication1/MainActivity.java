package com.example.myapplication1;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button bStart;
    private LinearLayout indicator;
    private TextView counter;
    private boolean is_open = true;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart = findViewById(R.id.button);
        counter = findViewById(R.id.textView);
        indicator = findViewById(R.id.linearLayout);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCounter();
            }
        });
    }

    private void startCounter(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (is_open) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count < 22) {

                        switch (count){
                            case 0:
                                indicator.setBackgroundResource(R.color.colorRed);
                                break;
                            case 10:
                                indicator.setBackgroundResource(R.color.colorYellow);
                                break;
                            case 12:
                                indicator.setBackgroundResource(R.color.colorGreen);
                                break;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                counter.setText(String.valueOf(count));
                            }
                        });
                        count++;
                    }else {
                        count = 0;
                    }
                }
                count = 0;
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        is_open = false;
    }
}
