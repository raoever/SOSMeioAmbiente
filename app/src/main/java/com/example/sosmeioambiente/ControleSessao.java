package com.example.sosmeioambiente;

import android.content.Context;
import android.content.SharedPreferences;

public class ControleSessao {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NOME = "sessao";
    String SESSAO_CHAVE = "sessao_usuario";

    public ControleSessao(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NOME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void salvaSessao (Usuario usuario){
        int id = usuario.getId();
        editor.putInt(SESSAO_CHAVE, id).commit();
    }

    public int pegaSessao (){
        return sharedPreferences.getInt(SESSAO_CHAVE, -1);
    }

    public void removeSessao(){
        editor.putInt(SESSAO_CHAVE, -1).commit();
    }

}


