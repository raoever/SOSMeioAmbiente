package com.example.sosmeioambiente;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Denuncia implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "idUsuario")
    private int idUsuario;
    @ColumnInfo(name = "tipo")
    private String tipo;
    @ColumnInfo(name = "endereco")
    private String endereco;
    @ColumnInfo(name = "descricao")
    private String descricao;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name = "longitude")
    private String longitude;

    public Denuncia() {
    }

    public Denuncia(int id, int idUsuario, String tipo, String endereco, String descricao, String latitude, String longitude) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.endereco = endereco;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", tipo='" + tipo + '\'' +
                ", endereco='" + endereco + '\'' +
                ", descricao='" + descricao + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
