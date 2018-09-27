package com.example.admin.musicplayer.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.example.admin.musicplayer.BuildConfig;
import com.example.admin.musicplayer.data.model.LoopType;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.screen.player.OnUpdateUiListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by TamTT on 9/20/2018.
 */

public class SongManager implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener {
    private static String BASE_URL_PLAY_MUSIC = "http://api.soundcloud.com/tracks/";
    private static String URL = "/stream?client_id=";
    private Context mContext;
    private int mPosition;
    private ArrayList<Track> mTracks;
    private ArrayList<Track> mUnShuffleTracks;
    private MediaPlayer mMediaPlayer;
    private String mUrl;
    private OnUpdateUiListener mUiListener;
    private boolean mIsLoopOne;
    private boolean mIsLoopAll;

    SongManager(Context context, int position, ArrayList<Track> tracks) {
        mContext = context;
        mTracks = tracks;
        mMediaPlayer = new MediaPlayer();
        mUnShuffleTracks = new ArrayList<>();
        mUnShuffleTracks.addAll(mTracks);
        mPosition = position;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mIsLoopOne) {
            setData();
        } else {
            next();
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mUiListener.updateStateButton(mediaPlayer.isPlaying());
        mUiListener.onUpdateSeekbar();
    }

    private void buildUrlPlayMusic(int trackId) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL_PLAY_MUSIC)
                .append(trackId)
                .append(URL)
                .append(BuildConfig.ApiKey);
        mUrl = builder.toString();
    }

    void setUiListener(OnUpdateUiListener listener) {
        mUiListener = listener;
    }

    void setData() {
        try {
            if (mMediaPlayer.isPlaying() || mMediaPlayer != null) {
                destroyMediaPlayer();
            }
            mMediaPlayer = new MediaPlayer();
            buildUrlPlayMusic(mTracks.get(mPosition).getId());
            mMediaPlayer.setDataSource(mContext, Uri.parse(mUrl));
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnErrorListener(this);
            mMediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void destroyMediaPlayer() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    void play() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        } else {
            mMediaPlayer.pause();
        }
        mUiListener.updateStateButton(isPlaying());
    }

    void next() {
        if (mIsLoopAll) {
            if (mPosition == mTracks.size() - 1) {
                mPosition = 0;
            } else {
                mPosition++;
            }
        } else {
            if (mPosition == mTracks.size() - 1) {
                return;
            } else {
                mPosition++;
            }
        }
        mUiListener.onUpdateUiPlay(getCurrentTrack());
        setData();
    }

    void previous() {
        if (mPosition > 0) {
            mPosition--;
        } else {
            mPosition = 0;
        }
        mUiListener.onUpdateUiPlay(getCurrentTrack());
        setData();
    }

    void shuffer() {
        Track track = mTracks.get(mPosition);
        mTracks.remove(mPosition);
        swapList();
        mTracks.add(mPosition, track);
        mUiListener.onShuffleStateChange(true);
    }

    private void swapList() {
        Set<Track> newTracks = new HashSet<>();
        while (newTracks.size() != mTracks.size()) {
            Random random = new Random();
            newTracks.add(mTracks.get(random.nextInt(mTracks.size())));
        }
        mTracks.clear();
        mTracks.addAll(newTracks);
    }

    void unShuffle() {
        mTracks.clear();
        mTracks.addAll(mUnShuffleTracks);
        mPosition = mTracks.indexOf(mTracks.get(mPosition));
        mUiListener.onShuffleStateChange(false);
    }

    void loopOne() {
        mMediaPlayer.setLooping(true);
        mIsLoopOne = true;
        mIsLoopAll = false;
        mUiListener.onLoopStateChange(LoopType.LOOP_ONE);
    }

    void loopAll() {
        mMediaPlayer.setLooping(false);
        mIsLoopOne = false;
        mIsLoopAll = true;
        mUiListener.onLoopStateChange(LoopType.LOOP_ALL);
    }

    void loopOff() {
        mIsLoopAll = false;
        mIsLoopOne = false;
        mUiListener.onLoopStateChange(LoopType.NO_LOOP);
    }

    Track getCurrentTrack() {
        return mTracks.get(mPosition);
    }

    boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    void seekTo(int position) {
        mMediaPlayer.seekTo(position);
    }

    int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    int getDuration() {
        return mMediaPlayer.getDuration();
    }
}
