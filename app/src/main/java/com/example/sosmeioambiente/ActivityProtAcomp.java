package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityProtAcomp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prot_acomp);
    }

    public void navegacaoProt(View view) {
        Intent intent = new Intent(ActivityProtAcomp.this, ActivityAcompanha.class);
        startActivity(intent);
    }
}