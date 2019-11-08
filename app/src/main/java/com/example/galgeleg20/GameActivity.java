package com.example.galgeleg20;

import android.os.Bundle;
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

    Galgelogik logik = new Galgelogik();
    Toolbar toolbar;
    ImageView iv;
    TextView info,guessed;
    Button guess;
    EditText et;

    List<Integer> image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        logik.nulstil();
        try {
            logik.hentOrdFraRegneark("12");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            info.setText("Du har vundet");
        }
        if (logik.erSpilletTabt()){
            info.setText("Du har tabt, ordet var: " + logik.getOrdet());
        }
    }

    private void updatePicture(){
        if (logik.getAntalForkerteBogstaver() < 7) {
            iv.setImageResource(image.get(logik.getAntalForkerteBogstaver()));
        }
    }
}
