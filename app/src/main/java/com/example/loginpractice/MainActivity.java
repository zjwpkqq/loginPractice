package com.example.loginpractice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    // Tab文字
    private final int[] TAB_TITLES = new int[] {R.string.wechat, R.string.contacts,
    R.string.find, R.string.me};
    // Tab图片
    private final int[] TAB_IMAGES = new int[] {R.drawable.tab_wechat_selector,
    R.drawable.tab_contacts_selector, R.drawable.tab_find_selector, R.drawable.tab_me_selector};
    // fragment数组
    private final Fragment[] TAB_FRAGMENTS = new Fragment[] {new WeChatFragment(),
    new ContactsFragment(), new FindFragment(), new MeFragment()};
    // Tab数量
    private final int COUNT = TAB_TITLES.length;
    private MyViewPagerAdapter adapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tabLayout);
        setTabs(tabLayout, this.getLayoutInflater(), TAB_TITLES, TAB_IMAGES);

        adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    /**
     * 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitles, int[] tabImages) {
        for (int i = 0 ; i < tabImages.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom, null);
            tab.setCustomView(view);

            TextView tvTitle = view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitles[i]);
            ImageView imgTab = view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImages[i]);
            tabLayout.addTab(tab);
        }
    }

    /**
     * ViewPager适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }

    }
}
