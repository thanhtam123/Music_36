package com.example.admin.musicplayer.screen.player;

import android.support.annotation.IntDef;

import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_CLEAR;
import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_NEXT;
import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_PAUSE;
import static com.example.admin.musicplayer.screen.player.NotificationType.REQUEST_CODE_PREVIOUS;

/**
 * Created by TamTT on 9/22/2018.
 */
@IntDef({REQUEST_CODE_NEXT, REQUEST_CODE_PAUSE, REQUEST_CODE_PREVIOUS, REQUEST_CODE_CLEAR
})
public @interface NotificationType {
    int REQUEST_CODE_NEXT = 0;
    int REQUEST_CODE_PAUSE = 1;
    int REQUEST_CODE_PREVIOUS = 2;
    int REQUEST_CODE_CLEAR = 3;
}
