package com.example.drh;

public class contacto {
    String puesto,nombre, correo,  numero, redSocial;
    Integer imagen1, imagen2;

    public contacto(String puesto, String nombre,  String correo, String numero, String rS,Integer imagen1, Integer imagen2) {
        this.puesto = puesto;
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.redSocial=rS;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
    }

    public String getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(String redSocial) {
        this.redSocial = redSocial;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getImagen1() {
        return imagen1;
    }

    public void setImagen1(Integer imagen1) {
        this.imagen1 = imagen1;
    }

    public Integer getImagen2() {
        return imagen2;
    }

    public void setImagen2(Integer imagen2) {
        this.imagen2 = imagen2;
    }
}
