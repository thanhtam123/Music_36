package com.example.admin.musicplayer.data.source.repository;

import com.example.admin.musicplayer.data.model.PlayMode;
import com.example.admin.musicplayer.data.source.PlayModeDataSource;
import com.example.admin.musicplayer.data.source.local.PlayModeLocalDataSource;

/**
 * Created by TamTT on 9/23/2018.
 */

public class PlayModeRepository implements PlayModeDataSource {

    private static PlayModeRepository sInstance;
    private PlayModeLocalDataSource mDataSource;

    private PlayModeRepository(PlayModeLocalDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static PlayModeRepository getInstance(PlayModeLocalDataSource dataSource) {
        if (sInstance == null) {
            synchronized (PlayModeRepository.class) {
                if (sInstance == null) {
                    sInstance = new PlayModeRepository(dataSource);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void savePlayMode(PlayMode mode) {
        mDataSource.savePlayMode(mode);
    }

    @Override
    public PlayMode getPlayMode() {
        return mDataSource.getPlayMode();
    }
}
