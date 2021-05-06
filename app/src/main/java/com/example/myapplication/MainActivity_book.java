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
    }
}
