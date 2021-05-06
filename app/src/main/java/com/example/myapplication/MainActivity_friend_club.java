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

import java.util.Vector;

public class MainActivity_friend_club extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_friend_club);
        Vector<FriendClub> friendClub = CacheTool.AllFriendClub();
        LinearLayout main1 = findViewById(R.id.friend_club_main);
        for(int i = 0; i < friendClub.size(); ++i) {
            LinearLayout main2 = new LinearLayout(this);
            main2.setOrientation(LinearLayout.VERTICAL);
            TextView user = new TextView(this);
            user.setTextSize(24);
            user.setText("用户：" + friendClub.elementAt(i).email);
            main2.addView(user);
            TextView text = new TextView(this);
            text.setTextSize(20);
            text.setText(friendClub.elementAt(i).text);
            main2.addView(text);
            TextView talk = new TextView(this);
            talk.setTextSize(24);
            talk.setText("评论：");
            main2.addView(talk);
            for(int j = 0; j < friendClub.elementAt(i).talk.size(); ++j) {
                TextView talk_text = new TextView(this);
                talk_text.setTextSize(20);
                talk_text.setText(friendClub.elementAt(i).talk.elementAt(j).email + ": " + friendClub.elementAt(i).talk.elementAt(j).text);
                main2.addView(talk_text);
            }
            if(LogAccount.isLogin()) {
                LinearLayout main3 = new LinearLayout(this);
                main3.setOrientation(LinearLayout.HORIZONTAL);
                TextView note = new TextView(this);
                note.setTextSize(20);
                note.setText("输入你的评论");
                main3.addView(note);
                EditText talk_in = new EditText(this);
                talk_in.setWidth(200);
                main3.addView(talk_in);
                Button talk_commit = new Button(this);
                talk_commit.setText("提交");
                main3.addView(talk_commit);
                int finalI = i;
                talk_commit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CacheTool.addFriendClubTalk(friendClub.elementAt(finalI).id, LogAccount.account, talk_in.getText().toString());
                        Intent intent = new Intent(MainActivity_friend_club.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                main2.addView(main3);
            }
            main1.addView(main2);
        }
        if(LogAccount.isLogin()) {
            TextView note = new TextView(this);
            note.setTextSize(20);
            note.setText("编写朋友圈");
            main1.addView(note);
            EditText talk_in = new EditText(this);
            talk_in.setWidth(200);
            main1.addView(talk_in);
            Button talk_commit = new Button(this);
            talk_commit.setText("提交");
            main1.addView(talk_commit);
            talk_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CacheTool.addFriendClub(LogAccount.account, talk_in.getText().toString());
                    Intent intent = new Intent(MainActivity_friend_club.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            TextView note = new TextView(this);
            note.setTextSize(20);
            note.setText("发帖跟评论请先登录");
            main1.addView(note);
        }
    }
}
