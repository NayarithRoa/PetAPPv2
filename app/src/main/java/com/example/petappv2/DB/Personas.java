package com.example.petappv2.DB;

public class Personas {
    private long id_Persona;
    private String nombres;
    private String telefono;
    private String correo;
    private String clave;
    private String ciudad;

    public Personas(long id_Persona, String nombres, String telefono, String correo, String clave, String ciudad) {
        this.id_Persona = id_Persona;
        this.nombres = nombres;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.ciudad = ciudad;
    }

    public Personas() {
        this.id_Persona = id_Persona;
        this.nombres = nombres;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.ciudad = ciudad;
    }

    public long getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(long id_Persona) {
        this.id_Persona = id_Persona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
