package com.example.petappv2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBMascotas extends DBHelper{
    Context context;

    public DBMascotas(@Nullable Context context) {
        super(context);
        this.context  = context;
    }

    //INSERTA EN TABLA MASCOTAS
    public long insertarMascotas(Mascotas mascotas){

        try{
            //Conexión
            DBHelper dbHelper = new DBHelper(this.context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", mascotas.getTipo());
            valores.put("TIPO", mascotas.getTipo());
            valores.put("SEXO", mascotas.getSexo());
            valores.put("TAMANIO", mascotas.getTamanio());
            valores.put("RAZA", mascotas.getRaza());
            valores.put("EDAD", mascotas.getEdad());
            valores.put("UBICACION", mascotas.getUbicacion());
            valores.put("FECHA", String.valueOf(mascotas.getFecha()));
            valores.put("ESTADO", mascotas.getEstado());
            valores.put("DESCRIPCION", mascotas.getDescripcion());
            valores.put("ID_PERSONA", mascotas.getId_Persona());

            long id=db.insert(Constantes.TABLA_MASCOTA,null,valores);
            if (id > 0) {
                Toast.makeText(context, "MASCOTA ALMACENADA", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "ERROR AL CREAR LA MASCOTA", Toast.LENGTH_SHORT).show();
            }
            return id;
        }catch (Exception ex){
            System.err.println(ex);
            return 0;
        }
    }
    //Metodo para mostrar las mascotas almacenadas en BD
    public ArrayList<Mascotas> mostrarMascotas() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Mascotas> listaMascota = new ArrayList<>();
        Mascotas mascota;
        Cursor cursorMascotas = null;
        cursorMascotas = db.rawQuery("SELECT * FROM " + Constantes.TABLA_MASCOTA, null);

        if (cursorMascotas.moveToFirst()) {
            do {
                mascota = new Mascotas();
                mascota.setId_Mascota(cursorMascotas.getInt(0));
                mascota.setTipo(cursorMascotas.getString(1));
                mascota.setTipo(cursorMascotas.getString(2));
                mascota.setSexo(cursorMascotas.getString(3));
                mascota.setTamanio(cursorMascotas.getString(4));
                mascota.setRaza(cursorMascotas.getString(5));
                mascota.setEdad(cursorMascotas.getInt(6));
                mascota.setUbicacion(cursorMascotas.getString(7));
                mascota.setImagen(cursorMascotas.getString(8));
                mascota.setEstado(cursorMascotas.getString(9));
                mascota.setDescripcion(cursorMascotas.getString(10));
                mascota.setFecha(cursorMascotas.getString(11));

                listaMascota.add(mascota);
            } while (cursorMascotas.moveToNext());
        }
        cursorMascotas.close();

        return listaMascota;
    }
}

