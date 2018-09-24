package com.example.admin.musicplayer.data.model;

/**
 * Created by TamTT on 9/23/2018.
 */

public class PlayMode {
    private boolean mIsShuffle;
    private int mLoopMode;

    public boolean isShuffle() {
        return mIsShuffle;
    }

    public void setShuffle(boolean shuffle) {
        mIsShuffle = shuffle;
    }

    public int getLoopMode() {
        return mLoopMode;
    }

    public void setLoopMode(@LoopType int loopMode) {
        mLoopMode = loopMode;
    }

    @Override
    public String toString() {
        return "PlayMode{" +
                "mIsShuffle=" + mIsShuffle +
                ", mLoopMode=" + mLoopMode +
                '}';
    }
}
