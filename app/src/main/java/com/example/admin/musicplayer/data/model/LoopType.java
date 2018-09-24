package com.example.admin.musicplayer.data.model;

import android.support.annotation.IntDef;

import static com.example.admin.musicplayer.data.model.LoopType.LOOP_ALL;
import static com.example.admin.musicplayer.data.model.LoopType.LOOP_ONE;
import static com.example.admin.musicplayer.data.model.LoopType.NO_LOOP;


/**
 * Created by TamTT on 9/23/2018.
 */
@IntDef({LOOP_ONE, NO_LOOP, LOOP_ALL})
public @interface LoopType {
    int NO_LOOP = 0;
    int LOOP_ONE = 1;
    int LOOP_ALL = 2;
}
