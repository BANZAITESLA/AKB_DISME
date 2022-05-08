package com.disu.disme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Entity (tableName = "friend_list_table")
public class FriendListData {

    public FriendListData(int id, String image, String name, String username) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.username = username;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "username")
    private String username;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
