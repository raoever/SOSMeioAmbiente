package com.example.sosmeioambiente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPrincipal extends AppCompatActivity {
    private TextView textViewBemVindo;
    private ImageView imageViewAdmin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SOS Meio Ambiente");
        textViewBemVindo = findViewById(R.id.textViewBemVindo);
        imageViewAdmin = findViewById(R.id.imageViewAdmin);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();

        ControleSessao controleSessao = new ControleSessao(ActivityPrincipal.this);
        int idUsuario = controleSessao.pegaSessao();
        if (idUsuario == 1)
            imageViewAdmin.setVisibility(View.VISIBLE);
        if (idUsuario != -1){
            if (db.usuarioDao().findById(idUsuario) != null){
                Usuario byId = db.usuarioDao().findById(idUsuario);
                textViewBemVindo.setText("Bem Vindo "+byId.getNome());
            }
        } else {
//            toolbar.getMenu().findItem(R.id.minhasDenuncias).setVisible(false);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.minhasDenuncias:
                Intent intent5 = new Intent(ActivityPrincipal.this, ActivityMinhasDenuncias.class);
                startActivity(intent5);
                break;

            case R.id.sair:
                removeSessao();
                finishAffinity();
                System.exit(0);
                return true;

            default:

        }
        return super.onOptionsItemSelected(item);
    }

    public void NavegarDen(View view) {
        Intent intent = new Intent(ActivityPrincipal.this, ActivityDenuncia.class);
        startActivity(intent);
    }

    public void navegacaoCom(View view) {
        Intent intent = new Intent(ActivityPrincipal.this, ActivityComentario.class);
        startActivity(intent);
    }

    public void navegacaoAcom(View view) {
        Intent intent = new Intent(ActivityPrincipal.this, ActivityProtAcomp.class);
        startActivity(intent);
    }

    public void sair(View view) {
        removeSessao();
        finishAffinity();
        System.exit(0);
    }

    private void removeSessao() {
        ControleSessao controleSessao = new ControleSessao(ActivityPrincipal.this);
        controleSessao.removeSessao();
    }

    public void admin(View view) {
        Intent intent2 = new Intent(ActivityPrincipal.this, ActivityAdmin.class);
        startActivity(intent2);
    }
}