package net.shybaieva.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    TextView bookDetailTV;
    Button stopButton, playButton;
    Intent data;
    boolean isPlayed = false;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        data = getIntent();
        final String title = data.getStringExtra("title");
        bookDetailTV.setText(title);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBook();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlayed==true){
                    player.pause();
                }
            }
        });
    }



    private void init (){
        bookDetailTV = findViewById(R.id.bookDetailTV);
        playButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);
    }

    public void playBook() {
        player = new MediaPlayer();
        if (isPlayed == false){
            try {
                player.setDataSource("https://firebasestorage.googleapis.com/v0/b/audioplayer-acf52.appspot.com/o/English_fairy_tale%2FTom_Tit_Tot%2Fenglish_fairy_tales_01_64kb.mp3?alt=media&token=7ff234f6-95a9-4a4c-9821-75780372b24f");
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                player.prepare();
                isPlayed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //TODO Resume sound
        }
    }


}
