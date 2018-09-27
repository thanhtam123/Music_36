package com.example.admin.musicplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.NotificationTarget;
import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.LoopType;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.screen.player.MediaListener;
import com.example.admin.musicplayer.screen.player.OnUpdateUiListener;
import com.example.admin.musicplayer.screen.player.PlayerActivity;

import java.util.ArrayList;

import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_CLEAR;
import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_NEXT;
import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_PAUSE;
import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_PREVIOUS;

/**
 * Created by TamTT on 9/20/2018.
 */

public class MusicService extends Service implements MediaListener {
    private static final String ACTION_NEXT_TRACK = "ACTION_NEXT_TRACK";
    private static final String START_FOREGROUND_SERVICE = "START_FOREGROUND_SERVICE";
    private static final String ACTION_PREVIOUS_TRACK = "ACTION_PREVIOUS_TRACK";
    private static final String ACTION_CHANGE_STATE = "ACTION_CHANGE_STATE";
    private static final String ACTION_MEDIA_CLEAR = "ACTION_MEDIA_CLEAR";
    private static final int ID_NOTIFICATION = 112;
    private static final String CHANNEL_ID_NOTIFY = "CHANNEL_ID_NOTIFY";
    private static String EXTRA_POSITION = "extra_position";
    private static String EXTRA_LIST_TRACK = "extra_list_track";
    private final IBinder mIBinder = new MusicBinder();
    private int mPosition;
    private ArrayList<Track> mTracks;
    private RemoteViews mRemoteViews;
    private NotificationTarget mNotificationTarget;
    private Notification mNotification;
    private SongManager mSongManager;

    public static Intent getMusicServiceIntent(Context context, int position, ArrayList<Track> tracks) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putExtra(EXTRA_POSITION, position);
        intent.putParcelableArrayListExtra(EXTRA_LIST_TRACK, tracks);
        intent.setAction(START_FOREGROUND_SERVICE);
        return intent;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.mIBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case START_FOREGROUND_SERVICE:
                    getInforTrack(intent);
                    mSongManager = new SongManager(getApplicationContext(),
                            mPosition, mTracks);
                    setData();
                    break;
                case ACTION_NEXT_TRACK:
                    next();
                    break;
                case ACTION_PREVIOUS_TRACK:
                    previous();
                    break;
                case ACTION_CHANGE_STATE:
                    play();
                    break;
                case ACTION_MEDIA_CLEAR:
                    mSongManager.destroyMediaPlayer();
                    stopSelf(startId);
                    stopForeground(true);
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void setData() {
        mSongManager.setData();
        Track currentTrack = mTracks.get(mPosition);
        createNotification(currentTrack.getTitle(), currentTrack.getPublisherMetadata().getArtist(),
                currentTrack.getArtworkUrl());
    }

    @Override
    public void play() {
        mSongManager.play();
        updateNotificationState();
    }

    @Override
    public boolean isPlaying() {
        return mSongManager.isPlaying();
    }

    @Override
    public void next() {
        mSongManager.next();
        updateNotificationChangeTrack(mSongManager.getCurrentTrack().getArtworkUrl());
    }

    @Override
    public void previous() {
        mSongManager.previous();
        updateNotificationChangeTrack(mSongManager.getCurrentTrack().getArtworkUrl());
    }

    @Override
    public void onShuffleChange(boolean isShuffle) {
        if(isShuffle){
            mSongManager.shuffer();
        }else {
            mSongManager.unShuffle();
        }
    }

    @Override
    public void onLoopStateChange(@LoopType int type) {
        switch (type) {
            case LoopType.NO_LOOP:
                mSongManager.loopOff();
                break;
            case LoopType.LOOP_ONE:
                mSongManager.loopOne();
                break;
            case LoopType.LOOP_ALL:
                mSongManager.loopAll();
                break;
        }
    }

    @Override
    public void seekTo(int position) {
        mSongManager.seekTo(position);
    }

    @Override
    public int getCurrentPosition() {
        return mSongManager.getCurrentPosition();
    }

    @Override
    public Track getCurrentTrack() {
        return mTracks.get(mPosition);
    }

    @Override
    public int getDuration() {
        return mSongManager.getDuration();
    }

    public MediaListener getListener() {
        return this;
    }

    public void setUiListener(OnUpdateUiListener listener) {
        mSongManager.setUiListener(listener);
    }

