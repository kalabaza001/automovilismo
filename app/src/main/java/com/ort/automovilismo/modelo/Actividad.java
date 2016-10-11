package com.ort.automovilismo.modelo;

import java.util.Date;

/**
 * Created by Andres on 10/10/2016.
 */

public class Actividad {
    private String titulo;
    private Date fecha;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Actividad(String titulo, Date fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
    }
}
