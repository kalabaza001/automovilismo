package com.ort.automovilismo.modelo;

import java.io.Serializable;

public class Circuito  implements Serializable {
    private String idCircuito;
    private String nombre;
    private String numero;
    private double largo;
    private int curvas;
    private String record;
    private String historia;
    private String idDrawable;
    private double latitud;
    private double longitud;

    public String getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(String idCircuito) {
        this.idCircuito = idCircuito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double longitud) {
        this.largo = longitud;
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

    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Circuito(String idCircuito, String nombre, String numero, double largo, int curvas, String record, String historia, String idDrawable, double latitud, double longitud) {
        this.idCircuito = idCircuito;
        this.nombre = nombre;
        this.numero = numero;
        this.largo = largo;
        this.curvas = curvas;
        this.record = record;
        this.historia = historia;
        this.idDrawable = idDrawable;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Circuito(String nombre, String numero, double longitud, int curvas, String record, String historia, String idDrawable) {
        this.nombre = nombre;
        this.numero = numero;
        this.longitud = longitud;
        this.curvas = curvas;
        this.record = record;
        this.historia = historia;
        this.idDrawable = idDrawable;
    }
}
