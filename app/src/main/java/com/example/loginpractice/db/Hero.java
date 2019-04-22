package com.example.loginpractice.db;

public class Hero {
    // 名字
    private String name;
    // 图片id
    private int imageId;
    // 介绍
    private String introduction;
    // 物种
    private String species;
    // 性别
    private String sex;
    // 能力
    private String power;

    public Hero(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public Hero(String name, int imageId, String sex, String species, String introduction, String power) {
        this.name = name;
        this.imageId = imageId;
        this.sex = sex;
        this.species = species;
        this.introduction = introduction;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
