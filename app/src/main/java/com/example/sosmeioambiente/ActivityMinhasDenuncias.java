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
    private List<String> listaDados;
    private ArrayAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_denuncias);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comentario, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listViewMinhasDenuncias = findViewById(R.id.listViewMinhasDenuncais);
        preencherLista();
        preencherAdapter();
        listViewMinhasDenuncias.setOnItemClickListener(this);

    }

    private void preencherAdapter() {
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaDados);
        listViewMinhasDenuncias.setAdapter(adapter);
    }

    private void preencherLista() {
        listaDados = new ArrayList<>();
        listaDados = pegaDados();
    }

    private List<String> pegaDados() {
        List<String> denunciasTexto = new ArrayList<>();
        List<Denuncia> denuncias = new ArrayList<>();
        ControleSessao controleSessao = new ControleSessao(ActivityMinhasDenuncias.this);
        int idUsuario = controleSessao.pegaSessao();
        denuncias = db.denunciaDao().findByIdUsuario(idUsuario);
        for (Denuncia denuncia :
                denuncias) {
            denunciasTexto.add(denuncia.toString());
        }
        return denunciasTexto;
    }

    public void irHome(View view) {
        Intent intent = new Intent(ActivityMinhasDenuncias.this, ActivityPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}