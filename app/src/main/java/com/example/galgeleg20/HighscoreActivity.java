package com.example.galgeleg20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HighscoreActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    ListView listView;
    Button button;
    ArrayList<String> stringArrayList;
    ArrayAdapter<String> stringArrayAdapter;
    Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        //prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs = getSharedPreferences("highscores",MODE_PRIVATE);

        editor = prefs.edit();

        set = new HashSet<>();

        Set<String> fetch = prefs.getStringSet("highscore", null);
        List<String> list;

        if (fetch != null) {
            list = new ArrayList<>(fetch);
        }
        else {
            list = new ArrayList<>();
        }


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.highscore_title);

        // Arrow Click
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.listView);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        stringArrayList = new ArrayList<>();

        stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_listview, stringArrayList);
        listView.setAdapter(stringArrayAdapter);

        if (getIntent().getExtras().getBoolean("activity")) {
            // lav færdig
            stringArrayList.add(getIntent().getExtras().getString("result") + " med " +
            getIntent().getExtras().getInt("score") + " forsøg" + "\n" +
            "Ordet var: " + getIntent().getExtras().getString("word"));
        }

        if (fetch != null) {
            stringArrayList.addAll(list);
        }
        stringArrayAdapter.notifyDataSetChanged();

        set.addAll(stringArrayList);

        editor.putStringSet("highscore",set).apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        stringArrayList.clear();
        stringArrayAdapter.notifyDataSetChanged();
        set.clear();
        editor.putStringSet("highscore",set).apply();
    }
}
