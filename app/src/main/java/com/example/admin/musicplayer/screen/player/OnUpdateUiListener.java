package com.example.admin.musicplayer.screen.player;

import com.example.admin.musicplayer.data.model.LoopType;
import com.example.admin.musicplayer.data.model.Track;

/**
 * Created by TamTT on 9/21/2018.
 */

public interface OnUpdateUiListener {

    void updateStateButton(boolean isPlaying);

    void onUpdateUiPlay(Track track);

    void onUpdateSeekbar();

    void onShuffleStateChange(boolean isShuffle);

    void onLoopStateChange(@LoopType int type);

}
