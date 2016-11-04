package com.ort.automovilismo.modelo;

/**
 * Created by andres on 01/11/16.
 */

public class RowCarrera {
    private String posicion;
    private String tiempoDia;
    private String tiempo;
    private String diferencia;
    private Piloto piloto;


    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getTiempoDia() {
        return tiempoDia;
    }

    public void setTiempoDia(String tiempoDia) {
        this.tiempoDia = tiempoDia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public RowCarrera(String posicion, String tiempoDia, String tiempo, String diferencia, Piloto piloto) {
        this.posicion = posicion;
        this.tiempoDia = tiempoDia;
        this.tiempo = tiempo;
        this.diferencia = diferencia;
        this.piloto = piloto;
    }
}
