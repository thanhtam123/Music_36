package com.example.admin.musicplayer.data.source.remote.config.service;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.GenreType;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TamTT on 9/14/2018.
 */

public interface SoundCloundApi {

    @GET("/charts")
    Single<Genre> getSingleGenre(@Query("kind") String order,
                                 @Query("genre") @GenreType String type,
                                 @Query("client_id") String apiKey);

    @GET("/charts")
    Single<Genre> getMoreTracksOnGenre(
                                @Query("kind") String order,
                                @Query("genre") @GenreType String type,
                                @Query("offset") int offset,
                                @Query("query_urn") String urn,
                                @Query("client_id") String apiKey);
}
