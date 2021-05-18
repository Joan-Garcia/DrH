package com.example.drh;

public class lisUsuario {

    public String nombre, domicilio,colonia,cp,pais,estado,correo, tel, celu,id,tipoU,ciudad,color;

    public lisUsuario(String nombre, String domicilio, String colonia, String cp, String pais, String estado, String correo, String tel, String celu, String id, String tipoU, String ciudad, String color) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.colonia = colonia;
        this.cp = cp;
        this.pais = pais;
        this.estado = estado;
        this.correo = correo;
        this.tel = tel;
        this.celu = celu;
        this.id = id;
        this.tipoU = tipoU;
        this.ciudad = ciudad;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCp() {
        return cp;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTel() {
        return tel;
    }

    public String getCelu() {
        return celu;
    }

    public String getId() {
        return id;
    }

    public String getTipoU() {
        return tipoU;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getColor() {
        return color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCelu(String celu) {
        this.celu = celu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipoU(String tipoU) {
        this.tipoU = tipoU;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
