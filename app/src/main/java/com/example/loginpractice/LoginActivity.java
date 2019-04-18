package com.example.loginpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpractice.util.Utility;

import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private SharedPreferences pref;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = findViewById(R.id.remember_pass);
        Button login = findViewById(R.id.login_button);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        // 创建数据库并导入管理员账号
        Utility.setManager();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(Utility.CheckLogin(account, getMD5String(password))) {
                    CheckRemember(account, password);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败，请检查账号密码是否正确。"
                    , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void CheckRemember(String account, String password) {
        SharedPreferences.Editor editor = pref.edit();
        if (rememberPass.isChecked()) {  // 检查复选框是否被选中
            editor.putBoolean("remember_password", true);
            editor.putString("account", account);
            editor.putString("password", password);
        } else {
            editor.clear();
        }
        editor.apply();
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
