package com.example.drh;

import android.widget.ImageView;

import java.sql.Date;

public class lisMasFech {
    String idAplicacion, nombremascota, producto, color, idAp;
    Date fechaApli, proxFecV;
    Integer iv;

    public lisMasFech(String idAplicacion, String nombremascota, String producto, String color, Date fechaApli, Date proxFecV,
                      Integer a, String idp) {
        this.idAplicacion = idAplicacion;
        this.nombremascota = nombremascota;
        this.producto = producto;
        this.color = color;
        this.fechaApli = fechaApli;
        this.proxFecV = proxFecV;
        this.iv=a;
        this.idAp=idp;
    }

    public String getIdAp() {
        return idAp;
    }

    public void setIdAp(String idAp) {
        this.idAp = idAp;
    }

    public Integer getIv() {
        return iv;
    }

    public void setIv(Integer iv) {
        this.iv = iv;
    }



    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombremascota() {
        return nombremascota;
    }

    public void setNombremascota(String nombremascota) {
        this.nombremascota = nombremascota;
    }

    public String getVacuna() {
        return producto;
    }

    public void setVacuna(String vacuna) {
        this.producto = producto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaApli() {
        return fechaApli;
    }

    public void setFechaApli(Date fechaApli) {
        this.fechaApli = fechaApli;
    }

    public Date getProxFecV() {
        return proxFecV;
    }

    public void setProxFecV(Date proxFecV) {
        this.proxFecV = proxFecV;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}