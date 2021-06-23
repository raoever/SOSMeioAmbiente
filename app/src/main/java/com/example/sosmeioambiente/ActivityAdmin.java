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
import java.util.List;

public class ActivityAdmin extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listViewDenuncias;
    private ArrayAdapter<Denuncia> adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        listViewDenuncias = findViewById(R.id.listViewDenuncias);
        ArrayList<Denuncia> dados = (ArrayList<Denuncia>) db.denunciaDao().getAll();
        adapter = new ArrayAdapter<Denuncia>(this, android.R.layout.simple_list_item_1, dados);
        listViewDenuncias.setAdapter(adapter);
        listViewDenuncias.setOnItemClickListener(this);
    }

    public void irHome(View view) {
        Intent intent = new Intent(ActivityAdmin.this, ActivityPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Denuncia denuncia = (Denuncia) parent.getItemAtPosition(position);
        Intent it = new Intent(ActivityAdmin.this, ActivityAdminUpdate.class);
        it.putExtra("dado", denuncia);
        startActivity(it);
    }
}