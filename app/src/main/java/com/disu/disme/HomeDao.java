package com.disu.disme;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * 08/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Dao
public interface HomeDao {
    @Query("SELECT * FROM home_table")
    List<HomeData> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(HomeData... home);
}

