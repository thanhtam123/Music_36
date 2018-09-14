package com.example.admin.musicplayer.data.source.remote.config.service;

/**
 * Created by TamTT on 9/14/2018.
 */

public class SoundCloundClient extends ServiceClient {

    private static final String SOUND_CLOUND_BASE_URL = "https://api-v2.soundcloud.com/";
    private static SoundCloundApi sSoundCloundApi;

    public static SoundCloundApi getInstance() {
        if (sSoundCloundApi == null) {
            return createService(SoundCloundApi.class, SOUND_CLOUND_BASE_URL);
        }
        return sSoundCloundApi;
    }
}
