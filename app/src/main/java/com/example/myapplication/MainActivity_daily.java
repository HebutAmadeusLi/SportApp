package com.example.myapplication;

import android.annotation.SuppressLint;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity_daily extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_daily);
        TextView child = findViewById(R.id.daily_notice);
        if(LogAccount.isLogin()) {
            if(CacheTool.isBookedToday(LogAccount.account)) {
                child.setText("您今日已打卡，明日再来！");
            }
            else {
                CacheTool.book(LogAccount.account);
                child.setText("打卡成功，次数+1");
            }
        }
        else {
            child.setText("请先登录！");
        }
    }
}
