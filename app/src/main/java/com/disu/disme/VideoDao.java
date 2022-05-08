package com.disu.disme;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Dao
public interface VideoDao {
    @Query("SELECT * FROM video_table")
    List<VideoData> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(VideoData... music);
}
