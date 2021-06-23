package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCadastro extends AppCompatActivity {
    private EditText editTextNomeCompleto, editTextEmailCadastro, editTextSenhaCadastro, editTextSenha2;
    private Button buttonCadastrar;
    private Usuario u;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNomeCompleto = findViewById(R.id.editTextNomeCompleto);
        editTextEmailCadastro = findViewById(R.id.editTextEmailCadastro);
        editTextSenhaCadastro = findViewById(R.id.editTextTextSenhaCadastro);
        editTextSenha2 = findViewById(R.id.editTextSenha2);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbSosMeioAmbiente").allowMainThreadQueries().build();
    }

    public void navegacaoCad(View view) {
        String nomeCompleto = editTextNomeCompleto.getText().toString();
        String emailCadastro = editTextEmailCadastro.getText().toString();
        String senha1 = editTextSenhaCadastro.getText().toString();
        String senha2 = editTextSenha2.getText().toString();

        if (!(nomeCompleto.equals("")) && (!emailCadastro.equals("")) && (!senha1.equals("")) && (!senha2.equals(""))){
            if (senha1.equals(senha2)){
                if (db.usuarioDao().findByEmail(emailCadastro) == null){
                    u = new Usuario();
                    u.setNome(nomeCompleto);
                    u.setEmail(emailCadastro);
                    u.setSenha(senha1);
                    db.usuarioDao().insertAll(u);
                    Toast.makeText(ActivityCadastro.this, "Usuário Salvo.", Toast.LENGTH_SHORT).show();
                    navegaPrincipal();
                } else {
                    Toast.makeText(ActivityCadastro.this, "Email já cadastrado. Entrar em contato com a Administração.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ActivityCadastro.this, "Senhas não são iguais.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActivityCadastro.this, "Todos os dados são obrigatórios.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navegaPrincipal() {
        Intent intent3 = new Intent(ActivityCadastro.this, MainActivity.class);
        startActivity(intent3);
    }
}