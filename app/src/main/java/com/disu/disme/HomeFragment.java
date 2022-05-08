package com.disu.disme;

import static com.disu.disme.AppDatabase.MIGRATION_1_2;
import static com.disu.disme.AppDatabase.MIGRATION_2_3;
import static com.disu.disme.AppDatabase.MIGRATION_3_4;
import static com.disu.disme.AppDatabase.MIGRATION_4_5;
import static com.disu.disme.AppDatabase.MIGRATION_5_6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/** 04/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6 */

public class HomeFragment extends Fragment {

    RecyclerView recyclerViewList;
    RecyclerView.Adapter adapter_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(getContext().getApplicationContext(), AppDatabase.class, "disme")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
                .allowMainThreadQueries()
                .build();

        db.homeDao().insertAll(new HomeData(1, "img_home_1","Hobby","Tidur pagi"));
        db.homeDao().insertAll(new HomeData(2, "img_home_3","Makanan Kesukaan", "Sate, tapi gak ada fotonya wkwkkw"));
        db.homeDao().insertAll(new HomeData(3, "img_home_1", "Minuman Kesukaan","Teh"));
        db.homeDao().insertAll(new HomeData(4, "img_home_1","Interest", "Code World"));
        db.homeDao().insertAll(new HomeData(5, "img_home_2","Cita-cita", "Superhero"));

        List<HomeData> home = db.homeDao().getAllData();

        adapter_home = new HomeAdapter(home);

        recyclerViewList = getView().findViewById(R.id.home_interest);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewList.setAdapter(adapter_home);
    }


}