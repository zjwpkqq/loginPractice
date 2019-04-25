package com.example.loginpractice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loginpractice.db.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends Fragment {

    private TextView userName;
    private String nickname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        userName = view.findViewById(R.id.username);
        Bundle bundle = getArguments();
        if (nickname == null)
            nickname = bundle != null ? bundle.getString("nickname") : null;
        userName.setText(nickname);
        final String account = bundle != null ? bundle.getString("account") : null;

        // 修改昵称
        RelativeLayout changeName = view.findViewById(R.id.change_name);
        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNickname(account);
            }
        });

        // 设置头像
        CircleImageView portraitImage = view.findViewById(R.id.portrait_image);
        portraitImage.setImageResource(bundle != null ? bundle.getInt("portrait") : 0);

        // 退出登录
        TextView logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void changeNickname(final String account) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.change_nickname, null);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(getActivity());

        final EditText editText = view.findViewById(R.id.edit_name);
        editText.setText(nickname);
        editText.setSelection(nickname.length());

        inputDialog.setTitle("修改名称").setView(view);
        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                User user = new User();
                user.setNickname(editText.getText().toString());
                user.updateAll("account = ?", account);
                nickname = editText.getText().toString();
                userName.setText(nickname);
            }
        }).show();
    }
}
