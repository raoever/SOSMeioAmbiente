package com.example.sosmeioambiente;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DenunciaDao {
    @Query("SELECT * FROM denuncia")
    List<Denuncia> getAll();

    @Query("SELECT * FROM denuncia WHERE id IN (:denunciaIds)")
    List<Denuncia> loadAllByIds(int[] denunciaIds);

    @Query("SELECT * FROM denuncia WHERE id LIKE :denunciaId LIMIT 1")
    Denuncia findById(int denunciaId);

    @Query("SELECT * FROM denuncia WHERE idUsuario LIKE :usuarioId")
    List<Denuncia> findByIdUsuario(int usuarioId);

    @Query("SELECT * FROM denuncia WHERE protocolo LIKE :protocolo LIMIT 1")
    Denuncia findByProtocolo(String protocolo);

    @Update
    void update(Denuncia p);

    @Insert
    void insertAll(Denuncia... denuncias);

    @Delete
    void delete(Denuncia denuncia);
}
