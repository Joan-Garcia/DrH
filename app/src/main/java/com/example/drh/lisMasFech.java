package com.example.drh;

import java.sql.Date;

public class lisMasFech {
    String idAplicacion, nombremascota, producto, color, descripcion;
    Date fechaApli, proxFecV;

    public lisMasFech(String idAplicacion, String nombremascota, String producto, String color, Date fechaApli, Date proxFecV, String descripcion) {
        this.idAplicacion = idAplicacion;
        this.nombremascota = nombremascota;
        this.producto = producto;
        this.color = color;
        this.fechaApli = fechaApli;
        this.proxFecV = proxFecV;
        this.descripcion=descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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