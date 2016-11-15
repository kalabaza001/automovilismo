package com.ort.automovilismo.modelo;

import java.io.Serializable;
import java.util.Date;

public class Actividad implements Serializable {
    private String titulo;
    private Date fechaIncio;
    private Date fechaFin;
    private String horaDH;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraDH() {
        return horaDH;
    }

    public void setHoraDH(String horaDH) {
        this.horaDH = horaDH;
    }

    public Actividad(String titulo, Date fechaIncio, Date fechaFin) {
        this.titulo = titulo;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public Actividad(String titulo, String horaDH) {
        this.titulo = titulo;
        this.horaDH = horaDH;
    }
}
