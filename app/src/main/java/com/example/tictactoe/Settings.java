package com.example.tictactoe;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {
    Switch sw;
    ImageView musicIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        sw=findViewById(R.id.setting_tg);
        musicIcon=findViewById(R.id.musicIcon);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){
                    AudioPlay.pauseAudio();
                    musicIcon.setImageResource(R.drawable.music2);
                }else{
                    if(!AudioPlay.mp.isPlaying()){
                        AudioPlay.resumeAudio();
                        musicIcon.setImageResource(R.drawable.music);
                    }
                }
            }
        });
    }
}