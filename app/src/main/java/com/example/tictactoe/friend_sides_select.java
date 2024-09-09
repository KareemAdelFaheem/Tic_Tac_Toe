package com.example.tictactoe;

import static com.example.tictactoe.AISidesSelectActivity.PLAYER1_NAME_KEY;
import static com.example.tictactoe.AISidesSelectActivity.PLAYER2_NAME_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class friend_sides_select extends AppCompatActivity {

    RadioGroup radioGroup;
    ImageView imageX;
    ImageView imageY;
    private EditText player1_edittext;
    private EditText player2_edittext;

    Button cont_btn;
    // -1 for unset ,0 for x ,1 for O
    int playerSide=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friend_sides_select);
        radioGroup=findViewById(R.id.RadioGroupXOfriend);
        imageX=findViewById(R.id.friendxImage);
        imageY=findViewById(R.id.friendyImage);
        player1_edittext=findViewById(R.id.et1_main);
        player2_edittext=findViewById(R.id.et2_main);
        cont_btn=findViewById(R.id.friendcont_btn_main);


        imageX.setImageAlpha(76);
        imageY.setImageAlpha(76);

        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1Name=player1_edittext.getText().toString();

                String player2Name=player2_edittext.getText().toString();

                navigate(GameActivity.class,player1Name,player2Name);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                playerSide=1;
                if(i==R.id.radioButtonForXfriend){
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

    private void navigate(Class activity,String name1,String name2){

        Intent i=new Intent(friend_sides_select.this,activity);
        i.putExtra(PLAYER1_NAME_KEY,name1);
        i.putExtra(PLAYER2_NAME_KEY,name2);
        startActivity(i);

    }
}