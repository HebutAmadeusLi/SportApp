package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity_log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log);

        Button login_button = findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.username);
                String email_text = email.getText().toString();
                EditText password = findViewById(R.id.password);
                String password_text = password.getText().toString();
                try {
                    if(CacheTool.account.containsKey(email_text) && !CacheTool.account.get(email_text).equals(password_text)) {
                        email.setText("");
                        password.setText("");
                        email.setHint("请输入正确用户名");
                        password.setHint("请输入正确密码");
                    }
                    else if(!CacheTool.AccountIsIn(email_text, password_text)) {
                        CacheTool.CreateAccount(email_text, password_text);
                        LogAccount.login(email_text);
                        Intent intent = new Intent(MainActivity_log.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        LogAccount.login(email_text);
                        Intent intent = new Intent(MainActivity_log.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
