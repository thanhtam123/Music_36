package com.example.admin.musicplayer.data.model;

import android.support.annotation.StringDef;

import static com.example.admin.musicplayer.data.model.GenreType.ALL_AUDIO;
import static com.example.admin.musicplayer.data.model.GenreType.ALL_MUSIC;
import static com.example.admin.musicplayer.data.model.GenreType.ALTERNATIVEROCK;
import static com.example.admin.musicplayer.data.model.GenreType.AMBIENT;
import static com.example.admin.musicplayer.data.model.GenreType.CLASSICAL;
import static com.example.admin.musicplayer.data.model.GenreType.COUNTRY;

/**
 * Created by TamTT on 9/14/2018.
 */

@StringDef({ALL_MUSIC, ALL_AUDIO, ALTERNATIVEROCK, AMBIENT, CLASSICAL, COUNTRY})
public @interface GenreType {
    String ALL_MUSIC = "soundcloud:genres:all-music";
    String ALL_AUDIO = "soundcloud:genres:all-audio";
    String ALTERNATIVEROCK = "soundcloud:genres:alternativerock";
    String AMBIENT = "soundcloud:genres:ambient";
    String CLASSICAL = "soundcloud:genres:classical";
    String COUNTRY = "soundcloud:genres:country";
}
