package com.example.admin.musicplayer.data.source.repository;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.data.source.GenreDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by TamTT on 9/14/2018.
 */

public class GenreRepository implements GenreDataSource.RemoteDataSource,
        GenreDataSource.LocalDataSource {
    private static GenreRepository sInstance;
    private GenreDataSource.RemoteDataSource mGenreRemoteDataSource;
    private GenreDataSource.LocalDataSource mLocalDataSource;

    private GenreRepository(
            GenreDataSource.RemoteDataSource genreRemoteDataSource,
            GenreDataSource.LocalDataSource localDataSource) {
        mGenreRemoteDataSource = genreRemoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static synchronized GenreRepository getInstance(
            GenreDataSource.RemoteDataSource genreRemoteDataSource,
            GenreDataSource.LocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(genreRemoteDataSource,localDataSource);
        }
        return sInstance;
    }

    @Override
    public Single<Genre> getSingleGenre(String kind, String type, String apiKey) {
        return mGenreRemoteDataSource.getSingleGenre(kind, type, apiKey);
    }

    @Override
    public Single<Genre> getMoreTracksOnGenre(String kind, String type, int offset, String urn, String apiKey) {
        return mGenreRemoteDataSource.getMoreTracksOnGenre(kind, type, offset, urn, apiKey);
    }

    @Override
    public Single<List<Track>> getAllTracks() {
        return mLocalDataSource.getAllTracks();
    }

    @Override
    public Completable dowloadTrack(Track track) {
        return mLocalDataSource.dowloadTrack(track);
    }

    @Override
    public Completable deleteTrack(String idTrack) {
        return mLocalDataSource.deleteTrack(idTrack);
    }

    @Override
    public Maybe<Track> isDownloaded(String id) {
        return mLocalDataSource.isDownloaded(id);
    }
}
