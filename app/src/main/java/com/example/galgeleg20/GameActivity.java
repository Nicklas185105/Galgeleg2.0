package com.example.galgeleg20;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //Galgelogik logik = new Galgelogik();
    Galgelogik logik;
    Toolbar toolbar;
    ImageView iv;
    TextView info,guessed;
    Button guess;
    EditText et;

    int antalForsøg;

    List<Integer> image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        antalForsøg = 0;
        logik = ((Galgelogik) this.getApplication());
        logik.nulstil();
        /*new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    logik.hentOrdFraRegneark("12");
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }.execute();*/

        System.out.println(logik.muligeOrd.size());
        Log.e("Antal mulige ord","" + logik.muligeOrd.size());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.game_title);

        iv = findViewById(R.id.galge);
        info = findViewById(R.id.ord);
        guessed = findViewById(R.id.ordGættet);
        guess = findViewById(R.id.GuessButton);
        et = findViewById(R.id.editText);

        guess.setOnClickListener(this);
        logik.logStatus();

        image.add(R.drawable.galge);
        image.add(R.drawable.forkert1);
        image.add(R.drawable.forkert2);
        image.add(R.drawable.forkert3);
        image.add(R.drawable.forkert4);
        image.add(R.drawable.forkert5);
        image.add(R.drawable.forkert6);

        opdaterSkærm();

        // Arrow Click
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        String bogstav = et.getText().toString();
        if (bogstav.length() != 1){
            et.setError("Skriv et bogstav");
            et.setText(null);
            return;
        }
        logik.gætBogstav(bogstav);
        et.setText(null);
        et.setError(null);

        opdaterSkærm();
    }

    private void opdaterSkærm(){
        info.setText("Gæt ordet: " + logik.getSynligtOrd());
        guessed.setText(logik.getAntalForkerteBogstaver() + ": " + logik.getBrugteBogstaver());
        updatePicture();

        if (logik.erSpilletVundet()){
            Intent i = new Intent(this, WonActivity.class);
            i.putExtra("tries", antalForsøg);
            i.putExtra("word", logik.getOrdet());
            startActivity(i);
        }
        if (logik.erSpilletTabt()){
            Intent i = new Intent(this, LostActivity.class);
            i.putExtra("tries", antalForsøg);
            i.putExtra("word", logik.getOrdet());
            startActivity(i);
        }
        antalForsøg++;
    }

    private void updatePicture(){
        if (logik.getAntalForkerteBogstaver() < 7) {
            iv.setImageResource(image.get(logik.getAntalForkerteBogstaver()));
        }
    }
}
