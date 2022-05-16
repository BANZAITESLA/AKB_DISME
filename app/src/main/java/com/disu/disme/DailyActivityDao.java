package com.disu.disme;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * 05/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Dao
public interface DailyActivityDao {
    @Query("SELECT * FROM daily_activity_table")
    List<DailyActivityData> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DailyActivityData... daily_activity);
}
