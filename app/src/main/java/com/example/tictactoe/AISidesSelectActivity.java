package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AISidesSelectActivity extends AppCompatActivity {

     static final String PLAYER1_NAME_KEY="player1_key";
     static final String PLAYER2_NAME_KEY="player2_key";
    RadioGroup radioGroup;
    ImageView imageX;
    ImageView imageY;
    // -1 for unset ,0 for x ,1 for O
    int playerSide=-1;
    Button continue_btn;
    String player1Name;
    String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ai_sides_select);
        radioGroup=findViewById(R.id.RadioGroupXO);
        imageX=findViewById(R.id.xImage);
        imageY=findViewById(R.id.yImage);
        imageX.setImageAlpha(76);
        imageY.setImageAlpha(76);

        continue_btn=findViewById(R.id.cont_btn_main);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navigate(GameActivity_AI.class);
            }
        });




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                playerSide=1;
                if(i==R.id.radioButtonForX){
                    playerSide=0;
                    imageY.setImageAlpha(76);
                    imageX.setImageAlpha(255);

                }
                else{

                    imageY.setImageAlpha(255);
                    imageX.setImageAlpha(76);
                }

            }
        });



    }

    private void navigate(Class activity){


        player1Name="You";
        player2Name="AI";
        Intent i=new Intent(AISidesSelectActivity.this,activity);
        i.putExtra(PLAYER1_NAME_KEY,player1Name);
        i.putExtra(PLAYER2_NAME_KEY,player2Name);
        startActivity(i);

    }
}