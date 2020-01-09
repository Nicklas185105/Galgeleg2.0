package com.example.galgeleg20;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LostActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button button;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        tv = findViewById(R.id.tv);
        button =  findViewById(R.id.button);

        tv.setText("Ordet var: " + getIntent().getExtras().getString("word"));
        mp = MediaPlayer.create(this, R.raw.lost_sound);
        mp.start();

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            Intent i = new Intent(this, HighscoreActivity.class);
            i.putExtra("activity", true);
            i.putExtra("result", "tabte");
            i.putExtra("score",getIntent().getExtras().getInt("tries"));
            i.putExtra("word", getIntent().getExtras().getString("word"));
            startActivity(i);
            mp.stop();
        }
    }
}
