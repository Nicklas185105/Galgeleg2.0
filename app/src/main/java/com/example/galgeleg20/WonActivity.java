package com.example.galgeleg20;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WonActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        imageView = findViewById(R.id.iv);

        imageView.getLayoutParams().width = 500;
        imageView.getLayoutParams().height = 500;
        imageView.setAdjustViewBounds(true);
    }
}
