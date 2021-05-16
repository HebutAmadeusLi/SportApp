package com.example.myapplication;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Vector;

public class MainActivity_book extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_book);
        LinearLayout main = findViewById(R.id.book_main);
        Vector<Url> urls = CacheTool.getAllUrl();
        for(int i = 0; i < urls.size(); ++i) {
            TextView child = new TextView(this);
            child.setTextSize(24);
            child.setText(urls.elementAt(i).title);
            main.addView(child);
            TextView childp = new TextView(this);
            childp.setTextSize(24);
            childp.setText(urls.elementAt(i).url);
            main.addView(childp);
        }
    }
}