    private void getInforTrack(Intent intent) {
        if (intent == null) {
            return;
        }
        mPosition = intent.getIntExtra(EXTRA_POSITION, 0);
        mTracks = intent.getParcelableArrayListExtra(EXTRA_LIST_TRACK);
        mTracks.get(mPosition);
    }

    private void createNotification(String title, String singer, String url) {
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        setDataForNotification(title, singer);
        createIntentNotify();
        buildNotify(url);
    }

    private void createIntentNotify() {
        createIntent(R.id.imageview_notify_next, ACTION_NEXT_TRACK, REQUEST_CODE_NEXT);
        createIntent(R.id.imageview_notify_previous, ACTION_PREVIOUS_TRACK,
                REQUEST_CODE_PREVIOUS);
        createIntent(R.id.imageview_notify_pause, ACTION_CHANGE_STATE, REQUEST_CODE_PAUSE);
        createIntent(R.id.imageview_notify_clear, ACTION_MEDIA_CLEAR, REQUEST_CODE_CLEAR);
    }

    private void createIntent(int id, String action, int requestCode) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClass(getApplicationContext(), MusicService.class);
        PendingIntent pendingIntent =
                PendingIntent.getService(getApplicationContext(), requestCode, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(id, pendingIntent);
    }

    private void buildNotify(String url) {
        Intent intent = PlayerActivity.getPlayerIntent(getApplicationContext());
        PendingIntent pendingIntent =
                PendingIntent.getActivities(this, (int) System.currentTimeMillis(),
                        new Intent[] { intent }, 0);
        Notification.Builder notificationBuilder =
                new Notification.Builder(getApplicationContext());
        mNotification = notificationBuilder.setSmallIcon(R.drawable.ic_music)
                .setContentIntent(pendingIntent)
                .setContent(mRemoteViews)
                .setDefaults(Notification.FLAG_NO_CLEAR)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            buildChannel(notificationBuilder);
        }
        mNotificationTarget = new NotificationTarget(
                getApplicationContext(),
                mRemoteViews,
                R.id.imageview_notify_avatar,
                mNotification,
                ID_NOTIFICATION);
        Glide.with(getApplicationContext())
                .load(url)
                .asBitmap()
                .into(mNotificationTarget);
        startForeground(ID_NOTIFICATION, mNotification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void buildChannel(Notification.Builder notificationBuilder) {
        int importance = NotificationManager.IMPORTANCE_HIGH;
        CharSequence name = getString(R.string.app_name);
        NotificationChannel mChannel =
                new NotificationChannel(CHANNEL_ID_NOTIFY, name, importance);
        mNotification = notificationBuilder.setSmallIcon(R.drawable.ic_music)
                .setChannelId(CHANNEL_ID_NOTIFY)
                .build();
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);
        mNotificationManager.notify(ID_NOTIFICATION, mNotification);
    }

    private void setDataForNotification(String title, String singer) {
        mRemoteViews.setTextViewText(R.id.textview_notify_name, title);
        mRemoteViews.setTextViewText(R.id.textview_notify_singer, singer);
        mRemoteViews.setImageViewResource(R.id.imageview_notify_clear, R.mipmap.ic_delete_white);
        mRemoteViews.setImageViewResource(R.id.imageview_notify_next, R.mipmap.ic_next);
        mRemoteViews.setImageViewResource(R.id.imageview_notify_pause, R.mipmap.ic_pause);
        mRemoteViews.setImageViewResource(R.id.imageview_notify_previous, R.mipmap.ic_previous);
    }

    private void updateNotificationChangeTrack(String url) {
        Track track = mSongManager.getCurrentTrack();
        mRemoteViews.setTextViewText(R.id.textview_notify_name, track.getTitle());
        mRemoteViews.setTextViewText(R.id.textview_notify_singer,
                track.getPublisherMetadata().getArtist());
        mNotificationTarget = new NotificationTarget(
                getApplicationContext(),
                mRemoteViews,
                R.id.imageview_notify_avatar,
                mNotification,
                ID_NOTIFICATION);
        Glide.with(getApplicationContext())
                .load(url)
                .asBitmap()
                .into(mNotificationTarget);
        startForeground(ID_NOTIFICATION, mNotification);
    }

    private void updateNotificationState() {
        if (mSongManager.isPlaying()) {
            mRemoteViews.setImageViewResource(R.id.imageview_notify_pause, R.mipmap.ic_pause);
        } else {
            mRemoteViews.setImageViewResource(R.id.imageview_notify_pause, R.mipmap.ic_play);
        }
        startForeground(ID_NOTIFICATION, mNotification);
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
