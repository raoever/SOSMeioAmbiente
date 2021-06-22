package com.example.sosmeioambiente;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id IN (:usuarioIds)")
    List<Usuario> loadAllByIds(int[] usuarioIds);

    @Query("SELECT * FROM usuario WHERE id LIKE :usuarioId LIMIT 1")
    Usuario findById(int usuarioId);

    @Query("SELECT * FROM usuario WHERE email LIKE :email LIMIT 1")
    Usuario findByEmail(String email);

    @Update
    void update(Usuario p);

    @Insert
    void insertAll(Usuario... usuarios);

    @Delete
    void delete(Usuario usuario);
}
