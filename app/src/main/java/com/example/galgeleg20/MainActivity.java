package com.example.galgeleg20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener {

    Toolbar toolbar;
    Button game, highscore, help;
    ProgressBar progressBar;
    Spinner dropdown;

    String difficulty;

    static MainActivity mainActivity;
    static AsyncTaskUdskifteligAktivitet asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.home_screen);

        // Find View By Id
        game = findViewById(R.id.game);
        dropdown = findViewById(R.id.dropdown);
        highscore = findViewById(R.id.highscore);
        help = findViewById(R.id.help);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(99);

        String[] items = new String[]{"Sværhedsgrad", "Nem", "Mellem", "Svær"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        difficulty = "0";

        // On Click Listener
        game.setOnClickListener(this);
        highscore.setOnClickListener(this);
        help.setOnClickListener(this);

        mainActivity = this;

        /*Toast.makeText(getApplicationContext(), "Henter ord fra Regneark", Toast.LENGTH_SHORT).show();
        asyncTask = new AsyncTaskUdskifteligAktivitet(20, 50);
        asyncTask.execute(20, 50);*/
        game.setEnabled(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        if (!difficulty.equals("0")) {
            game.setEnabled(false);
            dropdown.setEnabled(false);
        }
        if (parent.getItemAtPosition(pos).equals("Nem")) {
            difficulty = "1";
        }
        else if (parent.getItemAtPosition(pos).equals("Mellem")) {
            difficulty = "12";
        }
        else if (parent.getItemAtPosition(pos).equals("Svær")) {
            difficulty = "123";
        }
        if (!difficulty.equals("0")) {
            Toast.makeText(getApplicationContext(), "Henter ord fra Regneark", Toast.LENGTH_SHORT).show();
            asyncTask = new AsyncTaskUdskifteligAktivitet(20, 50);
            asyncTask.execute(20, 50);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        mainActivity = null; // Vigtigt, ellers bliver aktivitetsinstansen hængende i hukommelsen
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == game){
            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);
        }
        else if (v == highscore){
            Intent i = new Intent(this, HighscoreActivity.class);
            i.putExtra("activity", false);
            startActivity(i);
        }
        else if (v == help){
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }
    }

    /**
     * en AsyncTask hvor input er int, progress er double, resultat er String
     */
    static class AsyncTaskUdskifteligAktivitet extends AsyncTask {
        int antalSkridt;
        int ventetidPrSkridtIMilisekunder;
        double procent;
        double resttidISekunder;

        AsyncTaskUdskifteligAktivitet(int antalSkridt, int ventetidPrSkridtIMilisekunder){
            this.antalSkridt = antalSkridt;
            this.ventetidPrSkridtIMilisekunder = ventetidPrSkridtIMilisekunder;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            final Galgelogik logik = ((Galgelogik) mainActivity.getApplication());
            try {
                logik.hentOrdFraRegneark(mainActivity.difficulty);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            for (int i = 0; i < antalSkridt; i++) {
                SystemClock.sleep(ventetidPrSkridtIMilisekunder);
                procent = i * 100.0 / antalSkridt;
                resttidISekunder = (antalSkridt - i) * ventetidPrSkridtIMilisekunder / 100 / 10.0;
                publishProgress(procent, resttidISekunder); // sendes som parameter til onProgressUpdate()
            }
            return "Hentet " + logik.muligeOrd.size() + " ord"; // resultat (String) sendes til onPostExecute()
        }

        @Override
        protected void onProgressUpdate(Object... progress) {
            if (mainActivity == null) return;
            Log.d("AsyncTask", "arbejder - " + procent + "% færdig, mangler " + resttidISekunder + " sekunder endnu");
            mainActivity.progressBar.setProgress((int) procent);
        }

        @Override
        protected void onPostExecute(Object resultat) {
            if (mainActivity == null) return;
            Toast.makeText(mainActivity, resultat.toString(), Toast.LENGTH_SHORT).show();
            asyncTask = null;
            mainActivity.game.setEnabled(true);
            mainActivity.dropdown.setEnabled(true);
            mainActivity.progressBar.setProgress(100);
        }

        @Override
        protected void onCancelled() {
            if (mainActivity == null) return;
            asyncTask = null;
        }
    }
}
