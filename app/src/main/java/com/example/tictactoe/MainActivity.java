package com.example.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView langs_lv;
    private Button AI_btn;
    private Button friend_btn;
    private ImageView language_btn;
    String[] lang_names={"English","Arabic","中文","French","Dutch","Spanish"};
    int[] lang_flags={R.drawable.united_kingdom,R.drawable.egypt,R.drawable.china,R.drawable.france,R.drawable.flag__1_,R.drawable.flag};
    String[] lang_abbr={"en","ar","zh","fr","nl","es"};
    ImageView setting_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        LoadLocale();
        setContentView(R.layout.activity_main);
        language_btn=findViewById(R.id.language_tv);
        AI_btn=findViewById(R.id.ai_btn_main);
        friend_btn=findViewById(R.id.friend_btn_main);
        setting_btn=findViewById(R.id.setting_img);
        if(AudioPlay.mp!=null){
            AudioPlay.stopAudio();
        }
        AudioPlay.playAudio(this,R.raw.bgsound2);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });


        AI_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(AISidesSelectActivity.class);
            }
        });

        friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(friend_sides_select.class);
            }
        });

        language_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();


            }
        });



    }

    private void showDialog(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.activity_language_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().gravity= Gravity.NO_GRAVITY;
        langs_lv=dialog.findViewById(R.id.Languages_listview);
        CustomBaseAdapter customAdapter=new CustomBaseAdapter(this,lang_names,lang_flags);
        langs_lv.setAdapter(customAdapter);
        langs_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                setLocale(lang_abbr[i]);
                recreate();


            }
        });

        dialog.show();
    }



    private void setLocale(String lang){

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        } else {
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }
    public void LoadLocale(){
        SharedPreferences prefs=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=prefs.getString("My_Lang","");
        setLocale(language);


    }


    private void navigate(Class activity){
        Intent i=new Intent(MainActivity.this,activity);
        startActivity(i);

    }
}