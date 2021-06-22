package com.example.sosmeioambiente;

import java.io.Serializable;

public class Denuncia implements Serializable {
    private int id;
    private String tipo, endereco, descricao;
    private Coordenada coordenada;

    public Denuncia() {
    }

    public Denuncia(int id, String tipo, String endereco, String descricao, Coordenada coordenada) {
        this.id = id;
        this.tipo = tipo;
        this.endereco = endereco;
        this.descricao = descricao;
        this.coordenada = coordenada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", endereco='" + endereco + '\'' +
                ", descricao='" + descricao + '\'' +
                ", coordenada=" + coordenada +
                '}';
    }
}
