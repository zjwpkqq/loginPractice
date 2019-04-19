package com.example.loginpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.loginpractice.db.Hero;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {

    private List<Hero> heroList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initHeroes();
        HeroAdapter adapter = new HeroAdapter(getActivity(), R.layout.hero_item, heroList);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return view;
    }

    private void initHeroes() {
        for (int i = 0; i < 20; i++) {
            Hero ironman = new Hero("钢铁侠", R.drawable.ironman);
            heroList.add(ironman);
        }
    }
}
