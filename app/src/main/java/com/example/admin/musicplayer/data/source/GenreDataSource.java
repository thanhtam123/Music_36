package com.example.admin.musicplayer.data.source;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.GenreType;

import io.reactivex.Single;

/**
 * Created by TamTT on 9/14/2018.
 */

public interface GenreDataSource {

    interface LocalDataSource {

    }

    interface RemoteDataSource {
        Single<Genre> getSingleGenre(String kind, @GenreType String type, String apiKey);
    }
}
