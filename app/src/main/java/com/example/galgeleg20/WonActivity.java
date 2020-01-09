package com.example.galgeleg20;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WonActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button button;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        tv = findViewById(R.id.tv);
        button =  findViewById(R.id.button);

        tv.setText("Antal forsøg: " + getIntent().getExtras().getInt("tries"));
        mp = MediaPlayer.create(this, R.raw.won_sound);
        mp.start();

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            Intent i = new Intent(this, HighscoreActivity.class);
            i.putExtra("activity", true);
            i.putExtra("result", "vundet");
            i.putExtra("score", getIntent().getExtras().getInt("tries"));
            i.putExtra("word", getIntent().getExtras().getString("word"));
            startActivity(i);
            mp.stop();

        }
    }
}
