package com.example.admin.musicplayer.data.source.remote;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.GenreType;
import com.example.admin.musicplayer.data.source.GenreDataSource;
import com.example.admin.musicplayer.data.source.remote.config.service.SoundCloundApi;
import com.example.admin.musicplayer.data.source.remote.config.service.SoundCloundClient;

import io.reactivex.Single;

/**
 * Created by TamTT on 9/14/2018.
 */

public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {
    private static GenreRemoteDataSource sGenreRemoteDataSource;
    private SoundCloundApi mSoundCloundApi;

    private GenreRemoteDataSource(SoundCloundApi nameApi) {
        mSoundCloundApi = nameApi;
    }

    public static synchronized GenreRemoteDataSource getGenreRemoteDataSource() {
        if (sGenreRemoteDataSource == null) {
            sGenreRemoteDataSource = new GenreRemoteDataSource(SoundCloundClient.getInstance());
        }
        return sGenreRemoteDataSource;
    }

    @Override
    public Single<Genre> getSingleGenre(String kind, @GenreType String type, String apiKey) {
        return mSoundCloundApi.getSingleGenre(kind, type, apiKey);
    }
}
