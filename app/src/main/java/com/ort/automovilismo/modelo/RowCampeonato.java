package com.ort.automovilismo.modelo;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by andres on 17/10/16.
 */

public class RowCampeonato {

    private String idRowCampeonato;
    private int posicion;
    private Piloto piloto;
    private String primerFecha;
    private String segundaFecha;
    private String tercerFecha;
    private String cuartaFecha;
    private String quintaFecha;
    private String sextaFecha;
    private String septimaFecha;
    private String octavaFecha;
    private String novenaFecha;
    private String decimaFecha;
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
        return primerFecha;
    }

    public void setPrimerFecha(String primerFecha) {
        this.primerFecha = primerFecha;
    }

    public String getSegundaFecha() {
        return segundaFecha;
    }

    public void setSegundaFecha(String segundaFecha) {
        this.segundaFecha = segundaFecha;
    }

    public String getTercerFecha() {
        return tercerFecha;
    }

    public void setTercerFecha(String tercerFecha) {
        this.tercerFecha = tercerFecha;
    }

    public String getCuartaFecha() {
        return cuartaFecha;
    }

    public void setCuartaFecha(String cuartaFecha) {
        this.cuartaFecha = cuartaFecha;
    }

    public String getQuintaFecha() {
        return quintaFecha;
    }

    public void setQuintaFecha(String quintaFecha) {
        this.quintaFecha = quintaFecha;
    }

    public String getSextaFecha() {
        return sextaFecha;
    }

    public void setSextaFecha(String sextaFecha) {
        this.sextaFecha = sextaFecha;
    }

    public String getSeptimaFecha() {
        return septimaFecha;
    }

    public void setSeptimaFecha(String septimaFecha) {
        this.septimaFecha = septimaFecha;
    }

    public String getOctavaFecha() {
        return octavaFecha;
    }

    public void setOctavaFecha(String octavaFecha) {
        this.octavaFecha = octavaFecha;
    }

    public String getNovenaFecha() {
        return novenaFecha;
    }

    public void setNovenaFecha(String novenaFecha) {
        this.novenaFecha = novenaFecha;
    }

    public String getDecimaFecha() {
        return decimaFecha;
    }

    public void setDecimaFecha(String decimaFecha) {
        this.decimaFecha = decimaFecha;
    }

    public int getTotal() {
        try {
            return Integer.valueOf(getPrimerFecha()) + Integer.valueOf(getSegundaFecha()) + Integer.valueOf(getTercerFecha()) + Integer.valueOf(getQuintaFecha()) + Integer.valueOf(getSextaFecha()) + Integer.valueOf(getSeptimaFecha()) + Integer.valueOf(getOctavaFecha()) + Integer.valueOf(getNovenaFecha()) + Integer.valueOf(getDecimaFecha());
        }catch (Exception e){
            return  0;
        }

    }

    public void setTotal(int total) {
        Total = total;
    }


    public RowCampeonato(String idRowCampeonato, int posicion, Piloto piloto, String primerFecha, String segundaFecha, String tercerFecha, String cuartaFecha, String quintaFecha, String sextaFecha, String septimaFecha, String octavaFecha, String novenaFecha, String decimaFecha) {
        this.idRowCampeonato = idRowCampeonato;
        this.posicion = posicion;
        this.piloto = piloto;
        this.primerFecha = primerFecha;
        this.segundaFecha = segundaFecha;
        this.tercerFecha = tercerFecha;
        this.cuartaFecha = cuartaFecha;
        this.quintaFecha = quintaFecha;
        this.sextaFecha = sextaFecha;
        this.septimaFecha = septimaFecha;
        this.octavaFecha = octavaFecha;
        this.novenaFecha = novenaFecha;
        this.decimaFecha = decimaFecha;
    }
}
