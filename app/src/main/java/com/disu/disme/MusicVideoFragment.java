package com.disu.disme;

import static com.disu.disme.AppDatabase.MIGRATION_1_2;
import static com.disu.disme.AppDatabase.MIGRATION_2_3;
import static com.disu.disme.AppDatabase.MIGRATION_3_4;

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

/** 06/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6 */

public class MusicVideoFragment extends Fragment {

    RecyclerView recyclerViewList;
    RecyclerView.Adapter adapter_music;

    RecyclerView recyclerViewVideo;
    RecyclerView.Adapter adapter_video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(getContext().getApplicationContext(), AppDatabase.class, "disme")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .allowMainThreadQueries()
                .build();

        db.musicDao().insertAll(new MusicData(1, "img_music_1","Kingslayer", "Bring Me The Horizon, BABYMETAL", "audio_music_1"));
        db.musicDao().insertAll(new MusicData(2, "img_music_2","Tiny Light", "Akari Kito", "audio_music_2"));
        db.musicDao().insertAll(new MusicData(3, "img_music_3","Love Letter", "YOASOBI", "audio_music_3"));

        db.videoDao().insertAll(new VideoData(1, "thumbnail_video_1","The Hardest Kanji to Write In the World", "Kaligrafi Jepang Takumi", "video_1"));
        db.videoDao().insertAll(new VideoData(2, "thumbnail_video_2","Kitty Kitty Cat Meme", "elyn", "video_2"));
        db.videoDao().insertAll(new VideoData(3, "thumbnail_video_3","Anya Waku Waku for this New Chick", "ChillingWithCQwa", "video_3"));

        List<MusicData> music = db.musicDao().getAllData();
        List<VideoData> video = db.videoDao().getAllData();

        adapter_music = new MusicAdapter(music);
        adapter_video = new VideoAdapter(video);

        recyclerViewList = getView().findViewById(R.id.list_view_music);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewList.setAdapter(adapter_music);

        recyclerViewVideo = getView().findViewById(R.id.video_view);
        recyclerViewVideo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewVideo.setAdapter(adapter_video);
    }
}