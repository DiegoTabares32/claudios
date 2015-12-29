package com.ccapps.claudios.MediaPlayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.ccapps.claudios.R;

import java.io.IOException;

/**
 * Created by dtabares on 22/12/15.
 */
public class MediaPlayerService implements AudioManager.OnAudioFocusChangeListener {

    private final AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private static MediaPlayerService INSTANCE;
    private Context context;

    private MediaPlayerService(Context context){
        super();
        this.mediaPlayer = new MediaPlayer();
        this.context = context;
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public static MediaPlayerService getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new MediaPlayerService(context);
        }

        return INSTANCE;
    }

    public void play(int soundResource) {
        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(context, soundResource);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Log.v("MENSAJE", "ahora pone start() con el sonido ok");
        mediaPlayer.start();
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    public void destroy(){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
