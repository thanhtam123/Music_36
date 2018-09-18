package com.example.admin.musicplayer.screen;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.musicplayer.Constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by TamTT on 9/17/2018.
 */

public class BindingUltils {

    public static String splitName(String text) {
        String name = text.split(Constants.COLON)[2].replace(Constants.HYPHEN,Constants.SPACE);
        return name;
    }

    @SuppressLint("DefaultLocale")
    public static String convertMilisecToMinute(long milisec) {
        return String.format(Constants.FORMAT_MIMUTES, TimeUnit.MILLISECONDS.toMinutes(milisec),
                TimeUnit.MILLISECONDS.toSeconds(milisec) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(milisec)));
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .fitCenter()
                .into(view);
    }
}
