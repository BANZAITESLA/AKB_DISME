package com.disu.disme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Entity (tableName = "video_table")
public class VideoData {
    @PrimaryKey
    int id;

    @ColumnInfo(name = "thumbnail")
    String thumbnail;

    @ColumnInfo (name = "title_media")
    String title_media;

    @ColumnInfo (name = "author_media")
    String author_media;

    @ColumnInfo (name = "media")
    String media;

    public VideoData(int id, String thumbnail, String title_media, String author_media, String media) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title_media = title_media;
        this.author_media = author_media;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle_media() {
        return title_media;
    }

    public void setTitle_media(String title_media) {
        this.title_media = title_media;
    }

    public String getAuthor_media() {
        return author_media;
    }

    public void setAuthor_media(String author_media) {
        this.author_media = author_media;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
