package com.ort.automovilismo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Actividad;
import com.ort.automovilismo.modelo.Circuito;
import com.ort.automovilismo.modelo.Comida;
import com.ort.automovilismo.modelo.Evento;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.ResultadoEvento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoEvento extends Fragment {

    private static final String INDICE_SECCION = "EventoElegido";
    private ArrayList<Evento> LEventos = new ArrayList<>();
    private ProgressDialog progressDiag;
    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorEventos adaptador;
    //private ArrayList<Evento> LEventos = new ArrayList<Evento>();s

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
        View view = inflater.inflate(R.layout.fragmento_eventos, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/eventos");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);
       // LEventos = (ArrayList<Evento>) getArguments().getSerializable("ListaEventos");

        //Elementos de la vista
        TextView tituloEvento = (TextView) view.findViewById(R.id.titulo_evento);
        TextView lugarEvento = (TextView) view.findViewById(R.id.lugar_evento);
        TextView circuitoNombre = (TextView) view.findViewById(R.id.circuito_nombre);
        TextView circuitoNumero = (TextView) view.findViewById(R.id.circuito_numero);

        //Consumo servicio
        progressDiag = new ProgressDialog(getActivity());
        progressDiag.setMessage("loading");
        progressDiag.show();
        //new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/eventos");
        //SystemClock.sleep(2000);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);


        reciclador.setAdapter(adaptador);

        //obtengo el evento y cargo en el layout los datos del evento
        switch (indiceSeccion) {
            case 0:
                //adaptador = new AdaptadorCategorias(Comida.PLATILLOS);
                //adaptador = new AdaptadorEventos(LEventos,indiceSeccion);
                //tituloEvento.setText(LEventos.get(indiceSeccion).getTitulo());
                lugarEvento.setText("PINAR");
                circuitoNombre.setText("BORRAT");
                circuitoNumero.setText("Antihorario 6");
                break;
            case 1:
                //adaptador = new AdaptadorCategorias(Comida.BEBIDAS);
                //adaptador = new AdaptadorEventos(LEventos,indiceSeccion);
                tituloEvento.setText("hola1");
                break;
            case 2:
                //adaptador = new AdaptadorCategorias(Comida.POSTRES);
                //adaptador = new AdaptadorEventos(LEventos,indiceSeccion);
                tituloEvento.setText("Caca");
                break;
        }
    }

}
