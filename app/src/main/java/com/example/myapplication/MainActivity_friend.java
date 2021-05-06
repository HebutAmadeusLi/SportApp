package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Vector;

public class MainActivity_friend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_friend);
        if(LogAccount.isLogin()) {
            LinearLayout main = findViewById(R.id.friend_list);
            Vector<String> ret = CacheTool.friendList(LogAccount.account);
            for(int i = 0; i < ret.size(); ++i) {
                TextView child = new TextView(this);
                child.setTextSize(24);
                child.setText(ret.elementAt(i));
                main.addView(child);
            }
        }
        else {
            LinearLayout main = findViewById(R.id.friend_list);
            TextView child = new TextView(this);
            child.setTextSize(24);
            child.setText("请先登录！");
            main.addView(child);
        }
        Button add_friend_button = findViewById(R.id.add_friend_commit);
        add_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!LogAccount.isLogin()) {
                    return;
                }
                EditText email = findViewById(R.id.add_friend_text);
                String email_text = email.getText().toString();
                CacheTool.addFriend(LogAccount.account, email_text);
                Intent intent = new Intent(MainActivity_friend.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button delete_friend_button = findViewById(R.id.delete_friend_commit);
        delete_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!LogAccount.isLogin()) {
                    return;
                }
                EditText email = findViewById(R.id.delete_friend_text);
                String email_text = email.getText().toString();
                CacheTool.deleteFriend(LogAccount.account, email_text);
                Intent intent = new Intent(MainActivity_friend.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
