package com.disu.disme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 15/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Entity (tableName = "onboarding_table")
public class OnboardingData {
    @PrimaryKey
    int id;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo (name = "desc_onboarding")
    String desc_onboarding;

    @ColumnInfo (name = "image")
    String image;

    public OnboardingData(int id, String title, String desc_onboarding, String image) {
        this.id = id;
        this.title = title;
        this.desc_onboarding = desc_onboarding;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc_onboarding;
    }

    public void setDesc(String desc_onboarding) {
        this.desc_onboarding = desc_onboarding;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
