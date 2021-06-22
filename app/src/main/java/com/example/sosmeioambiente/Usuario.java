package com.example.sosmeioambiente;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "senha")
    private String senha;


    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha, List<Denuncia> denuncias) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
//        this.denuncias = denuncias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public List<Denuncia> getDenuncias() {
//        return denuncias;
//    }
//
//    public void setDenuncias(List<Denuncia> denuncias) {
//        this.denuncias = denuncias;
//    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
//                ", denuncias=" + denuncias +
                '}';
    }
}
