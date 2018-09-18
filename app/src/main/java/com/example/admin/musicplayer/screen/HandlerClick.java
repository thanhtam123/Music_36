package com.example.admin.musicplayer.screen;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.Track;

/**
 * Created by TamTT on 9/15/2018.
 */

public class HandlerClick {
    private Context mContext;

    public HandlerClick(Context context) {
        mContext = context;
    }

    public void onButtonMoreClicked(Genre genre) {
        /*Intent intent = GenreActivity.getGenreIntent(mContext, genre);
        mContext.startActivity(intent);*/
    }

    public void onButtonDownloadClick(Track track) {
        Toast.makeText(mContext, track.getDownloadUrl(), Toast.LENGTH_SHORT).show();
    }
}
