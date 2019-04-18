package com.example.loginpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpractice.util.Utility;

import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        Button login = findViewById(R.id.login_button);
        // 创建数据库并导入管理员账号
        Utility.setManager();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = getMD5String(passwordEdit.getText().toString());
                if(Utility.CheckLogin(account, password)) {
                    Intent intent = new Intent(LoginActivity.this, LoginSuccess.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败，请检查账号密码是否正确。"
                    , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算MD5函数
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
