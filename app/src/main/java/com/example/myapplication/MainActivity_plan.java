package com.example.myapplication;

import android.os.Build;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Vector;

public class MainActivity_plan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plan);

        if(LogAccount.isLogin()) {
            Vector<String> ret = CacheTool.bookedList(LogAccount.account);
            LinearLayout main = findViewById(R.id.booked_list);
            for(int i = 0; i < ret.size(); ++i) {
                TextView child = new TextView(this);
                child.setTextSize(24);
                child.setText(ret.elementAt(i));
                main.addView(child);
            }
        }
        else {
            LinearLayout main = findViewById(R.id.booked_list);
            TextView child = new TextView(this);
            child.setTextSize(24);
            child.setText("请先登录!");
            main.addView(child);
        }
    }
}
