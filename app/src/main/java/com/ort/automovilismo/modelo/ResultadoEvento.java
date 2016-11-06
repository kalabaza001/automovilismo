package com.ort.automovilismo.modelo;

/**
 * Created by andres on 05/11/16.
 */

public class ResultadoEvento {

    private int posicion;
    private int numero;
    private String nombrePiloto;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public ResultadoEvento(int posicion, int numero, String nombrePiloto) {
        this.posicion = posicion;
        this.numero = numero;
        this.nombrePiloto = nombrePiloto;
    }

    @Override
    public String toString() {
        return "ResultadoEvento{" +
                "posicion=" + posicion +
                ", numero=" + numero +
                ", nombrePiloto='" + nombrePiloto + '\'' +
                '}';
    }
}
