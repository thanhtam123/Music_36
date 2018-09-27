package com.example.admin.musicplayer.screen.player;

import com.example.admin.musicplayer.data.model.LoopType;
import com.example.admin.musicplayer.data.model.Track;

/**
 * Created by TamTT on 9/20/2018.
 */

public interface MediaListener {

    void preparePlayTrack();

    void play();

    boolean isPlaying();

    void next();

    void previous();

    void onShuffleChange(boolean isShuffer);

    void onLoopStateChange(@LoopType int type);

    void seekTo(int position);

    int getCurrentPosition();

    Track getCurrentTrack();

    int getDuration();

}
