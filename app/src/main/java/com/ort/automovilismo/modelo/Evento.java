package com.ort.automovilismo.modelo;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Evento {
    private int idEvento;
    private String titulo;
    private Date fecha;
    private int idDrawable;
    private String ciudad;
    private int latitud;
    private int longitud;
    private List<Piloto>listaPiloto;
    private List<Actividad>listaActividades;
    //Cosas del circuito
    private Circuito circuito;
    private int vueltas;
    private String clima;

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
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

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public List<Piloto> getListaPiloto() {
        return listaPiloto;
    }

    public void setListaPiloto(List<Piloto> listaPiloto) {
        this.listaPiloto = listaPiloto;
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
    public Evento(String titulo, Date fecha, int idDrawable, String ciudad,List<Piloto>listaPiloto) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.idDrawable = idDrawable;
        this.ciudad = ciudad;
        this.listaPiloto=listaPiloto;
    }

    public Evento(int idEvento, String titulo, Date fecha, int idDrawable, String ciudad, int latitud, int longitud, List<Piloto> listaPiloto, List<Actividad> listaActividades, Circuito circuito, int vueltas) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.fecha = fecha;
        this.idDrawable = idDrawable;
        this.ciudad = ciudad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.listaPiloto = listaPiloto;
        this.listaActividades = listaActividades;
        this.circuito = circuito;
        this.vueltas = vueltas;
    }

    public static final List<Evento> EVENTOS = new ArrayList<Evento>();


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

    }
}
