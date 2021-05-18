package com.example.drh;

import java.sql.Date;

public class lisEDespa {
    String idDesp, nombremascota, despa,color;
    Date fechaApli, proxFecD;

    public lisEDespa(String idDesp, String nombremascota, String despa, String color, Date fechaApli, Date proxFecD) {
        this.idDesp = idDesp;
        this.nombremascota = nombremascota;
        this.despa = despa;
        this.color = color;
        this.fechaApli = fechaApli;
        this.proxFecD = proxFecD;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdDesp() {
        return idDesp;
    }

    public void setIdDesp(String idDesp) {
        this.idDesp = idDesp;
    }

    public Date getProxFecD() {
        return proxFecD;
    }

    public void setProxFecD(Date proxFecD) {
        this.proxFecD = proxFecD;
    }

    public String getNombremascota() {
        return nombremascota;
    }

    public void setNombremascota(String nombremascota) {
        this.nombremascota = nombremascota;
    }

    public String getDespa() {
        return despa;
    }

    public void setDespa(String despa) {
        this.despa = despa;
    }

    public Date getFechaApli() {
        return fechaApli;
    }

    public void setFechaApli(Date fechaApli) {
        this.fechaApli = fechaApli;
    }


}


