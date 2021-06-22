package com.example.sosmeioambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextSenha;
    private Button buttonEntrar;
    private TextView textViewCadastro, textViewAnonimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        textViewCadastro = findViewById(R.id.textViewCadastro);
        textViewAnonimo = findViewById(R.id.textViewAnonimo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ControleSessao controleSessao = new ControleSessao(MainActivity.this);
        int idUsuario = controleSessao.pegaSessao();
        if (idUsuario != -1)
            navegacaoPrincipal();
    }

    private void navegacaoPrincipal() {
        Intent intent2 = new Intent(MainActivity.this, ActivityPrincipal.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }

    public void navegacao(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityCadastro.class);
        startActivity(intent);
    }

    public void entrar(View view) {
        if ((!editTextEmail.getText().toString().equals("")) && (!editTextSenha.getText().toString().equals(""))){
            Usuario u = new Usuario();
            u.setEmail(editTextEmail.getText().toString());
            u.setSenha(editTextSenha.getText().toString());

            if (true){
                ControleSessao controleSessao = new ControleSessao(MainActivity.this);
                controleSessao.salvaSessao(u);
                navegacaoPrincipal();
            } else {
                Toast.makeText(MainActivity.this, "Email e/ou Senha não Encontrados.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "Email e Senha são obrigatórios.", Toast.LENGTH_SHORT).show();
        }
    }
}