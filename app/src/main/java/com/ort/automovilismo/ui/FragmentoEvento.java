package com.ort.automovilismo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Evento;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoEvento extends Fragment {

    private static final String INDICE_SECCION
            = "EventoElegido";

    private TextView titulo;
    private TextView direccion;
    private ImageView circuito;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;

    public static FragmentoEvento nuevaInstancia(int indiceSeccion) {
        FragmentoEvento fragment = new FragmentoEvento();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_lista_eventos, container, false);

        titulo = (TextView) view.findViewById(R.id.titulo_evento);
        direccion=(TextView) view.findViewById(R.id.lugar_evento);
        circuito=(ImageView) view.findViewById(R.id.miniatura_evento2);
       // layoutManager = new GridLayoutManager(getActivity(), 2);


        int indiceSeccion = getArguments().getInt(INDICE_SECCION);


       //obtengo el evento y cargo en el layout los datos del evento
        Evento evento=Evento.EVENTOS.get(indiceSeccion);
        titulo.setText(evento.getTitulo());
        direccion.setText(evento.getCiudad());
        circuito.setImageResource(evento.getIdDrawable());
        return view;
    }

}
