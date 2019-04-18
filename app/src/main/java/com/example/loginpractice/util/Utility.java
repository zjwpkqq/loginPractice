package com.example.loginpractice.util;

import com.example.loginpractice.MyApplication;
import com.example.loginpractice.R;
import com.example.loginpractice.db.User;

import org.litepal.LitePal;

public class Utility {

    public static void setManager() {
        LitePal.getDatabase();
        User user = new User();
        user.setAccount(MyApplication.getContext().getResources().getString(R.string.admin));
        user.setPassword(MyApplication.getContext().getResources().getString(R.string.md5));
        user.save();
    }

    public static boolean CheckLogin(String account, String password) {
        User user = LitePal.find(User.class, 1);
        return account.equals(user.getAccount()) && password.equals(user.getPassword());
    }

}
