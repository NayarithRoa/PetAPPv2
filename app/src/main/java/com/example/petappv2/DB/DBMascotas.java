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

    //MUESTRA DATOS DE MASCOTA
    public Mascotas verMascota(int id){

        //Conexión
        DBHelper helper = new DBHelper(context);
        //Objeto para la lectura en la base de datos
        SQLiteDatabase base_datos = helper.getReadableDatabase();
        Mascotas mascota = null;
        //Se define cursor para almacenar el resultado de la búsqueda
        Cursor cursorMascota = base_datos.rawQuery("SELECT * FROM " + Constantes.TABLA_MASCOTA + " WHERE ID_MASCOTA=" + id + " LIMIT 1", null);
        if (cursorMascota.moveToFirst()) {

            mascota = new Mascotas();
            mascota.setId_Mascota(cursorMascota.getInt(0));
            mascota.setNombre(cursorMascota.getString(1));
            mascota.setTipo(cursorMascota.getString(2));
            mascota.setSexo(cursorMascota.getString(3));
            mascota.setTamanio(cursorMascota.getString(4));
            mascota.setRaza(cursorMascota.getString(5));
            mascota.setEdad(cursorMascota.getInt(6));
            mascota.setUbicacion(cursorMascota.getString(7));
            mascota.setImagen(cursorMascota.getString(8));
            mascota.setFecha(cursorMascota.getString(9));
            mascota.setEstado(cursorMascota.getString(10));
            mascota.setDescripcion(cursorMascota.getString(11));


        }
        cursorMascota.close();
        return mascota;
    }
    //MODIFICAR DATOS DE MASCOTA
    public boolean editMascota(int id, String nombre, String tipo, String sexo,String tamanio,
                               String raza, Integer edad, String ubicacion,String imagen, String fecha,
                               String estado, String descripcion) {

        boolean correcto = false;

        //Conexión
        DBHelper helper = new DBHelper(context);
        //Objeto para la lectura en la base de datos
        SQLiteDatabase base_datos = helper.getWritableDatabase();

        try {
            base_datos.execSQL("UPDATE " + Constantes.TABLA_MASCOTA + " SET NOMBRE = '" + nombre + "', TIPO = '" + tipo + "'," +
                    " SEXO = '" + sexo + "', TAMANIO = '" + tamanio + "',RAZA = '" + raza + "',EDAD = '" + edad + "'," +
                    " UBICACION = '" + ubicacion + "', IMAGEN = '" + imagen + "',FECHA = '" + fecha + "',ESTADO = '" + estado + "',DESCRIPCION = '" + descripcion + "' " +
                    "WHERE ID_MASCOTA='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            base_datos.close();
        }

        return correcto;
    }

    public boolean eliminarMascota(int id) {

        boolean correcto = false;
        //Conexión
        DBHelper helper = new DBHelper(context);
        //Objeto para la lectura en la base de datos
        SQLiteDatabase base_datos = helper.getWritableDatabase();

        try {
            base_datos.execSQL("DELETE FROM " + Constantes.TABLA_MASCOTA + " WHERE ID_MASCOTA = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            base_datos.close();
        }

        return correcto;
    }
}

