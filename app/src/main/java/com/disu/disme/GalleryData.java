package com.disu.disme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 07/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Entity (tableName = "gallery_table")
public class GalleryData {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "image")
    private String image;

    public GalleryData(int id, String image) {
        this.id = id;
        this.image = image;
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
}
