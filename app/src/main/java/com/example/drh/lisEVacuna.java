package com.example.drh;

import java.sql.Date;

public class lisEVacuna {
    //idvacuna mascota fecha vacuna proxfecha
    String idVacuna, nombremascota, vacuna,color;
    Date fechaApli, proxFecV;

    public lisEVacuna(String idVacuna, String nombremascota, String vacuna, Date fechaApli, Date proxFecV, String color) {
        this.idVacuna = idVacuna;
        this.nombremascota = nombremascota;
        this.vacuna = vacuna;
        this.fechaApli = fechaApli;
        this.proxFecV = proxFecV;
        this.color=color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(String idVacuna) {
        this.idVacuna = idVacuna;
    }

    public String getNombremascota() {
        return nombremascota;
    }

    public void setNombremascota(String nombremascota) {
        this.nombremascota = nombremascota;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
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
}
