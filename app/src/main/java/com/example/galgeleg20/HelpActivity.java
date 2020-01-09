package com.example.galgeleg20;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button back, forward;
    ImageView imageView;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.help_title);

        back = findViewById(R.id.back);
        forward = findViewById(R.id.forward);

        imageView = findViewById(R.id.imageView);

        back.setOnClickListener(this);
        forward.setOnClickListener(this);

        // Arrow Click
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Help1Fragment()).commit();
            imageView.setImageResource(R.drawable.help1);
            n = 1;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == back){
            if (n == 2){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help1Fragment()).commit();
                imageView.setImageResource(R.drawable.help1);
                n--;
            }
            else if (n == 3){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help2Fragment()).commit();
                imageView.setImageResource(R.drawable.help2);
                n--;
            }
            else if (n == 4){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help3Fragment()).commit();
                imageView.setImageResource(R.drawable.help3);
                n--;
            }
            else if (n == 5){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help4Fragment()).commit();
                imageView.setImageResource(R.drawable.help4);
                n--;
            }
        }
        else if (v == forward){
            if (n == 1){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help2Fragment()).commit();
                imageView.setImageResource(R.drawable.help2);
                n++;
            }
            else if (n == 2){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help3Fragment()).commit();
                imageView.setImageResource(R.drawable.help3);
                n++;
            }
            else if (n == 3){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help4Fragment()).commit();
                imageView.setImageResource(R.drawable.help4);
                n++;
            }
            else if (n == 4){
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.fragment_container, new Help5Fragment()).commit();
                imageView.setImageResource(R.drawable.help5);
                n++;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
