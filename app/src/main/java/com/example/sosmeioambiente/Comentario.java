package com.example.sosmeioambiente;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Comentario {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "tipo")
    private String tipo;
    @ColumnInfo(name = "registro")
    private String registro;

    public Comentario() {
    }

    public Comentario(int id, String tipo, String registro) {
        this.id = id;
        this.tipo = tipo;
        this.registro = registro;
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

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", registro='" + registro + '\'' +
                '}';
    }
}
