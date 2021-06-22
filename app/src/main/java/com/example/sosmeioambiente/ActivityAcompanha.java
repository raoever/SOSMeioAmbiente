package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityAcompanha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanha);
    }

    public void navegacaoHome(View view) {
        Intent intent = new Intent(ActivityAcompanha.this, ActivityPrincipal.class);
        startActivity(intent);
    }
}