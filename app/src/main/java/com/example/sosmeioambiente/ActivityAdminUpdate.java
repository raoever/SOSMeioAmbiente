package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class ActivityAdminUpdate extends AppCompatActivity {
    private Denuncia d;
    private TextView textViewDados;
    private EditText editTextUpdate;
    private ImageView imageViewTela;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        textViewDados = findViewById(R.id.textViewDados);
        editTextUpdate = findViewById(R.id.editTextUpdate);
        imageViewTela = findViewById(R.id.imageViewTela);
        d = (Denuncia) getIntent().getSerializableExtra("dado");
        if (d != null){
            textViewDados.setText(d.toString());
            if (d.getImagem_denuncia() != null){
                byte[] saidaImagem = d.getImagem_denuncia();
                ByteArrayInputStream imagemStream = new ByteArrayInputStream(saidaImagem);
                Bitmap imagemBitmap = BitmapFactory.decodeStream(imagemStream);
                imageViewTela.setImageBitmap(imagemBitmap);
            }
        }
    }

    public void cancela(View view) {
        Intent intent = new Intent(ActivityAdminUpdate.this, ActivityAdmin.class);
        startActivity(intent);
    }

    public void update(View view) {
        String update = d.getAcompanhamento();
        if (update == null)
            update = "";
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