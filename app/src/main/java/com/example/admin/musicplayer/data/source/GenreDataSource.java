package com.example.admin.musicplayer.data.source;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.GenreType;
import com.example.admin.musicplayer.data.model.Track;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by TamTT on 9/14/2018.
 */

public interface GenreDataSource {

    interface LocalDataSource {

         Single<List<Track>> getAllTracks();

         Completable dowloadTrack(Track track);

         Completable deleteTrack(String idTrack);

         Maybe<Track> isDownloaded(String id);
    }

    interface RemoteDataSource {
        Single<Genre> getSingleGenre(String kind, @GenreType String type, String apiKey);

        Single<Genre> getMoreTracksOnGenre(String kind,
                                           @GenreType String type,
                                           int offset,
                                           String urn,
                                           String apiKey);
    }
}
