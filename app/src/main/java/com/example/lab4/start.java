package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class start extends AppCompatActivity {
TextView sec, dif;
String time, how_hard;
Button difficulty, group;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        sec = (TextView)findViewById(R.id.textView10);
        dif = (TextView)findViewById(R.id.textView11);
        difficulty = (Button)findViewById(R.id.button8);
        group = (Button)findViewById(R.id.button9);
        registerForContextMenu(difficulty);
        addListenerOnButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.time_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.seconds_75:
                sec.setText("75");
                return true;
            case R.id.seconds_60:
                sec.setText("60");
                return true;
            case R.id.seconds_45:
                sec.setText("45");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.difficulty_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.easy:
                dif.setText("easy");
                return true;
            case R.id.medium:
                dif.setText("medium");
                return true;
            case R.id.hard:
                dif.setText("hard");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addListenerOnButton() {
        Button start_btn = (Button) findViewById(R.id.button3);
        Button leave_btn = (Button) findViewById(R.id.button4);
        start_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        time = sec.getText().toString();
                        how_hard = dif.getText().toString();
                        timer();

                    }
                }
        );
        leave_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );
    }
    public void timer(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("timer",time);
        intent.putExtra("difficulty",how_hard);
        startActivity(intent);
    }
}