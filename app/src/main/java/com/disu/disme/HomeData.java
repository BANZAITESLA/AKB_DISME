package com.disu.disme;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 08/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Entity (tableName = "home_table")
public class HomeData {
    @PrimaryKey
    private int id;

    @Nullable @ColumnInfo (name = "image")
    private String image;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "desc_int")
    private String desc_int;

    public HomeData(int id, String image, String name, String desc_int) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.desc_int = desc_int;
    }

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

    public String getDesc_int() {
        return desc_int;
    }

    public void setDesc_int(String desc_int) {
        this.desc_int = desc_int;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
