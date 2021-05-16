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
            Vector<Book> ret = CacheTool.bookedList(LogAccount.account);
            LinearLayout main = findViewById(R.id.booked_list);
            for(int i = 0; i < ret.size(); ++i) {
                TextView child = new TextView(this);
                child.setTextSize(24);
                child.setText(ret.elementAt(i).date);
                main.addView(child);
                TextView childp = new TextView(this);
                childp.setTextSize(24);
                childp.setText(ret.elementAt(i).text);
                main.addView(childp);
                TextView childpp = new TextView(this);
                childpp.setTextSize(24);
                childpp.setText(ret.elementAt(i).type);
                main.addView(childpp);
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
