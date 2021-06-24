package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAdminUpdate extends AppCompatActivity {
    private Denuncia d;
    private TextView textViewDados;
    private EditText editTextUpdate;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        textViewDados = findViewById(R.id.textViewDados);
        editTextUpdate = findViewById(R.id.editTextUpdate);
        d = (Denuncia) getIntent().getSerializableExtra("dado");
        if (d != null){
            textViewDados.setText(d.toString());
        }
    }

    public void cancela(View view) {
        Intent intent = new Intent(ActivityAdminUpdate.this, ActivityAdmin.class);
        startActivity(intent);
    }

    public void update(View view) {
        String update = d.getAcompanhamento();
        update += editTextUpdate.getText().toString() + "\n";
        d.setAcompanhamento(update);
        db.denunciaDao().update(d);
        Intent intent = new Intent(ActivityAdminUpdate.this, ActivityAdmin.class);
        startActivity(intent);
    }

    public void deleta(View view) {
        db.denunciaDao().delete(d);
        Intent intent = new Intent(ActivityAdminUpdate.this, ActivityAdmin.class);
        startActivity(intent);
    }
}