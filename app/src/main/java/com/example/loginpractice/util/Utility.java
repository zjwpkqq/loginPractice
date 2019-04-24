package com.example.loginpractice.util;

import com.example.loginpractice.MyApplication;
import com.example.loginpractice.R;
import com.example.loginpractice.db.User;

import org.litepal.LitePal;

import java.util.List;

public class Utility {

    public static void setManager() {
        LitePal.getDatabase();
        User user = new User();
        user.setAccount(MyApplication.getContext().getResources().getString(R.string.admin));
        user.setPassword(MyApplication.getContext().getResources().getString(R.string.md5));
        user.setNickname("噬元兽");
        user.setPortraitId(R.drawable.portrait);
        user.save();
    }

    public static boolean CheckLogin(String account, String password) {
        List<User> users = LitePal.where("account = ?", account).find(User.class);
        for (User user : users) {
            if(password.equals(user.getPassword()))
                return true;
        }
        return false;
    }

    public static String getNickname(String account) {
        List<User> users = LitePal.where("account = ?", account).find(User.class);
        return users.get(0).getNickname();
    }

    public static int getPortrait(String account) {
        List<User> users = LitePal.where("account = ?", account).find(User.class);
        return users.get(0).getPortraitId();
    }
}
