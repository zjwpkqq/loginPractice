package com.example.loginpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HeroActivity extends AppCompatActivity {

    public static final String HERO_NAME = "hero_name";
    public static final String HERO_IMAGE_ID = "hero_image_id";
    public static final String HERO_SEX = "hero_sex";
    public static final String HERO_SPECIES = "hero_species";
    public static final String HERO_INTRODUCTION = "hero_introduction";
    public static final String HERO_POWER = "hero_power";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);
        Intent intent = getIntent();
        String heroName = intent.getStringExtra(HERO_NAME);
        int heroImageId = intent.getIntExtra(HERO_IMAGE_ID, 0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ImageView heroImageView = findViewById(R.id.hero_image_view);
        TextView heroSex = findViewById(R.id.hero_sex);
        TextView heroSpecies = findViewById(R.id.hero_species);
        TextView heroIntroduction = findViewById(R.id.hero_introduction);
        TextView heroPower = findViewById(R.id.hero_power);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(heroName);
        Glide.with(this).load(heroImageId).into(heroImageView);
        String sex = intent.getStringExtra(HERO_SEX);
        String species = intent.getStringExtra(HERO_SPECIES);
        String introduction = intent.getStringExtra(HERO_INTRODUCTION);
        String power = intent.getStringExtra(HERO_POWER);
        heroSex.setText(sex);
        heroSpecies.setText(species);
        heroIntroduction.setText(introduction);
        heroPower.setText(power);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
