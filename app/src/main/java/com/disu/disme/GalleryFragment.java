package com.disu.disme;

import static com.disu.disme.AppDatabase.MIGRATION_1_2;
import static com.disu.disme.AppDatabase.MIGRATION_2_3;
import static com.disu.disme.AppDatabase.MIGRATION_3_4;
import static com.disu.disme.AppDatabase.MIGRATION_4_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

/** 07/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6 */

public class GalleryFragment extends Fragment {

    RecyclerView recyclerViewGrid;
    RecyclerView.Adapter adapter_gallery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(getContext().getApplicationContext(), AppDatabase.class, "disme")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
                .allowMainThreadQueries()
                .build();

        db.galleryDao().insertAll(new GalleryData(1, "img_gallery_1"));
        db.galleryDao().insertAll(new GalleryData(2, "img_gallery_2"));
        db.galleryDao().insertAll(new GalleryData(3, "img_gallery_3"));
        db.galleryDao().insertAll(new GalleryData(4, "img_gallery_4"));
        db.galleryDao().insertAll(new GalleryData(5, "img_gallery_5"));
        db.galleryDao().insertAll(new GalleryData(6, "img_gallery_6"));
        db.galleryDao().insertAll(new GalleryData(7, "img_gallery_7"));
        db.galleryDao().insertAll(new GalleryData(8, "img_gallery_8"));
        db.galleryDao().insertAll(new GalleryData(9, "img_gallery_9"));
        db.galleryDao().insertAll(new GalleryData(10, "img_gallery_10"));
        db.galleryDao().insertAll(new GalleryData(11, "img_gallery_11"));
        db.galleryDao().insertAll(new GalleryData(12, "img_gallery_12"));

        List<GalleryData> gallery = db.galleryDao().getAllData();

        adapter_gallery = new GalleryAdapter(gallery);

        recyclerViewGrid = getView().findViewById(R.id.grid_gallery);
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewGrid.setAdapter(adapter_gallery);
    }
}