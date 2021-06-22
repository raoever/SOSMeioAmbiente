package com.example.sosmeioambiente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityComentario extends AppCompatActivity {
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comentario, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
    }

    public void registrar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityComentario.this);
        builder.setTitle("Confirma Registro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Registro Salvo.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityComentario.this, ActivityPrincipal.class);
                        startActivity(intent);
                    }
                })
                .setNeutralButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ação Cancelada.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}