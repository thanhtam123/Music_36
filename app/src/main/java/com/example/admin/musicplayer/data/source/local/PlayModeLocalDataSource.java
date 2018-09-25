package com.example.admin.musicplayer.data.source.local;

import android.content.Context;

import com.example.admin.musicplayer.data.model.PlayMode;
import com.example.admin.musicplayer.data.source.PlayModeDataSource;
import com.example.admin.musicplayer.data.source.local.config.shareprefs.SharedPrefsImpl;

/**
 * Created by TamTT on 9/23/2018.
 */

public class PlayModeLocalDataSource implements PlayModeDataSource {
    private static final String PREF_IS_SHUFFLE = "PREF_IS_SHUFFLE";
    private static final String PREF_LOOP_MODE = "PREF_PLAY_MODE";
    private static PlayModeLocalDataSource sInstance;
    private SharedPrefsImpl mSharedPrefs;

    private PlayModeLocalDataSource(Context context) {
        mSharedPrefs = SharedPrefsImpl.getInstance(context);
    }

    public static synchronized PlayModeLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (PlayModeLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new PlayModeLocalDataSource(context);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void savePlayMode(PlayMode mode) {
        if (mode == null) {
            return;
        }
        mSharedPrefs.put(PREF_IS_SHUFFLE, mode.isShuffle());
        mSharedPrefs.put(PREF_LOOP_MODE, mode.getLoopMode());
    }

    @Override
    public PlayMode getPlayMode() {
        PlayMode mode = new PlayMode();
        mode.setLoopMode(mSharedPrefs.get(PREF_LOOP_MODE, Integer.class));
        mode.setShuffle(mSharedPrefs.get(PREF_IS_SHUFFLE, Boolean.class));
        return mode;
    }
}
