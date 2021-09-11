package com.example.gameox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    Button btnPlay;
    TextView player1,player2;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        addNotification();
        mediaPlayer = MediaPlayer.create(this,R.raw.notification_tone);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);


        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                String p1 = player1.getText().toString();
                String p2 = player2.getText().toString();


                Intent i = new Intent(MainActivity.this, MainActivity2.class);

                if(!p1.equals("")&&!p2.equals("")) {


                    i.putExtra("player1", p1);
                    i.putExtra("player2", p2);

                }else if(p1.equals("")&&!p2.equals("")) {


                    i.putExtra("player1", "player1");
                    i.putExtra("player2", p2);

                }else if(!p1.equals("")&&p2.equals("")) {


                    i.putExtra("player1", p1);
                    i.putExtra("player2", "player2");

                }
                else
                {
                    i.putExtra("player1","player1" );
                    i.putExtra("player2", "player2");
                }
                startActivity(i);

            }
        });


    }


    public void addNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_baseline_games_24);
        builder.setContentTitle("New Notificacton ");
        builder.setContentText("This is a test notification");


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

  }
