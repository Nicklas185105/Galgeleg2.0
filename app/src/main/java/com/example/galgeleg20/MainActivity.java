package com.example.galgeleg20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Toolbar toolbar;
    Button game, highscore, help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.home_screen);

        // Find View By Id
        game = findViewById(R.id.game);
        highscore = findViewById(R.id.highscore);
        help = findViewById(R.id.help);

        // On Click Listener
        game.setOnClickListener(this);
        highscore.setOnClickListener(this);
        help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == game){
            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);
        }
        else if (v == highscore){
            Intent i = new Intent(this, HighscoreActivity.class);
            startActivity(i);
        }
        else if (v == help){
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }
    }
}
