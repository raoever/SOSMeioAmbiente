package com.example.sosmeioambiente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityComentario extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spinner2;
    private EditText editTextRegistro;
    private ListView listViewRegistros;
    private ArrayAdapter<Comentario> adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();

        editTextRegistro = findViewById(R.id.editTextRegistro);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.comentario, android.R.layout.simple_list_item_1);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterSpinner);
        spinner2.setOnItemSelectedListener(this);

        listViewRegistros = findViewById(R.id.listViewRegistros);
        ArrayList<Comentario> dados = (ArrayList<Comentario>) db.comentarioDao().getAll();
        adapter = new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1, dados);
        listViewRegistros.setAdapter(adapter);
        listViewRegistros.setOnItemClickListener(this);

    }

    public void registrar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityComentario.this);
        builder.setTitle("Confirma Registro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Comentario c = new Comentario();
                        c.setTipo(spinner2.getSelectedItem().toString());
                        c.setRegistro(editTextRegistro.getText().toString());
                        db.comentarioDao().insertAll(c);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void home(View view) {
        Intent intent = new Intent(ActivityComentario.this, ActivityPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}