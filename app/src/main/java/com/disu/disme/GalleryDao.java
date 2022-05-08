package com.disu.disme;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * 07/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Dao
public interface GalleryDao {
    @Query("SELECT * FROM gallery_table")
    List<GalleryData> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(GalleryData... gallery);
}
