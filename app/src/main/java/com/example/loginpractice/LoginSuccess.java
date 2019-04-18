package com.example.loginpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginSuccess extends AppCompatActivity {

    private ImageView offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        offline = findViewById(R.id.offline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSuccess.this, LoginActivity.class);
                Toast.makeText(LoginSuccess.this, "返回登录界面",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}
