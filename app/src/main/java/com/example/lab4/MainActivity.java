package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView gameRules, colorName, colorText, timer, result, seconds, difficulty;
    Button yesButton, noButton;
    int correct, name, text, time;
    String score, how_hard;

    List<String> listNames1 = Arrays.asList("Красный", "Зеленый");
    List<Integer> listColors1 = Arrays.asList(Color.RED, Color.GREEN);
    List<String> listNames2 = Arrays.asList("Красный", "Зеленый", "Синий", "Черный", "Пурпурный", "Голубой");
    List<Integer> listColors2 = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.MAGENTA, Color.CYAN);
    List<String> listNames = Collections.emptyList();
    List<Integer> listColors = Collections.emptyList();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("main_activity", "onCreate");
        setContentView(R.layout.activity_main);
        gameRules = (TextView) findViewById(R.id.textView);
        colorName = (TextView) findViewById(R.id.textView3);
        colorText = (TextView) findViewById(R.id.textView4);
        timer = (TextView) findViewById(R.id.textView2);
        result = (TextView) findViewById(R.id.textView5);
        seconds = (TextView) findViewById(R.id.textView8);
        difficulty = (TextView) findViewById(R.id.textView9);

        yesButton = (Button) findViewById(R.id.button);
        noButton = (Button) findViewById(R.id.button2);
        seconds.setText(getIntent().getStringExtra("timer"));
        difficulty.setText(getIntent().getStringExtra("difficulty"));

        correct = 0;
        time = Integer.valueOf(seconds.getText().toString())*1000;
        seconds.setText("");
        how_hard = difficulty.getText().toString();

        if (how_hard == "easy"){
            listNames.add(listNames1.get(1));
            listColors.add(listColors.get(1));
        }

        colorName.setText(listNames.get((int) (Math.random() * listNames.size())));
        colorName.setTextColor(listColors.get((int) (Math.random() * listColors.size())));
        colorText.setText(listNames.get((int) (Math.random() * listNames.size())));
        colorText.setTextColor(listColors.get((int) (Math.random() * listColors.size())));

        name = listNames.indexOf(colorName);
        text = listColors.indexOf(colorText.getCurrentTextColor());

        CountDownTimer my_timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {timer.setText(Long.toString(l / 1000));}

            @Override
            public void onFinish() {
                timer.setText("Игра завершена");
                score = Integer.toString(correct);
                results();

            }
        };

        my_timer.start();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name == text)
                    correct++;

                colorName.setText(listNames.get((int) (Math.random() * listNames.size())));
                colorName.setTextColor(listColors.get((int) (Math.random() * listColors.size())));
                colorText.setText(listNames.get((int) (Math.random() * listNames.size())));
                colorText.setTextColor(listColors.get((int) (Math.random() * listColors.size())));

            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name != text)
                    correct++;


                colorName.setText(listNames.get((int) (Math.random() * listNames.size())));
                colorName.setTextColor(listColors.get((int) (Math.random() * listColors.size())));
                colorText.setText(listNames.get((int) (Math.random() * listNames.size())));
                colorText.setTextColor(listColors.get((int) (Math.random() * listColors.size())));
            }
        });
    }
    public void results(){
        Intent intent = new Intent(this,result.class);
        intent.putExtra("key",score);
        startActivity(intent);
    }
}