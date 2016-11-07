package com.ort.automovilismo.ui.fragmentos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Evento;
import com.ort.automovilismo.ui.ResultadosFragment;
import com.ort.automovilismo.ui.adaptadores.AdaptadorEventos;
import com.ort.automovilismo.ui.adaptadores.AdaptadorHorarios;

import java.util.ArrayList;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoEvento extends Fragment {

    private static final String EVENTO = "Evento";
    private ArrayList<Evento> LEventos = new ArrayList<>();
    private ProgressDialog progressDiag;
    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorEventos adaptador;
    //private ArrayList<Evento> LEventos = new ArrayList<Evento>();s

    public static FragmentoEvento nuevaInstancia(Evento evento) {
        FragmentoEvento fragment = new FragmentoEvento();
        Bundle args = new Bundle();
        args.putSerializable(EVENTO,evento);
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

        final Evento evento = (Evento) getArguments().getSerializable(EVENTO);

        //Elementos de la vista
        TextView tituloEvento = (TextView) view.findViewById(R.id.evento_titulo);
        TextView fechas = (TextView) view.findViewById(R.id.evento_fechas);
        TextView circuitoNombre = (TextView) view.findViewById(R.id.circuito_nombre);
        TextView circuitoNumero = (TextView) view.findViewById(R.id.circuito_numero);
        TextView longitud = (TextView) view.findViewById(R.id.circuito_longitud);
        TextView curvas = (TextView) view.findViewById(R.id.circuito_curvas);
        ImageView circuitoMiniatura = (ImageView) view.findViewById(R.id.circuito_miniatura);
        Button btnResultados = (Button) view.findViewById(R.id.btnResultados);


        tituloEvento.setText(evento.getTitulo());
        fechas.setText(evento.getsFecha());
        circuitoNombre.setText(evento.getCircuito().getNombre());
        circuitoNumero.setText(evento.getCircuito().getNumero());
        longitud.setText(String.valueOf(evento.getCircuito().getLongitud()));
        curvas.setText(String.valueOf(evento.getCircuito().getCurvas()));
        Glide.with(view.getContext())
                // .load("http://superturismo.com.uy/wp-content/uploads/2015/10/rama-foto.jpg")
                .load("http://www.baremos.uy:8000/images_pilotos/"+evento.getCircuito().getIdDrawable())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                // .placeholder(R.drawable.placeholder)
                .error(R.drawable.auvo_error)
                // .centerCrop()
                .fitCenter()
                .into(circuitoMiniatura);

        //Cargo horarios
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorHorarios adapter = new AdaptadorHorarios(evento.getListaActividades());
        reciclador.setAdapter(adapter);
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultadosFragment resultadosFragment = ResultadosFragment.newInstance(evento.getResultados());
                getFragmentManager().beginTransaction().replace(R.id.contenedor_principal, resultadosFragment).addToBackStack("").commit();
            }
        });





    }




}
