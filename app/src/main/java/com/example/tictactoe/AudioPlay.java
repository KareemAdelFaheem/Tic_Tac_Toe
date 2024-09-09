package com.example.tictactoe;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlay {

    public static MediaPlayer mp;
    public static void playAudio(Context c, int id){

        mp=MediaPlayer.create(c,id);
        mp.setVolume(0.3f,0.3f);
        if(!mp.isPlaying()){
            mp.start();
        }
    }
    public static void resumeAudio(){
        mp.start();
    }
    public static void pauseAudio(){
        mp.pause();
    }



    public static void stopAudio(){
        mp.stop();
        mp.release();

    }
}
