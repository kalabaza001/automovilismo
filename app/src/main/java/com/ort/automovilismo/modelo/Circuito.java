package com.ort.automovilismo.modelo;

import java.util.Date;

/**
 * Created by Andres on 10/10/2016.
 */

public class Circuito {
    private int idCircuito;
    private String Nombre;
    private double longitud;
    private int curvas;
    private String record;
    private String historia;
    private int idDrawable;

    public int getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(int idCircuito) {
        this.idCircuito = idCircuito;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getCurvas() {
        return curvas;
    }

    public void setCurvas(int curvas) {
        this.curvas = curvas;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public Circuito(int idCircuito, String nombre, double longitud, int curvas, String record, String historia, int idDrawable) {
        this.idCircuito = idCircuito;
        Nombre = nombre;
        this.longitud = longitud;
        this.curvas = curvas;
        this.record = record;
        this.historia = historia;
        this.idDrawable = idDrawable;
    }
}
