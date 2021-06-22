package com.example.sosmeioambiente;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComentarioDao {
    @Query("SELECT * FROM comentario")
    List<Comentario> getAll();

    @Query("SELECT * FROM comentario WHERE id IN (:comentarioIds)")
    List<Comentario> loadAllByIds(int[] comentarioIds);

    @Query("SELECT * FROM comentario WHERE id LIKE :comentarioId LIMIT 1")
    Comentario findById(int comentarioId);

    @Update
    void update(Comentario p);

    @Insert
    void insertAll(Comentario... comentarios);

    @Delete
    void delete(Comentario comentario);
}
