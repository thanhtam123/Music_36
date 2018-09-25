package com.example.admin.musicplayer.data.source.local.config.shareprefs;

/**
 * Created by TamTT on 9/23/2018.
 */

public interface SharedPrefsApi {
    <T> T get(String key, Class<T> clazz);

    <T> void put(String key, T data);

    void clear();
}
