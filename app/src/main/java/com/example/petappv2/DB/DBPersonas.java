package com.example.petappv2.DB;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.petappv2.DatosUsuario.Globales;

public class DBPersonas extends DBHelper{
    private Context context;

    public DBPersonas(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    //INSERTA EN TABLA PERSONAS
    public long insertarPersonas(Personas personas){

        try{
            //Conexión
            DBHelper dbHelper = new DBHelper(this.context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("NOMBRES", personas.getNombres());
            valores.put("TELEFONO", personas.getTelefono());
            valores.put("CORREO", personas.getCorreo());
            valores.put("CLAVE", personas.getClave());
            valores.put("CIUDAD", personas.getCiudad());

            long id= db.insert(Constantes.TABLA_PERSONA,null,valores);
            if (id > 0) {
                Toast.makeText(context, "USUARIO GUARDADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "ERROR AL CREAR EL USUARIO", Toast.LENGTH_SHORT).show();
            }
            return id;
        }catch (Exception ex){
            System.err.println(ex);
            return 0;
        }
    }
    //VERIFICA EXISTENCIA DE PERSONA
    public boolean autenticarPersonas(String correo, String clave){
        try {
            //Conexión
            DBHelper helper = new DBHelper(context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //Arreglo con las condiciones de búsqueda, WHERE
            String[] parametrosConsulta = {correo, clave};
            //Arreglo con los campos a consultar, SELECT
            String[] camposConsulta = {};
            //Se define cursor para almacenar el resultado de la búsqueda
            Cursor cursor = base_datos.query(Constantes.TABLA_PERSONA, camposConsulta, "CORREO" + "=?" + " AND "+ "CLAVE" + "=?",
                    parametrosConsulta, null, null, null);

            if (cursor.moveToFirst()) {
                Globales.setId_usuario(cursor.getInt(0));
                cursor.close();
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            System.err.println(ex);
            return false;}
    }

    //MUESTRA DATOS DE PERSONA
    public Personas verPersona(int id){

            //Conexión
            DBHelper helper = new DBHelper(context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            Personas persona = null;
            //Se define cursor para almacenar el resultado de la búsqueda
            Cursor cursorPersona = base_datos.rawQuery("SELECT * FROM " + Constantes.TABLA_PERSONA + " WHERE ID_PERSONA=" + id + " LIMIT 1", null);
            if (cursorPersona.moveToFirst()) {

                persona = new Personas();
                persona.setId_Persona(cursorPersona.getInt(0));
                persona.setNombres(cursorPersona.getString(1));
                persona.setTelefono(cursorPersona.getString(2));
                persona.setCorreo(cursorPersona.getString(3));
                persona.setClave(cursorPersona.getString(4));
                persona.setCiudad(cursorPersona.getString(5));
            }
            cursorPersona.close();
            return persona;
    }
    //MODIFICAR DATOS DE PERSONA
    public boolean editPersona(int id, String nombre, String telefono, String correo,String clave, String ciudad) {

        boolean correcto = false;

        //Conexión
        DBHelper helper = new DBHelper(context);
        //Objeto para la lectura en la base de datos
        SQLiteDatabase base_datos = helper.getWritableDatabase();

        try {
            base_datos.execSQL("UPDATE " + Constantes.TABLA_PERSONA + " SET nombres = '" + nombre + "', TELEFONO = '" + telefono + "', CORREO = '" + correo + "', CLAVE = '" + clave + "',CIUDAD = '" + ciudad + "' WHERE ID_PERSONA='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            base_datos.close();
        }

        return correcto;
    }

    public boolean eliminarUsuario(int id) {

        boolean correcto = false;
        //Conexión
        DBHelper helper = new DBHelper(context);
        //Objeto para la lectura en la base de datos
        SQLiteDatabase base_datos = helper.getWritableDatabase();

        try {
            base_datos.execSQL("DELETE FROM " + Constantes.TABLA_PERSONA + " WHERE ID_PERSONA = '" + id + "'");
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
