package com.ort.automovilismo.modelo;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Evento {
    private String idEvento;
    private String titulo;
    private Date fecha;
    private String sFecha;
    private Circuito circuito;
    private List<Actividad>listaActividades;
    private List<ResultadoEvento>resultados;
    //Cosas del circuito

    private int vueltas;
    private String clima;

    public String getsFecha() {
        return sFecha;
    }

    public void setsFecha(String sFecha) {
        this.sFecha = sFecha;
    }

    public List<ResultadoEvento> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoEvento> resultados) {
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

    // New
    public Evento(String titulo, Date fecha,List<ResultadoEvento>listaResultado) {
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

    public Evento(String idEvento, String titulo, String sFecha, Circuito circuito, List<Actividad> listaActividades, ArrayList<ResultadoEvento> listaResultado) {
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

    /*public static final List<Evento> EVENTOS = new ArrayList<Evento>();


    static {

        List<Piloto>listaPilotoEvento1=new ArrayList<>();
        listaPilotoEvento1.add(Piloto.PILOTOS.get(2));
        listaPilotoEvento1.add(Piloto.PILOTOS.get(0));
        listaPilotoEvento1.add(Piloto.PILOTOS.get(1));
        EVENTOS.add(new Evento("Fecha 1", new Date(2016,1,23),R.drawable.camarones,"Artigas",listaPilotoEvento1));
        List<Piloto>listaPilotoEvento2=new ArrayList<>();
        listaPilotoEvento2.add(Piloto.PILOTOS.get(3));
        listaPilotoEvento2.add(Piloto.PILOTOS.get(0));
        listaPilotoEvento2.add(Piloto.PILOTOS.get(1));
        EVENTOS.add(new Evento("Fecha 2", new Date(2016,2,23),R.drawable.cafe,"Rivera",listaPilotoEvento2));
        List<Piloto>listaPilotoEvento3=new ArrayList<>();
        listaPilotoEvento3.add(Piloto.PILOTOS.get(0));
        listaPilotoEvento3.add(Piloto.PILOTOS.get(4));
        listaPilotoEvento3.add(Piloto.PILOTOS.get(1));
        EVENTOS.add(new Evento("Fecha 3", new Date(2016,3,23),R.drawable.coctel,"Colonia",listaPilotoEvento3));
        List<Piloto>listaPilotoEvento4=new ArrayList<>();
        listaPilotoEvento4.add(Piloto.PILOTOS.get(1));
        listaPilotoEvento4.add(Piloto.PILOTOS.get(3));
        listaPilotoEvento4.add(Piloto.PILOTOS.get(2));
        EVENTOS.add(new Evento("Fecha 4", new Date(2016,4,23),R.drawable.jugo_natural,"Mercedes",listaPilotoEvento4));
        List<Piloto>listaPilotoEvento5=new ArrayList<>();
        listaPilotoEvento5.add(Piloto.PILOTOS.get(3));
        listaPilotoEvento5.add(Piloto.PILOTOS.get(4));
        listaPilotoEvento5.add(Piloto.PILOTOS.get(1));
        EVENTOS.add(new Evento("Fecha 5", new Date(2016,5,23),R.drawable.pastel_fresa,"Montevideo",listaPilotoEvento5));

    }*/
}
