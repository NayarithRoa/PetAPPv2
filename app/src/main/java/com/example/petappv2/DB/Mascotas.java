package com.example.petappv2.DB;

import java.sql.Date;

public class Mascotas {
    private long id_Mascota;
    private String nombre;
    private String tipo;
    private String sexo;
    private String tamanio;
    private String raza;
    private int edad;
    private String ubicacion;
    private byte[] imagen;
    private String estado;
    private String descripcion;
    private String fecha;
    private long id_Persona;

    public Mascotas(long id_Mascota, String nombre, String tipo, String sexo, String tamanio, String raza, int edad, String ubicacion, byte[] imagen, String estado, String descripcion, String fecha, long id_Persona) {
        this.id_Mascota = id_Mascota;
        this.nombre = nombre;
        this.tipo = tipo;
        this.sexo = sexo;
        this.tamanio = tamanio;
        this.raza = raza;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecha=fecha;
        this.id_Persona = id_Persona;
    }
    public Mascotas() {
        this.id_Mascota = id_Mascota;
        this.nombre = nombre;
        this.tipo = tipo;
        this.sexo = sexo;
        this.tamanio = tamanio;
        this.raza = raza;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.id_Persona = id_Persona;
    }

    public long getId_Mascota() {
        return id_Mascota;
    }

    public void setId_Mascota(long id_Mascota) {
        this.id_Mascota = id_Mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(long id_Persona) {
        this.id_Persona = id_Persona;
    }
}
