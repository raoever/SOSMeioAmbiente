package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ActivityAcompanha extends AppCompatActivity {
    private TextView textViewProtocolado, textViewStatus;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanha);

        Intent intent = getIntent();
        String protocoloDigitado = intent.getStringExtra("protocoloDigitado");
        textViewProtocolado = findViewById(R.id.textViewProtocolado);
        textViewProtocolado.setText(protocoloDigitado);
        textViewStatus = findViewById(R.id.textViewStatus);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        Denuncia byProtocolo = db.denunciaDao().findByProtocolo(protocoloDigitado);
        Log.i("entradaProtocolo: ", byProtocolo.toString());
        if (byProtocolo != null)
            textViewStatus.setText(byProtocolo.toString());
    }

    public void navegacaoHome(View view) {
        Intent intent = new Intent(ActivityAcompanha.this, ActivityPrincipal.class);
        startActivity(intent);
    }
}