package com.example.admin.musicplayer.data.source;

import com.example.admin.musicplayer.data.model.PlayMode;

/**
 * Created by TamTT on 9/23/2018.
 */

public interface PlayModeDataSource {
    void savePlayMode(PlayMode mode);

    PlayMode getPlayMode();
}
