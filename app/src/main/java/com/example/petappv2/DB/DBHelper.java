package com.example.petappv2.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREAR_TABLA_PERSONA = "CREATE TABLE " + Constantes.TABLA_PERSONA +
            " (ID_PERSONA INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NOMBRES TEXT NOT NULL, " +
            " TELEFONO TEXT NOT NULL," +
            " CORREO TEXT NOT NULL," +
            " CLAVE TEXT NOT NULL," +
            " CIUDAD TEXT NOT NULL)" ;

    private static final String CREAR_TABLA_MASCOTA = "CREATE TABLE " + Constantes.TABLA_MASCOTA+
            " (ID_MASCOTA INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NOMBRE TEXT NOT NULL, " +
            " TIPO TEXT NOT NULL, " +
            " SEXO TEXT NOT NULL," +
            " TAMANIO TEXT NOT NULL," +
            " RAZA TEXT NOT NULL," +
            " EDAD INT NOT NULL," +
            " UBICACION TEXT NOT NULL," +
            " IMAGEN BLOB," +
            " FECHA TEXT NOT NULL," +
            " ESTADO TEXT NOT NULL," +
            " DESCRIPCION TEXT NOT NULL," +
            " ID_PERSONA INT NOT NULL,"+
            " FOREIGN KEY (ID_PERSONA) REFERENCES "+ Constantes.TABLA_PERSONA+ "(ID_PERSONA))";


    public DBHelper(@Nullable Context context) {
        super(context, Constantes.NOMBRE_BD, null, Constantes.VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación tabla usuarios
        sqLiteDatabase.execSQL(CREAR_TABLA_PERSONA);
        //Creación tabla MASCOTAS
        sqLiteDatabase.execSQL(CREAR_TABLA_MASCOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Actualiza de la BD por cambio de versión
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_PERSONA);//Elimina si existe
        sqLiteDatabase.execSQL(CREAR_TABLA_PERSONA);//Creación de la tabla PERSONA después de eliminar
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_MASCOTA);//Elimina si existe
        sqLiteDatabase.execSQL(CREAR_TABLA_MASCOTA);//Creación de la tabla MASCOTA después de eliminar

    }
}
