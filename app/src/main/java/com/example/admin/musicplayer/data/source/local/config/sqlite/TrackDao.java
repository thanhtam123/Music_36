package com.example.admin.musicplayer.data.source.local.config.sqlite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.admin.musicplayer.data.model.Track;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by TamTT on 9/25/2018.
 */
@Dao
public interface TrackDao {
    String TABLE_TRACK = "Tracks";

    @Query("SELECT * FROM " + TABLE_TRACK)
    Single<List<Track>> getAllTracks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTrack(Track track);

    @Query("DELETE FROM " + TABLE_TRACK + " WHERE id = :trackId")
    Completable deleteTrack(String trackId);

    @Query("SELECT * FROM " + TABLE_TRACK + " WHERE id = :trackId")
    Maybe<Track> isDownLoaded(String trackId);
}
