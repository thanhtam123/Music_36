package com.example.admin.musicplayer.data.source.local.config.sqlite;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.admin.musicplayer.data.model.Track;

/**
 * Created by TamTT on 9/25/2018.
 */

@Database(entities = {Track.class}, version = 1)
public abstract class TrackDatabase extends RoomDatabase {
    private static TrackDatabase INSTANCE;
    private static String DATABASE_NAME = "SoundCloud.db";

    public abstract TrackDao trackDao();

    public static TrackDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TrackDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
