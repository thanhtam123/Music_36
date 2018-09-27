package com.example.admin.musicplayer.screen.player;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.LoopType;
import com.example.admin.musicplayer.data.model.PlayMode;
import com.example.admin.musicplayer.data.source.repository.PlayModeRepository;

/**
 * Created by TamTT on 9/24/2018.
 */

public class PlayerViewModel extends AndroidViewModel{
    private PlayModeRepository mPlayModeRepository;
    public ObservableField<Integer> positionSeekBar = new ObservableField<>();
    public ObservableField<Integer> maxSeekBar = new ObservableField<>();
    public ObservableField<Boolean> isShuffle = new ObservableField<>();
    public ObservableField<Integer> typeLoop = new ObservableField<>();
    public ObservableField<Boolean> playState = new ObservableField<>();
    public ObservableField<Integer> loopRes = new ObservableField<>();

    private Observable.OnPropertyChangedCallback mPropertyChangedCallback =
            new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int i) {

                }
            };

    public PlayerViewModel(@NonNull Application application) {
        super(application);
    }

    void setPlayModeRepository(PlayModeRepository playModeRepository){
        mPlayModeRepository = playModeRepository;
    }

    PlayMode getPlayerMode(){
        return mPlayModeRepository.getPlayMode();
    }

    void savePlayerMode(PlayMode mode){
        mPlayModeRepository.savePlayMode(mode);
    }

    void setShuffle(boolean isShuffer){
        isShuffle.set(isShuffer);
    }

    void onSeekBarPositionChange(int position){
        positionSeekBar.set(position);
    }

    void setMaxSeekBar(int max){
        maxSeekBar.set(max);
    }

    void setPlayState(boolean state) {
        playState.set(state);
    }

    void setLoopType(int loopType) {
        typeLoop.set(loopType);
        switch (loopType){
            case LoopType.LOOP_ONE:
                setLoopRes(R.mipmap.ic_loop_one);
                break;
            case LoopType.LOOP_ALL:
                setLoopRes(R.mipmap.ic_loop_all);
                break;
            case LoopType.NO_LOOP:
                setLoopRes(R.mipmap.ic_no_loop);
                break;
        }
    }

    int getLoopRes(){
        return loopRes.get();
    }

    void setLoopRes(@DrawableRes int res){
        loopRes.set(res);
    }

    void onStart(){
        positionSeekBar.addOnPropertyChangedCallback(mPropertyChangedCallback);
        maxSeekBar.addOnPropertyChangedCallback(mPropertyChangedCallback);
        isShuffle.addOnPropertyChangedCallback(mPropertyChangedCallback);
        playState.addOnPropertyChangedCallback(mPropertyChangedCallback);
        typeLoop.addOnPropertyChangedCallback(mPropertyChangedCallback);
        loopRes.addOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    void onStop() {
        positionSeekBar.removeOnPropertyChangedCallback(mPropertyChangedCallback);
        maxSeekBar.removeOnPropertyChangedCallback(mPropertyChangedCallback);
        isShuffle.removeOnPropertyChangedCallback(mPropertyChangedCallback);
        playState.removeOnPropertyChangedCallback(mPropertyChangedCallback);
        typeLoop.removeOnPropertyChangedCallback(mPropertyChangedCallback);
        loopRes.removeOnPropertyChangedCallback(mPropertyChangedCallback);
    }
}
