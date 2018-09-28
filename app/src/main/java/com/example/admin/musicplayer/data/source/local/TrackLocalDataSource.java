package com.example.admin.musicplayer.data.source.local;

import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.data.source.GenreDataSource;
import com.example.admin.musicplayer.data.source.local.config.sqlite.TrackDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by TamTT on 9/27/2018.
 */

public class TrackLocalDataSource implements GenreDataSource.LocalDataSource{

    private static TrackLocalDataSource sInstance;

    private TrackDao mTrackDao;

    public TrackLocalDataSource(TrackDao trackDao) {
        mTrackDao = trackDao;
    }

    public static TrackLocalDataSource getInstance(TrackDao trackDao) {
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource(trackDao);
        }
        return sInstance;
    }

    @Override
    public Single<List<Track>> getAllTracks() {
        return mTrackDao.getAllTracks();
    }

    @Override
    public Completable dowloadTrack(Track track) {
        return mTrackDao.insertTrack(track);
    }

    @Override
    public Completable deleteTrack(String idTrack) {
        return mTrackDao.deleteTrack(idTrack);
    }

    @Override
    public Maybe<Track> isDownloaded(String id) {
        return mTrackDao.isDownLoaded(id);
    }
}
