package com.example.admin.musicplayer.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_LIST_TRACK,
                genre.getListTrackFromGenre(genre));
        bundle.putString(Constants.EXTRA_NAME_GENRE, genre.getGenre());
        Intent intent = Genre.getGenreIntent(mContext, genre);
        intent.putExtra(Constants.EXTRA_BUNDLE, bundle);
        mContext.startActivity(intent);
    }

    public void onButtonDownloadClick(Track track) {
        Toast.makeText(mContext, track.getDownloadUrl(), Toast.LENGTH_SHORT).show();
    }
}
