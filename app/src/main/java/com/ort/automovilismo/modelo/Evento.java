package com.ort.automovilismo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento implements Serializable {
    private int numero;
    private String idEvento;
    private String titulo;
    private Date fecha;
    private String sFecha;
    private Circuito circuito;
    private List<Actividad>listaActividades;
    private ArrayList<ResultadoEvento>resultados;

    private int vueltas;
    private String clima;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getsFecha() {
        return sFecha;
    }

    public void setsFecha(String sFecha) {
        this.sFecha = sFecha;
    }

    public ArrayList<ResultadoEvento> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<ResultadoEvento> resultados) {
        this.resultados = resultados;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

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

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public Evento(String titulo, Date fecha,ArrayList<ResultadoEvento>listaResultado) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.resultados = listaResultado;
    }

    public Evento(String idEvento, String titulo, Date fecha, List<Piloto> lis, List<Actividad> listaActividades, Circuito circuito, int vueltas) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.fecha = fecha;
        this.listaActividades = listaActividades;
        this.circuito = circuito;
        this.vueltas = vueltas;
    }

    public Evento(int numero, String idEvento, String titulo, String sFecha, Circuito circuito, List<Actividad> listaActividades, ArrayList<ResultadoEvento> listaResultado) {
        this.numero = numero;
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.sFecha = sFecha;
        this.circuito = circuito;
        this.listaActividades = listaActividades;
        this.resultados = listaResultado;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento='" + idEvento + '\'' +
                ", titulo='" + titulo + '\'' +
                ", sFecha='" + sFecha + '\'' +
                ", Circuito='" + circuito.getNombre() + " - " + circuito.getNumero() + '\'' +
                '}';
    }

}
