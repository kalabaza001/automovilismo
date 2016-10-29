package com.ort.automovilismo.modelo;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by andres on 17/10/16.
 */

public class RowCampeonato implements Serializable {

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
    private int total;


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
        ArrayList <String> fechas = new ArrayList<>();
        fechas.add(primerFecha);
        fechas.add(segundaFecha);
        fechas.add(tercerFecha);
        fechas.add(cuartaFecha);
        fechas.add(quintaFecha);
        fechas.add(sextaFecha);
        fechas.add(septimaFecha);
        fechas.add(octavaFecha);
        fechas.add(novenaFecha);
        fechas.add(decimaFecha);
        setTotal(0);
        //Log.d("Piloto: " ,getPiloto().getApellido());
        for (String fecha:fechas) {
            try {
                int puntoFecha = Integer.valueOf(fecha);
                //Log.d("Puntos:", fecha);
                total += puntoFecha;
            }catch (Exception e){
                //Log.e("Suma puntos fecha",fecha);
            }
        }
            setTotal(total);
            //Log.d("Total", Integer.toString(total));
            return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "RowCampeonato{" +
                "posicion=" + posicion +
                ", primerFecha='" + primerFecha + '\'' +
                ", segundaFecha='" + segundaFecha + '\'' +
                ", tercerFecha='" + tercerFecha + '\'' +
                ", cuartaFecha='" + cuartaFecha + '\'' +
                ", quintaFecha='" + quintaFecha + '\'' +
                ", sextaFecha='" + sextaFecha + '\'' +
                ", septimaFecha='" + septimaFecha + '\'' +
                ", octavaFecha='" + octavaFecha + '\'' +
                ", novenaFecha='" + novenaFecha + '\'' +
                ", decimaFecha='" + decimaFecha + '\'' +
                ", total=" + total +
                ", piloto=" + piloto.getNombre() + " " + piloto.getApellido() +
                '}';
    }
}
