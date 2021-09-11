package com.example.gameox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    ImageView imgZero, imgOne, imgTwo, imgThree, imgFour, imgFive, imgSix, imgSeven, imgEight;
    int activePlayer = 0;
    TextView player1,winnerName,p1NameScore,p2NameScore,p1Score,p2Score;
    Button Reset, ok,score,resetScore;
    int[] gamestate = {5, 10, 15, 20, 25, 35, 40, 45, 60};
    float avg;
    int a = 0 ,b = 0;
    Dialog dialog;
    MediaPlayer mediaPlayer;
  String p1,p2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        Intent i = getIntent();
        p1 = i.getStringExtra("player1");
        p2 = i.getStringExtra("player2");
        mediaPlayer = MediaPlayer.create(MainActivity2.this,R.raw.tone);


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        player1 = findViewById(R.id.p1);

        imgZero = findViewById(R.id.zero);
        imgOne = findViewById(R.id.one);
        imgTwo = findViewById(R.id.two);
        imgThree = findViewById(R.id.three);
        imgFour = findViewById(R.id.four);
        imgFive = findViewById(R.id.five);
        imgSix = findViewById(R.id.six);
        imgSeven = findViewById(R.id.seven);
        imgEight = findViewById(R.id.eight);
        winnerName = dialog.findViewById(R.id.win);
        Reset = findViewById(R.id.btnReset);
        score = findViewById(R.id.btnScore);
        ok = dialog.findViewById(R.id.buttonok);
        p1NameScore = dialog.findViewById(R.id.p1NameScore);
        p2NameScore = dialog.findViewById(R.id.p2NameScore);
        p1Score = dialog.findViewById(R.id.p1Score);
        p2Score = dialog.findViewById(R.id.p2Score);
        resetScore = dialog.findViewById(R.id.scoreReset);
        player1.setText(p1+" Turn");

        resetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 0;
                b = 0;
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        imgEight.setOnClickListener(this);
        imgSeven.setOnClickListener(this);
        imgSix.setOnClickListener(this);
        imgFive.setOnClickListener(this);
        imgFour.setOnClickListener(this);
        imgThree.setOnClickListener(this);
        imgTwo.setOnClickListener(this);
        imgOne.setOnClickListener(this);
        imgZero.setOnClickListener(this);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgZero.setImageResource(0);
                imgOne.setImageResource(0);
                imgTwo.setImageResource(0);
                imgThree.setImageResource(0);
                imgFour.setImageResource(0);
                imgFive.setImageResource(0);
                imgSix.setImageResource(0);
                imgSeven.setImageResource(0);
                imgEight.setImageResource(0);
                gamestate[0] = 5;
                gamestate[1] = 10;
                gamestate[2] = 15;
                gamestate[3] = 20;
                gamestate[4] = 25;
                gamestate[5] = 30;
                gamestate[6] = 35;
                gamestate[7] = 40;
                gamestate[8] = 50;


                player1.setText(p1+" Turn");
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                dialog.show();
                String score1 = String.valueOf(a);
                String score2 = String.valueOf(b);

                winnerName.setText("");
                p2NameScore.setText(p2);
                p1NameScore.setText(p1);
                p1Score.setText(score1);
                p2Score.setText(score2);

            }
        });


    }

    @Override
    public void onClick(View view) {
        mediaPlayer.start();
        ImageView img = (ImageView) view;
        img.setTranslationY(-1000f);
        avg = (gamestate[0] + gamestate[1] + gamestate[2] + gamestate[3] + gamestate[4] + gamestate[5] + gamestate[6] + gamestate[7] + gamestate[8]) / 9;
        int tapedImage = Integer.parseInt(img.getTag().toString());

        if (gamestate[tapedImage] >= 2) {

            if (activePlayer == 0) {

                player1.setText(p2+" Turn");


                img.setImageResource(R.drawable.finalo);
                gamestate[tapedImage] = activePlayer;

                activePlayer = 1;
            } else {

                player1.setText(p1+" Turn");
                img.setImageResource(R.drawable.finalx);
                gamestate[tapedImage] = activePlayer;

                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        } else
            Toast.makeText(this, "choose another location", Toast.LENGTH_SHORT).show();

        if (gamestate[0] == gamestate[1] && gamestate[1] == gamestate[2] || gamestate[3] == gamestate[4] && gamestate[4] == gamestate[5] || gamestate[6] == gamestate[7] && gamestate[7] == gamestate[8]
                || gamestate[0] == gamestate[3] && gamestate[3] == gamestate[6] || gamestate[1] == gamestate[4] && gamestate[4] == gamestate[7] || gamestate[2] == gamestate[5] && gamestate[8] == gamestate[2]
                || gamestate[0] == gamestate[4] && gamestate[4] == gamestate[8] || gamestate[2] == gamestate[4] && gamestate[4] == gamestate[6])
                   {

                                  if (activePlayer == 0)
                                  {  b++;
                                      Toast.makeText(this, "winner x", Toast.LENGTH_SHORT).show();
                                      winnerName.setText(p2);
                                      p2NameScore.setText(p2+" score = ");
                                      p1NameScore.setText(p1+" score = ");
                                      p1Score.setText(""+a);
                                      p2Score.setText(""+b);
                                      dialog.show();
                                  }
                                  else
                                      {
                                          a++;
                                      Toast.makeText(this, "winner o", Toast.LENGTH_SHORT).show();
                                          winnerName.setText(p1);
                                          p2NameScore.setText(p2+" score = ");
                                          p1NameScore.setText(p1+" score = ");
                                          p1Score.setText(""+a);
                                          p2Score.setText(""+b);
                                      dialog.show();
                                  }
                                  avg = 125;
                                  if(a>b){
                                      winnerName.setText(p1);

                                  }else if(a<b)
                                  {
                                      winnerName.setText(p2);
                                  }
                                  else{
                                      winnerName.setText("Draw game");
                                  }

                 } else if (avg < 2)
                 Toast.makeText(this, "draw", Toast.LENGTH_SHORT).show();


       }
}