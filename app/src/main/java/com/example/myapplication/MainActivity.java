package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.*;

public class MainActivity extends AppCompatActivity {
    String msg = "Android : ";

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(LogAccount.isLogin()) {
            LinearLayout main = findViewById(R.id.main_windows);
            TextView child = new TextView(this);
            child.setTextSize(24);
            child.setText("欢迎您，用户：" + LogAccount.account);
            main.addView(child, 0);
        }
        else {
            MySQLConnector mc = new MySQLConnector();
            mc.start();
        }

        Button daily_button = findViewById(R.id.daily);
        daily_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_daily.class);
                startActivity(intent);
            }
        });

        Button log_button = findViewById(R.id.log);
        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LogAccount.isLogin()) {
                    LogAccount.logout();
                    LinearLayout main = findViewById(R.id.main_windows);
                    main.removeViewAt(0);
                    Intent intent = new Intent(MainActivity.this, MainActivity_log.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, MainActivity_log.class);
                    startActivity(intent);
                }
            }
        });

        Button friend_button = findViewById(R.id.friend);
        friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_friend.class);
                startActivity(intent);
            }
        });

        Button friend_club_button = findViewById(R.id.friend_club);
        friend_club_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_friend_club.class);
                startActivity(intent);
            }
        });

        Button plan_button = findViewById(R.id.plan);
        plan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_plan.class);
                startActivity(intent);
            }
        });

        Button book_button = findViewById(R.id.book);
        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_book.class);
                startActivity(intent);
            }
        });
    }
}