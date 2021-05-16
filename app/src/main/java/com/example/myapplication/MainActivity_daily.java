package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        EditText text_in = new EditText(this);
        Button submit = new Button(this);
        LinearLayout main = findViewById(R.id.daily_window);

        if(LogAccount.isLogin()) {
            if(CacheTool.isBookedToday(LogAccount.account)) {
                child.setText("您今日已打卡，明日再来！");
            }
            else {
                child.setText("请输入今日运动计划");
                text_in.setWidth(200);
                main.addView(text_in);
                TextView t1 = new TextView(this);
                t1.setText("选择打卡");
                LinearLayout child1 = new LinearLayout(this);
                child1.setOrientation(LinearLayout.HORIZONTAL);
                Button b1 = new Button(this);
                b1.setWidth(50);
                b1.setText("游泳打卡");
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        t1.setText("游泳打卡");
                    }
                });
                child1.addView(b1);
                Button b2 = new Button(this);
                b2.setWidth(50);
                b2.setText("跑步打卡");
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        t1.setText("跑步打卡");
                    }
                });
                child1.addView(b2);
                Button b3 = new Button(this);
                b3.setWidth(50);
                b3.setText("健身打卡");
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        t1.setText("健身打卡");
                    }
                });
                child1.addView(b3);
                main.addView(child1);
                t1.setTextSize(24);
                main.addView(t1);
                submit.setText("提交");
                main.addView(submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = text_in.getText().toString();
                        if(!t1.getText().toString().equals("选择打卡")) {
                            CacheTool.book(LogAccount.account, text, t1.getText().toString());
                            Intent intent = new Intent(MainActivity_daily.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
        else {
            child.setText("请先登录！");
        }
    }
}
