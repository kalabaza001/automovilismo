package com.ort.automovilismo.modelo;

import java.util.Date;

/**
 * Created by andres on 17/10/16.
 */

public class RowCampeonato {

    private String idRowCampeonato;
    private int posicion;
    private Piloto piloto;
    private String PrimerFecha;
    private String SegundaFecha;
    private int Total;

    public String getIdRowCampeonato() {
        return idRowCampeonato;
    }

    public void setIdRowCampeonato(String idRowCampeonato) {
        this.idRowCampeonato = idRowCampeonato;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public String getPrimerFecha() {
        return PrimerFecha;
    }

    public void setPrimerFecha(String primerFecha) {
        PrimerFecha = primerFecha;
    }

    public String getSegundaFecha() {
        return SegundaFecha;
    }

    public void setSegundaFecha(String segundaFecha) {
        SegundaFecha = segundaFecha;
    }

    public int getTotal() {
        return Integer.getInteger(getPrimerFecha()) + Integer.getInteger(getSegundaFecha());
    }

    public void setTotal(int total) {
        Total = total;
    }

    public RowCampeonato(String idRowCampeonato, Piloto piloto, String primerFecha, String segundaFecha, int total) {
        this.idRowCampeonato = idRowCampeonato;
        this.piloto = piloto;
        PrimerFecha = primerFecha;
        SegundaFecha = segundaFecha;
        Total = total;
    }

    public RowCampeonato(String idRowCampeonato, int posicion, String primerFecha, String segundaFecha) {
        this.posicion = posicion;
        this.idRowCampeonato = idRowCampeonato;
        PrimerFecha = primerFecha;
        SegundaFecha = segundaFecha;
        //Total = total;
    }
}
