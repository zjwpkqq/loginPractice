package com.example.loginpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loginpractice.db.Hero;

import java.util.List;

import static com.example.loginpractice.MyApplication.getContext;

public class HeroAdapter extends BaseAdapter {

    private Context context;

    private List<Hero> heroList;

    private int resourceId;

    public HeroAdapter(Context context, int resourceId, List<Hero> heroList) {
        this.context = context;
        this.resourceId = resourceId;
        this.heroList = heroList;
    }

    @Override
    public int getCount() {
        return heroList.size();
    }

    @Override
    public Hero getItem(int position) {
        return heroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Hero hero = getItem(position); // 获取当前项的Hero实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.heroImage = view.findViewById(R.id.hero_image);
            viewHolder.heroName = view.findViewById(R.id.hero_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(hero.getImageId()).into(viewHolder.heroImage);
        viewHolder.heroName.setText(hero.getName());
        return view;
    }

    class ViewHolder {

        ImageView heroImage;

        TextView heroName;
    }
}
