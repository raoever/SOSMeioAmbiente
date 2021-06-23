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
    private List<String> listaDados;
    private ArrayAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comentario, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listViewDenuncias = findViewById(R.id.listViewDenuncias);
        preencherLista();
        preencherAdapter();
        listViewDenuncias.setOnItemClickListener(this);
    }

    private void preencherAdapter() {
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaDados);
        listViewDenuncias.setAdapter(adapter);
    }

    private void preencherLista() {
        listaDados = new ArrayList<>();
        listaDados = pegaDados();
    }

    private List<String> pegaDados() {
        List<String> denunciasTexto = new ArrayList<>();
        List<Denuncia> denuncias;
        denuncias = db.denunciaDao().getAll();
        for (Denuncia denuncia :
                denuncias) {
            denunciasTexto.add(denuncia.toString());
        }
        return denunciasTexto;
    }

    public void irHome(View view) {
        Intent intent = new Intent(ActivityAdmin.this, ActivityPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);
//        abrirTela(item);
    }
}