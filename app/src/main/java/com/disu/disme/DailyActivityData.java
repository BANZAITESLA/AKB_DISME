package com.disu.disme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 04/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Entity (tableName = "daily_activity_table")
public class DailyActivityData {

    public DailyActivityData(int id, String image, String title, String desc) {
        this.id = id;
        this.image = image;
        this.title= title;
        this.desc = desc;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo (name = "image")
    private String image;

    @ColumnInfo (name = "title")
    private String title;

    @ColumnInfo (name = "desc")
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
