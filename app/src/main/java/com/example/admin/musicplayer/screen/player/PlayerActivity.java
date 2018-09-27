package com.example.admin.musicplayer.screen.player;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.LoopType;
import com.example.admin.musicplayer.data.model.PlayMode;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.data.source.local.PlayModeLocalDataSource;
import com.example.admin.musicplayer.data.source.repository.PlayModeRepository;
import com.example.admin.musicplayer.databinding.ActivityPlayerBinding;
import com.example.admin.musicplayer.screen.HandlerClick;
import com.example.admin.musicplayer.service.MusicService;

public class PlayerActivity extends AppCompatActivity implements
        OnUpdateUiListener,
        SeekBar.OnSeekBarChangeListener {

    private static final int CONVERT_MINISECOND = 1000;
    private MusicService mService;
    private MediaListener mMediaListener;
    private ActivityPlayerBinding mBinding;
    private PlayMode mPlayMode;
    private boolean mBinded;
    private PlayModeRepository mPlayModeRepository;
    private PlayerViewModel mViewModel;
    private Runnable mRunnable;
    private Handler mHandler = new Handler();
    private int mPosition;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) iBinder;
            mService = binder.getService();
            mBinded = true;
            if (mService != null) {
                mMediaListener = mService.getListener();
                mService.setUiListener(PlayerActivity.this);
                onUpdateUiPlay(mService.getCurrentTrack());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBinded = false;
        }
    };

    public static Intent getPlayerIntent(Context context) {
        Intent intent = new Intent(context, PlayerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_player);
        mViewModel = ViewModelProviders.of(PlayerActivity.this)
                .get(PlayerViewModel.class);
        mPlayModeRepository = PlayModeRepository.getInstance(
                PlayModeLocalDataSource.getInstance(
                        this.getApplication().getApplicationContext()));
        mViewModel.setPlayModeRepository(mPlayModeRepository);
        mBinding.executePendingBindings();
        initPlayMode();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
        connectService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBinded) {
            this.unbindService(mConnection);
            mBinded = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void updateStateButton(boolean isPlaying) {
        mViewModel.setPlayState(isPlaying);
    }

    @Override
    public void onUpdateUiPlay(Track track) {
        mBinding.setTrack(track);
        onUpdateSeekbar();
        updateStateButton(mService.isPlaying());
    }

    @Override
    public void onUpdateSeekbar() {
        mViewModel.setMaxSeekBar(mService.getDuration() / CONVERT_MINISECOND);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mService != null) {
                    mPosition = mService.getCurrentPosition() / CONVERT_MINISECOND;
                    mViewModel.onSeekBarPositionChange(mPosition);
                }
                mHandler.postDelayed(mRunnable, CONVERT_MINISECOND);
            }
        };
        mHandler.postDelayed(mRunnable, CONVERT_MINISECOND);
    }

    @Override
    public void onShuffleStateChange(boolean isShuffle) {
        if (isShuffle) {
            Toast.makeText(PlayerActivity.this, getString(R.string.msg_on_shuffer),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PlayerActivity.this, getString(R.string.msg_off_shuffer),
                    Toast.LENGTH_SHORT).show();
        }
        mViewModel.setShuffle(!mPlayMode.isShuffle());
    }

    @Override
    public void onLoopStateChange(int type) {
        mViewModel.setLoopType(type);
        mPlayMode.setLoopMode(type);
        mViewModel.savePlayerMode(mPlayMode);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int position, boolean b) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mPosition = seekBar.getProgress();
        mViewModel.onSeekBarPositionChange(mPosition);
        mMediaListener.seekTo(mPosition * CONVERT_MINISECOND);
    }

    public void onPlayClick() {
        mMediaListener.play();
    }

    public void onNextClick() {
        mMediaListener.next();
    }

    public void onPreviousClick() {
        mMediaListener.previous();
    }

    public void onFavoriteClick() {
    }

    public void onShufferClick() {
        mPlayMode = mViewModel.getPlayerMode();
        mService.onShuffleChange(!mPlayMode.isShuffle());
        mPlayMode.setShuffle(!mPlayMode.isShuffle());
        mViewModel.savePlayerMode(mPlayMode);
    }

    public void onLoopClick() {
        mPlayMode = mViewModel.getPlayerMode();
        switch (mPlayMode.getLoopMode()) {
            case LoopType.NO_LOOP:
                mService.onLoopStateChange(LoopType.LOOP_ALL);
                break;

            case LoopType.LOOP_ONE:
                mService.onLoopStateChange(LoopType.NO_LOOP);
                break;

            case LoopType.LOOP_ALL:
                mService.onLoopStateChange(LoopType.LOOP_ONE);
                break;
        }
    }

    public void connectService() {
        Intent intent = new Intent(PlayerActivity.this, MusicService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    void initPlayMode() {
        mPlayMode = mViewModel.getPlayerMode();
        switch (mPlayMode.getLoopMode()) {
            case LoopType.NO_LOOP:
                mViewModel.setLoopType(LoopType.NO_LOOP);
                break;

            case LoopType.LOOP_ONE:
                mViewModel.setLoopType(LoopType.LOOP_ONE);
                break;

            case LoopType.LOOP_ALL:
                mViewModel.setLoopType(LoopType.LOOP_ALL);
                break;
        }
        if (mPlayMode.isShuffle()) {
            mViewModel.setShuffle(true);
        } else {
            mViewModel.setShuffle(false);
        }
    }

    private void initListener() {
        HandlerClick handlerClick = new HandlerClick(PlayerActivity.this);
        mBinding.setListener(this);
        mBinding.setViewModel(mViewModel);
        mBinding.setHandler(handlerClick);
        mBinding.seekBar.setOnSeekBarChangeListener(this);
    }
}
