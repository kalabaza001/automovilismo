package com.ort.automovilismo.modelo;

import com.ort.automovilismo.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Evento {
    private String titulo;
    private Date fecha;
    private int idDrawable;
    private String direccion;
    private List<Piloto>listaPiloto;

    public Evento(String titulo, Date fecha, int idDrawable, String direccion,List<Piloto>listaPiloto) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.idDrawable = idDrawable;
        this.direccion = direccion;
        this.listaPiloto=listaPiloto;
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


    public int getIdDrawable() {
        return idDrawable;
    }

    public String getDireccion() { return direccion;
    }

    public Date getFecha() { return fecha;
    }

    public String getTitulo() { return titulo;
    }
}
