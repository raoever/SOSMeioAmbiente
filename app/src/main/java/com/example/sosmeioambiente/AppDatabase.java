package com.example.sosmeioambiente;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class, Denuncia.class, Comentario.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
    public abstract DenunciaDao denunciaDao();
    public abstract ComentarioDao comentarioDao();
}
