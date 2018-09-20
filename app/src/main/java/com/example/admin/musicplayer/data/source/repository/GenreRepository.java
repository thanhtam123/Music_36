package com.example.admin.musicplayer.data.source.repository;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.source.GenreDataSource;
import com.example.admin.musicplayer.data.source.remote.GenreRemoteDataSource;

import io.reactivex.Single;

/**
 * Created by TamTT on 9/14/2018.
 */

public class GenreRepository implements GenreDataSource.RemoteDataSource,
        GenreDataSource.LocalDataSource {
    private static GenreRepository sInstance;
    private GenreDataSource.RemoteDataSource mGenreRemoteDataSource;

    private GenreRepository(GenreDataSource.RemoteDataSource genreRemoteDataSource) {
        mGenreRemoteDataSource = genreRemoteDataSource;
    }

    public static synchronized GenreRepository getInstance() {
        if (sInstance == null) {
            sInstance = new GenreRepository(GenreRemoteDataSource.getGenreRemoteDataSource());
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
}
