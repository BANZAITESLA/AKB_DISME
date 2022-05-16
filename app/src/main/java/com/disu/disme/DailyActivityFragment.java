package com.disu.disme;

import static com.disu.disme.AppDatabase.MIGRATION_1_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

/** 04/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6 */

public class DailyActivityFragment extends Fragment {

    RecyclerView recyclerViewList;
    RecyclerView.Adapter adapter_daily;

    RecyclerView recyclerViewHorizontal;
    RecyclerView.Adapter adapter_friend;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(getContext().getApplicationContext(), AppDatabase.class, "disme")
                .addMigrations(MIGRATION_1_2).allowMainThreadQueries()
                .build();

        db.dailyActivityDao().insertAll(new DailyActivityData(1, "img_daily_activity_1","Bukber", "yang lain teraweh ini malah mabal dengan gaya.."));
        db.dailyActivityDao().insertAll(new DailyActivityData(2, "img_daily_activity_2","Kang Ganggu", "digangguin mulu makhluk berbulu"));
        db.dailyActivityDao().insertAll(new DailyActivityData(3, "img_daily_activity_3","Study with me!", "study, rain, music on, andd hot chocolate!!! :)"));

        db.friendListDao().insertAll(new FriendListData(1, "img_friend_list_1","Abu Fernando Malinowski", "@akukabur"));
        db.friendListDao().insertAll(new FriendListData(2, "img_friend_list_2","Abu Naum", "@Ilovesleep"));
        db.friendListDao().insertAll(new FriendListData(3, "img_friend_list_3","Lily Fernanda Malinowski", "@MommyLily"));

        List<DailyActivityData> daily_activity = db.dailyActivityDao().getAllData();
        List<FriendListData> friend_list = db.friendListDao().getAllData();

        adapter_daily = new DailyAdapter(daily_activity);
        adapter_friend = new FriendAdapter(friend_list);

        recyclerViewList = getView().findViewById(R.id.list_view_daily);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewList.setAdapter(adapter_daily);

        recyclerViewHorizontal = getView().findViewById(R.id.horizontal_view_friend);
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHorizontal.setAdapter(adapter_friend);
    }
}