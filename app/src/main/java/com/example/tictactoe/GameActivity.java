package com.example.tictactoe;

import static com.example.tictactoe.AISidesSelectActivity.PLAYER1_NAME_KEY;
import static com.example.tictactoe.AISidesSelectActivity.PLAYER2_NAME_KEY;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    static final String RESULT_PLAYER_NAME="player1won";

    ImageView one1;
    ImageView one2;
    ImageView one3;
    ImageView two1;
    ImageView two2;
    ImageView two3;
    ImageView three1;
    ImageView three2;
    ImageView three3;
    TextView player1_Name_tv;
    TextView player2_Name_tv;
    int playerwin;
    Button new_game_btn;
    MaterialCardView X_player_Card_view;
    MaterialCardView O_player_Card_view;
    private int[] placedPositions;
    private final List<int[]> winPositions=new ArrayList<>();
    // false for X and true for O
    private int playerTurn;
    private int imageAlpha;
    String player1_Name;
    String player2_Name;
    MediaPlayer mp_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        one1=findViewById(R.id.imageone1);
        one2=findViewById(R.id.imageone2);
        one3=findViewById(R.id.imageone3);
        two1=findViewById(R.id.imagetwo1);
        two2=findViewById(R.id.imagetwo2);
        two3=findViewById(R.id.imagetwo3);
        three1=findViewById(R.id.imagethree1);
        three2=findViewById(R.id.imagethree2);
        three3=findViewById(R.id.imagethree3);
        player1_Name_tv=findViewById(R.id.player1_tv_game);
        player2_Name_tv=findViewById(R.id.player2_tv_game);
        AudioPlay.stopAudio();
        mp_game=MediaPlayer.create(this,R.raw.move);

        new_game_btn=findViewById(R.id.new_game_button);
        X_player_Card_view=findViewById(R.id.X_playerCard);
        O_player_Card_view=findViewById(R.id.O_playerCard);
        X_player_Card_view.setStrokeWidth(10);
        O_player_Card_view.setStrokeWidth(0);


        winPositions.add(new int[]{0,1,2});
        winPositions.add(new int[]{3,4,5});
        winPositions.add(new int[]{6,7,8});
        winPositions.add(new int[]{0,3,6});
        winPositions.add(new int[]{2,5,8});
        winPositions.add(new int[]{1,4,7});
        winPositions.add(new int[]{0,4,8});
        winPositions.add(new int[]{2,4,6});
        placedPositions=new int[]{0,0,0,0,0,0,0,0,0};
        //-1 for x and 1 for o
        playerTurn=-1;




         Intent intent=getIntent();
         player1_Name=intent.getStringExtra(PLAYER1_NAME_KEY);
         player2_Name=intent.getStringExtra(PLAYER2_NAME_KEY);


        player1_Name_tv.setText(player1_Name);
        player2_Name_tv.setText(player2_Name);




        new_game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });



        one1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(0)&&!stopGame())
                {
                    fillBox((ImageView) view,playerTurn);
                    playerTurn=(playerTurn==-1)? 1:-1;
                    fillPosition(0);
                    checkWin();

                }

            }
        });
        one2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(1)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(1);
                checkWin();}
            }
        });
        one3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(2)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(2);
                checkWin();}
            }
        });
        two1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(3)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(3);
                checkWin();}
            }
        });
        two2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(4)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(4);
                checkWin();}
            }
        });
        two3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(5)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(5);
                checkWin();}
            }
        });
        three1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(6)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(6);
                checkWin();}
            }
        });
        three2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(7)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(7);
                checkWin();}
            }
        });
        three3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectable(8)&&!stopGame())
                {
                fillBox((ImageView) view,playerTurn);
                playerTurn=(playerTurn==-1)? 1:-1;
                fillPosition(8);
                checkWin();}
            }
        });
    }

    public void fillBox(ImageView image,int playerTurn){


            if(playerTurn==-1)
            {
                image.setImageResource(R.drawable.xshadow_img);
                X_player_Card_view.setStrokeWidth(0);
                O_player_Card_view.setStrokeWidth(10);

            }
            else {

                image.setImageResource(R.drawable.oshadow_img);
                X_player_Card_view.setStrokeWidth(10);
                O_player_Card_view.setStrokeWidth(0);
            }

    }

    public void fillPosition(int imageIndex){
        placedPositions[imageIndex]=playerTurn;
    }

    public boolean checkWin(){
        boolean result=false;

        for(int i=0;i<winPositions.size();i++){
            final int[] winLine= winPositions.get(i);
            if(placedPositions[winLine[0]]==playerTurn&&placedPositions[winLine[1]]==playerTurn
                    &&placedPositions[winLine[2]]==playerTurn){
                result=true;
                playerwin=playerTurn;
                imageAlpha=100;
                one1.setImageAlpha(imageAlpha);
                one2.setImageAlpha(imageAlpha);
                one3.setImageAlpha(imageAlpha);
                two1.setImageAlpha(imageAlpha);
                two2.setImageAlpha(imageAlpha);
                two3.setImageAlpha(imageAlpha);
                three1.setImageAlpha(imageAlpha);
                three2.setImageAlpha(imageAlpha);
                three3.setImageAlpha(imageAlpha);
                mp_game=MediaPlayer.create(this,R.raw.win2);
                mp_game.setVolume(0.5f,0.5f);
                mp_game.start();
                X_player_Card_view.setStrokeWidth(0);
                O_player_Card_view.setStrokeWidth(0);
                showDialog();

            }
        }

        return result;
    }


    private void showDialog(){

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.activity_resultdialog);
        TextView tv_main=dialog.findViewById(R.id.resulttext);
        tv_main.setText("Congratulations! "+getWinner()+" Won!");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.80);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.30);
        dialog.getWindow().setLayout(width,height);

        Button share_btn=dialog.findViewById(R.id.share_btn_main);
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"I just won an intense game of Tic-Tac-Toe! \uD83C\uDF89 " +
                        "Think you can beat me? Download the game now and let's find out");
                startActivity(Intent.createChooser(intent,"Share Via"));
            }
        });
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mp_game.stop();
                mp_game.release();
                mp_game=null;
            }
        });
        dialog.show();


    }
    public String getWinner(){
        String winner;

        if(playerwin==1)
        {
            winner=player1_Name;

        }else{winner=player2_Name;}

        return winner;
    }

    private void navigate(Class activity,int playerwin){

        Intent i=new Intent(GameActivity.this,activity);
        if(playerwin==-1)
        {
            i.putExtra(RESULT_PLAYER_NAME,player1_Name);

        }else{i.putExtra(RESULT_PLAYER_NAME,player2_Name);}
        startActivity(i);

    }

    public boolean stopGame(){
        boolean result=false;
        if(checkWin()){result=true;}
        return result;
    }
    public boolean isSelectable(int position){
        boolean isSelectable=false;
        if(placedPositions[position]==0){
            isSelectable=true;
        }
        return isSelectable;
    }
    public void newGame(){
        placedPositions=new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=-1;
        one1.setImageResource(0);
        one2.setImageResource(0);
        one3.setImageResource(0);
        two1.setImageResource(0);
        two2.setImageResource(0);
        two3.setImageResource(0);
        three1.setImageResource(0);
        three2.setImageResource(0);
        three3.setImageResource(0);
        imageAlpha=255;
        one1.setImageAlpha(imageAlpha);
        one2.setImageAlpha(imageAlpha);
        one3.setImageAlpha(imageAlpha);
        two1.setImageAlpha(imageAlpha);
        two2.setImageAlpha(imageAlpha);
        two3.setImageAlpha(imageAlpha);
        three1.setImageAlpha(imageAlpha);
       three2.setImageAlpha(imageAlpha);
        three3.setImageAlpha(imageAlpha);
        X_player_Card_view.setStrokeWidth(10);
        O_player_Card_view.setStrokeWidth(0);

    }
}