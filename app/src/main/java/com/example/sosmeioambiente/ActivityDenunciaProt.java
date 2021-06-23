package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityDenunciaProt extends AppCompatActivity {
    TextView textViewProtocolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia_prot);

        textViewProtocolo = findViewById(R.id.textViewProtocolo);
        Intent intent1 = getIntent();
        textViewProtocolo.setText(intent1.getStringExtra("protocolo"));
    }

    public void navegacaoHome(View view) {
        Intent intent = new Intent(ActivityDenunciaProt.this, ActivityPrincipal.class);
        startActivity(intent);
    }
}