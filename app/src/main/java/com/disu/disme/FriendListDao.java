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
public interface FriendListDao {
    @Query("SELECT * FROM friend_list_table")
    List<FriendListData> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FriendListData... friend_list);
}
