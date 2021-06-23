package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityProtAcomp extends AppCompatActivity {
    EditText editTextProtocoloDigitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prot_acomp);

        editTextProtocoloDigitado = findViewById(R.id.editTextProtocoloDigitado);
    }

    public void navegacaoProt(View view) {
        Intent intent = new Intent(ActivityProtAcomp.this, ActivityAcompanha.class);
        intent.putExtra("protocoloDigitado", editTextProtocoloDigitado.getText().toString());
        startActivity(intent);
    }
}