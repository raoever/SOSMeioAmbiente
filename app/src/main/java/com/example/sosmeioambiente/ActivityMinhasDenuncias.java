package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityMinhasDenuncias extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listViewMinhasDenuncias;
    private ArrayAdapter<Denuncia> adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_denuncias);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        listViewMinhasDenuncias = findViewById(R.id.listViewMinhasDenuncais);
        ControleSessao controleSessao = new ControleSessao(ActivityMinhasDenuncias.this);
        int idUsuario = controleSessao.pegaSessao();
        ArrayList<Denuncia> dados = (ArrayList<Denuncia>) db.denunciaDao().findByIdUsuario(idUsuario);
        adapter = new ArrayAdapter<Denuncia>(this, android.R.layout.simple_list_item_1, dados);
        listViewMinhasDenuncias.setAdapter(adapter);
        listViewMinhasDenuncias.setOnItemClickListener(this);
    }

    public void irHome(View view) {
        Intent intent = new Intent(ActivityMinhasDenuncias.this, ActivityPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}