package com.example.admin.musicplayer.screen.genres;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.GenreType;
import com.example.admin.musicplayer.data.source.repository.GenreRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TamTT on 9/19/2018.
 */

public class GenreViewModel extends AndroidViewModel {
    private GenreRepository mGenreRepository;
    private MutableLiveData<Genre> mGenres;

    public GenreViewModel(@NonNull Application application) {
        super(application);
        mGenreRepository = GenreRepository.getInstance();
        mGenres = new MutableLiveData<>();
    }

    LiveData<Genre> getGenre(String kind, @GenreType String type, int offset, String urn, String api) {
        mGenreRepository.getMoreTracksOnGenre(kind, type, offset, urn, api)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Genre>() {
                    @Override
                    public void accept(Genre genre) throws Exception {
                        mGenres.setValue(genre);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGenres.setValue(null);
                    }
                });
        return mGenres;
    }
}
