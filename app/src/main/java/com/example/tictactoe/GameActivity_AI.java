package com.example.tictactoe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity_AI extends AppCompatActivity {
    static final String RESULT_PLAYER_NAME = "player1won";

    ImageView one1, one2, one3, two1, two2, two3, three1, three2, three3;
    TextView player1_Name_tv, player2_Name_tv;
    Button new_game_btn;
    MaterialCardView X_player_Card_view, O_player_Card_view;
    private int[] placedPositions;
    private final List<int[]> winPositions = new ArrayList<>();
    // false for X and true for O
    private int playerTurn;
    private int imageAlpha;
    String player1_Name, player2_Name;
    MediaPlayer mp_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        AudioPlay.stopAudio();
        // Initialize Views
        one1 = findViewById(R.id.imageone1);
        one2 = findViewById(R.id.imageone2);
        one3 = findViewById(R.id.imageone3);
        two1 = findViewById(R.id.imagetwo1);
        two2 = findViewById(R.id.imagetwo2);
        two3 = findViewById(R.id.imagetwo3);
        three1 = findViewById(R.id.imagethree1);
        three2 = findViewById(R.id.imagethree2);
        three3 = findViewById(R.id.imagethree3);
        player1_Name_tv = findViewById(R.id.player1_tv_game);
        player2_Name_tv = findViewById(R.id.player2_tv_game);
        new_game_btn = findViewById(R.id.new_game_button);
        X_player_Card_view = findViewById(R.id.X_playerCard);
        O_player_Card_view = findViewById(R.id.O_playerCard);

        // Initialize MediaPlayer


        // Set initial stroke widths
        X_player_Card_view.setStrokeWidth(10);
        O_player_Card_view.setStrokeWidth(0);

        // Define winning positions
        winPositions.add(new int[]{0, 1, 2});
        winPositions.add(new int[]{3, 4, 5});
        winPositions.add(new int[]{6, 7, 8});
        winPositions.add(new int[]{0, 3, 6});
        winPositions.add(new int[]{2, 5, 8});
        winPositions.add(new int[]{1, 4, 7});
        winPositions.add(new int[]{0, 4, 8});
        winPositions.add(new int[]{2, 4, 6});

        // Initialize game state
        placedPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = -1;

        // Get player names from Intent
        Intent intent = getIntent();
        player1_Name = intent.getStringExtra(AISidesSelectActivity.PLAYER1_NAME_KEY);
        player2_Name = intent.getStringExtra(AISidesSelectActivity.PLAYER2_NAME_KEY);
        player1_Name_tv.setText(player1_Name);
        player2_Name_tv.setText(player2_Name);

        // Set new game button click listener
        new_game_btn.setOnClickListener(view -> newGame());

        // Set click listeners for each cell
        setCellClickListener(one1, 0);
        setCellClickListener(one2, 1);
        setCellClickListener(one3, 2);
        setCellClickListener(two1, 3);
        setCellClickListener(two2, 4);
        setCellClickListener(two3, 5);
        setCellClickListener(three1, 6);
        setCellClickListener(three2, 7);
        setCellClickListener(three3, 8);
    }

    private void setCellClickListener(ImageView imageView, int position) {
        imageView.setOnClickListener(view -> {
            if (isSelectable(position) && !stopGame()) {
                if (playerTurn == -1) {
                    fillBox(imageView, playerTurn);
                    fillPosition(position, -1);
                    if (checkWin() != 0) {

                        showdialog(checkWin()); // Show dialog if player wins
                    } else if (!stopGame()) {
                        playerTurn = 1; // Switch to AI's turn
                        Handler handler = new Handler();
                        handler.postDelayed(this::makeAIMove, 500);
                    }
                }
            }
        });
    }

    public void fillBox(ImageView image, int playerTurn) {
        if (playerTurn == -1) {
            image.setImageResource(R.drawable.xshadow_img);
            X_player_Card_view.setStrokeWidth(0);
            O_player_Card_view.setStrokeWidth(10);
        } else {
            image.setImageResource(R.drawable.oshadow_img);
            X_player_Card_view.setStrokeWidth(10);
            O_player_Card_view.setStrokeWidth(0);
        }
    }

    public void fillPosition(int imageIndex, int turn) {
        placedPositions[imageIndex] = turn;
    }

    public int checkWin() {
        for (int[] winLine : winPositions) {
            if (placedPositions[winLine[0]] == playerTurn &&
                    placedPositions[winLine[1]] == playerTurn &&
                    placedPositions[winLine[2]] == playerTurn) {
                return playerTurn;
            }
        }
        return 0; // No winner yet
    }

    private void updateUIForWin() {
        one1.setImageAlpha(imageAlpha);
        one2.setImageAlpha(imageAlpha);
        one3.setImageAlpha(imageAlpha);
        two1.setImageAlpha(imageAlpha);
        two2.setImageAlpha(imageAlpha);
        two3.setImageAlpha(imageAlpha);
        three1.setImageAlpha(imageAlpha);
        three2.setImageAlpha(imageAlpha);
        three3.setImageAlpha(imageAlpha);

        X_player_Card_view.setStrokeWidth(0);
        O_player_Card_view.setStrokeWidth(0);
    }

    private void makeAIMove() {
        Random rand = new Random();
        int randNum;
        do {
            randNum = rand.nextInt(9);
        } while (placedPositions[randNum] != 0);

        ImageView selectedImage = getImageViewForPosition(randNum);
        selectedImage.setImageResource(R.drawable.oshadow_img);
        fillPosition(randNum, 1);

        if (checkWin() == 1) {
            updateUIForWin();
            showdialog(1); // Show dialog if AI wins
        } else if (stopGame()) {
            showdialog(-1); // Show dialog if the game is a draw
        } else {
            playerTurn = -1; // Switch back to player's turn
        }
    }

    private ImageView getImageViewForPosition(int position) {
        switch (position) {
            case 0: return one1;
            case 1: return one2;
            case 2: return one3;
            case 3: return two1;
            case 4: return two2;
            case 5: return two3;
            case 6: return three1;
            case 7: return three2;
            case 8: return three3;
            default: throw new IllegalArgumentException("Invalid position");
        }
    }

    private void showdialog(int winner) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_resultdialog);
        TextView tv_main = dialog.findViewById(R.id.resulttext);
        mp_game = MediaPlayer.create(this, R.raw.win);
        mp_game.start();
        if (winner == 0) {
            tv_main.setText("It's a draw!");
        } else {
            tv_main.setText("Congratulations! " + (winner == -1 ? player1_Name : player2_Name) + " Won!");
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.80);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.30);
        dialog.getWindow().setLayout(width, height);

        Button share_btn = dialog.findViewById(R.id.share_btn_main);
        share_btn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "I just won an intense game of Tic-Tac-Toe! 🎉 " +
                    "Think you can beat me? Download the game now and let's find out");
            startActivity(Intent.createChooser(intent, "Share Via"));
        });
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        dialog.setOnDismissListener(dialogInterface -> {
            if (mp_game != null) {
                mp_game.stop();
                mp_game.release();
                mp_game = null;
            }
        });
        dialog.show();
    }

    private void newGame() {
        placedPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = -1;

        one1.setImageResource(0);
        one2.setImageResource(0);
        one3.setImageResource(0);
        two1.setImageResource(0);
        two2.setImageResource(0);
        two3.setImageResource(0);
        three1.setImageResource(0);
        three2.setImageResource(0);
        three3.setImageResource(0);
        imageAlpha = 255;
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

    private boolean isSelectable(int position) {
        return placedPositions[position] == 0;
    }

    private boolean stopGame() {
        for (int i : placedPositions) {
            if (i == 0) return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp_game != null) {
            mp_game.release();
            mp_game = null;
        }
    }
}