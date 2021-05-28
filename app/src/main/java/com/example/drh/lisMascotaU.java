package com.example.drh;

import java.sql.Date;

public class lisMascotaU {
    String idMascota, nombre, especie, raza, sexo, color, tatuaje, microchip, colorImg, nombrepro;
    Date fechaNac;

    public lisMascotaU(String idMascota,  String nombre, Date fechaNac, String especie, String raza, String sexo, String color, String tatuaje, String microchip, String colorImg) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tatuaje = tatuaje;
        this.microchip = microchip;
        this.colorImg=colorImg;

    }

    public String getColorImg() {
        return colorImg;
    }

    public void setColorImg(String colorImg) {
        this.colorImg = colorImg;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTatuaje() {
        return tatuaje;
    }

    public void setTatuaje(String tatuaje) {
        this.tatuaje = tatuaje;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }


}




