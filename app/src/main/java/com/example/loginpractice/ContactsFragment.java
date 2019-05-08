package com.example.loginpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.loginpractice.db.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactsFragment extends Fragment {
    private List<Hero> heroList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private HeroAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshHeroes();
            }
        });

        if (heroList.size() == 0){
            initHeroes();
        }
        adapter = new HeroAdapter(getActivity(), R.layout.hero_item, heroList);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Hero hero = heroList.get(position);
                Intent intent = new Intent(getActivity(), HeroActivity.class);
                intent.putExtra(HeroActivity.HERO_NAME, hero.getName());
                intent.putExtra(HeroActivity.HERO_IMAGE_ID, hero.getImageId());
                intent.putExtra(HeroActivity.HERO_SEX, hero.getSex());
                intent.putExtra(HeroActivity.HERO_SPECIES, hero.getSpecies());
                intent.putExtra(HeroActivity.HERO_INTRODUCTION, hero.getIntroduction());
                intent.putExtra(HeroActivity.HERO_POWER, hero.getPower());
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        return view;
    }

    // 下拉刷新
    private void refreshHeroes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initHeroes();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initHeroes() {
        String[] sex = {"男", "女", "无"};
        String[] species = {"人类", "阿斯加德人", "半人类半斯巴达星人", "泽侯贝里人", "兽人", "花神巨像族",
                "外星人", "生化人"};
        Hero ironman = new Hero("钢铁侠", R.drawable.iron_man, sex[0], species[0],
                getResources().getString(R.string.iron_man_intro),
                getResources().getString(R.string.iron_man_power));
        heroList.add(ironman);
        Hero captainAmerica = new Hero("美国队长", R.drawable.captain_america, sex[0], species[0],
                getResources().getString(R.string.captain_america_intro),
                getResources().getString(R.string.captain_america_power));
        heroList.add(captainAmerica);
        Hero blackWidow = new Hero("黑寡妇", R.drawable.black_widow, sex[1], species[0],
                getResources().getString(R.string.black_widow_intro),
                getResources().getString(R.string.black_widow_power));
        heroList.add(blackWidow);
        Hero hulk = new Hero("浩克", R.drawable.hulk, sex[0], species[0],
                getResources().getString(R.string.hulk_intro),
                getResources().getString(R.string.hulk_power));
        heroList.add(hulk);
        Hero hawkeye = new Hero("鹰眼", R.drawable.hawkeye, sex[0], species[0],
                getResources().getString(R.string.hawkeye_intro),
                getResources().getString(R.string.hawkeye_power));
        heroList.add(hawkeye);
        Hero thor = new Hero("雷神", R.drawable.thor, sex[0], species[1],
                getResources().getString(R.string.thor_intro),
                getResources().getString(R.string.thor_power));
        heroList.add(thor);
        Hero captainMarvel = new Hero("惊奇队长", R.drawable.captain_marvel, sex[1], species[0],
                getResources().getString(R.string.captain_marvel_intro),
                getResources().getString(R.string.captain_marvel_power));
        heroList.add(captainMarvel);
        Hero warMachine = new Hero("战争机器", R.drawable.war_machine, sex[0], species[0],
                getResources().getString(R.string.war_machine_intro),
                getResources().getString(R.string.war_machine_power));
        heroList.add(warMachine);
        Hero vision = new Hero("幻视", R.drawable.vision, sex[0], species[7],
                getResources().getString(R.string.vision_intro),
                getResources().getString(R.string.vision_power));
        heroList.add(vision);
        Hero scarletWitch = new Hero("绯红女巫", R.drawable.scarlet_witch, sex[1], species[0],
                getResources().getString(R.string.scarlet_witch_intro),
                getResources().getString(R.string.scarlet_witch_power));
        heroList.add(scarletWitch);
        Hero doctorStrange = new Hero("奇异博士", R.drawable.doctor_strange, sex[0], species[0],
                getResources().getString(R.string.doctor_strange_intro),
                getResources().getString(R.string.doctor_strange_power));
        heroList.add(doctorStrange);
        Hero blackPanther = new Hero("黑豹", R.drawable.black_panther, sex[0], species[0],
                getResources().getString(R.string.black_panther_intro),
                getResources().getString(R.string.black_panther_power));
        heroList.add(blackPanther);
        Hero shuri = new Hero("舒莉", R.drawable.shuri, sex[1], species[0],
                getResources().getString(R.string.shuri_intro),
                getResources().getString(R.string.shuri_power));
        heroList.add(shuri);
        Hero spiderMan = new Hero("蜘蛛侠", R.drawable.spider_man, sex[0], species[0],
                getResources().getString(R.string.spider_man_intro),
                getResources().getString(R.string.spider_man_power));
        heroList.add(spiderMan);
        Hero falcon = new Hero("猎鹰", R.drawable.falcon, sex[0], species[0],
                getResources().getString(R.string.falcon_intro),
                getResources().getString(R.string.falcon_power));
        heroList.add(falcon);
        Hero winterSolder = new Hero("巴基", R.drawable.winter_solder,sex[0], species[0],
                getResources().getString(R.string.winter_solder_intro),
                getResources().getString(R.string.winter_solder_power));
        heroList.add(winterSolder);
        Hero antMan = new Hero("蚁人", R.drawable.ant_man, sex[0], species[0],
                getResources().getString(R.string.ant_man_intro),
                getResources().getString(R.string.ant_man_power));
        heroList.add(antMan);
        Hero wasp = new Hero("黄蜂女", R.drawable.wasp, sex[1], species[0],
                getResources().getString(R.string.wasp_intro),
                getResources().getString(R.string.wasp_power));
        heroList.add(wasp);
        Hero starLord = new Hero("星爵", R.drawable.star_lord, sex[0], species[2],
                getResources().getString(R.string.star_lord_intro),
                getResources().getString(R.string.star_lord_power));
        heroList.add(starLord);
        Hero gamora = new Hero("卡魔拉", R.drawable.gamora, sex[1], species[3],
                getResources().getString(R.string.gamora_intro),
                getResources().getString(R.string.gamora_power));
        heroList.add(gamora);
        Hero rocket = new Hero("火箭浣熊", R.drawable.rocket, sex[0], species[4],
                getResources().getString(R.string.rocket_intro),
                getResources().getString(R.string.rocket_power));
        heroList.add(rocket);
        Hero groot = new Hero("格鲁特", R.drawable.groot, sex[0], species[5],
                getResources().getString(R.string.groot_intro),
                getResources().getString(R.string.groot_power));
        heroList.add(groot);
        Hero drax = new Hero("德拉克斯", R.drawable.drax, sex[0], species[6],
                getResources().getString(R.string.drax_intro),
                getResources().getString(R.string.drax_power));
        heroList.add(drax);
        Hero mantis = new Hero("螳螂女", R.drawable.mantis, sex[1], species[6],
                getResources().getString(R.string.mantis_intro),
                getResources().getString(R.string.mantis_power));
        heroList.add(mantis);
    }
}
