package com.disu.disme;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * 15/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Dao
public interface OnboardingDao {
    @Query("SELECT * FROM onboarding_table")
    List<OnboardingData> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(OnboardingData... screen_item);
}
