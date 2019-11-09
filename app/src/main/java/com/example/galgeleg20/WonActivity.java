package com.example.galgeleg20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WonActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        tv = findViewById(R.id.tv);
        imageView = findViewById(R.id.iv);
        button =  findViewById(R.id.button);

        tv.setText("Antal forsøg: " + getIntent().getExtras().getInt("tries"));

        imageView.getLayoutParams().width = 500;
        imageView.getLayoutParams().height = 500;
        imageView.setAdjustViewBounds(true);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
