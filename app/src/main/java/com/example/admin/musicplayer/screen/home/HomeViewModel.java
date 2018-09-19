package com.example.admin.musicplayer.screen.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.admin.musicplayer.BuildConfig;
import com.example.admin.musicplayer.Constants;
import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.GenreType;
import com.example.admin.musicplayer.data.source.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function6;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TamTT on 9/14/2018.
 */

public class HomeViewModel extends AndroidViewModel {

    private GenreRepository mGenreRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mGenreRepository = GenreRepository.getInstance();
    }

    LiveData<List<Genre>> getAllGenres() {
        final MutableLiveData<List<Genre>> data = new MutableLiveData<>();
        Single.zip(
                mGenreRepository.getSingleGenre(Constants.KIND, GenreType.ALL_MUSIC,
                        BuildConfig.ApiKey),
                mGenreRepository.getSingleGenre(Constants.KIND, GenreType.ALL_AUDIO,
                        BuildConfig.ApiKey),
                mGenreRepository.getSingleGenre(Constants.KIND, GenreType.ALTERNATIVEROCK,
                        BuildConfig.ApiKey),
                mGenreRepository.getSingleGenre(Constants.KIND, GenreType.AMBIENT,
                        BuildConfig.ApiKey),
                mGenreRepository.getSingleGenre(Constants.KIND, GenreType.CLASSICAL,
                        BuildConfig.ApiKey),
                mGenreRepository.getSingleGenre(Constants.KIND, GenreType.COUNTRY,
                        BuildConfig.ApiKey),
                new Function6<Genre, Genre, Genre, Genre, Genre, Genre, List<Genre>>() {
                    @Override
                    public List<Genre> apply(Genre genreAllMusic,
                                             Genre genreAllAudio,
                                             Genre genreAlternativerock,
                                             Genre genreAmbient,
                                             Genre genreClassical,
                                             Genre genreCountry) throws Exception {
                        List<Genre> genres = new ArrayList<>();
                        genres.add(genreAllMusic);
                        genres.add(genreAllAudio);
                        genres.add(genreAlternativerock);
                        genres.add(genreAmbient);
                        genres.add(genreClassical);
                        genres.add(genreCountry);
                        return genres;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Genre>>() {
                    @Override
                    public void accept(List<Genre> genres) throws Exception {
                        data.setValue(genres);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        return data;
    }
}
