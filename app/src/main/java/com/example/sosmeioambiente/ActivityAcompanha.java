package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class ActivityAcompanha extends AppCompatActivity {
    private TextView textViewProtocolado, textViewStatus;
    private ImageView ivAcompanhamento;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanha);

        Intent intent = getIntent();
        String protocoloDigitado = intent.getStringExtra("protocoloDigitado");
        textViewProtocolado = findViewById(R.id.textViewProtocolado);
        textViewProtocolado.setText(protocoloDigitado);
        textViewStatus = findViewById(R.id.textViewStatus);
        ivAcompanhamento = findViewById(R.id.ivAcompamento);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
        Denuncia byProtocolo = db.denunciaDao().findByProtocolo(protocoloDigitado);
        if (byProtocolo != null){
            textViewStatus.setText(byProtocolo.toString());
            if (byProtocolo.getImagem_denuncia() != null){
                byte[] saidaImagem = byProtocolo.getImagem_denuncia();
                ByteArrayInputStream imagemStream = new ByteArrayInputStream(saidaImagem);
                Bitmap imagemBitmap = BitmapFactory.decodeStream(imagemStream);
                ivAcompanhamento.setImageBitmap(imagemBitmap);
            }
        }
    }

    public void navegacaoHome(View view) {
        Intent intent = new Intent(ActivityAcompanha.this, ActivityPrincipal.class);
        startActivity(intent);
    }
}