package com.disu.disme;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 05/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

@Database(entities = {DailyActivityData.class, FriendListData.class, MusicData.class, VideoData.class, GalleryData.class, HomeData.class, OnboardingData.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DailyActivityDao dailyActivityDao();
    public abstract FriendListDao friendListDao();
    public abstract MusicDao musicDao();
    public abstract VideoDao videoDao();
    public abstract GalleryDao galleryDao();
    public abstract HomeDao homeDao();
    public abstract OnboardingDao onboardingDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "INSERT or REPLACE INTO friend_list_table (id, image, name, username) SELECT id, image, name, username FROM friend_list_table");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS music_table (id INTEGER, image STRING, title_media STRING, author_media STRING, media STRING, PRIMARY KEY(id))");
            database.execSQL(
                    "INSERT or REPLACE INTO music_table (id, image, title_media, author_media, media) SELECT id, image, title_media, author_media, media FROM music_table");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS video_table (id INTEGER NOT NULL, thumbnail TEXT, title_media TEXT, author_media TEXT, media TEXT, PRIMARY KEY(id))");
            database.execSQL(
                    "INSERT or REPLACE INTO video_table (id, thumbnail, title_media, author_media, media) SELECT id, thumbnail, title_media, author_media, media FROM video_table");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS gallery_table (id INTEGER NOT NULL, image TEXT, PRIMARY KEY(id))");
            database.execSQL(
                    "INSERT or REPLACE INTO gallery_table (id, image) SELECT id, image FROM gallery_table");
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS home_table (id INTEGER NOT NULL, image TEXT, name TEXT, desc_int TEXT, PRIMARY KEY(id))");
            database.execSQL(
                    "INSERT or REPLACE INTO home_table (id, image, name, desc_int) SELECT id, image, name, desc_int FROM home_table");
        }
    };

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS onboarding_table (id INTEGER NOT NULL, title TEXT, desc_onboarding TEXT, image TEXT, PRIMARY KEY(id))");
            database.execSQL(
                    "INSERT or REPLACE INTO onboarding_table (id, title, desc_onboarding, image) SELECT id, title, desc_onboarding, image FROM onboarding_table");
        }
    };
}
