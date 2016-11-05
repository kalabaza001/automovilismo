package com.ort.automovilismo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Circuito;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.RowCampeonato;
import com.ort.automovilismo.modelo.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FragmentoCircuito extends Fragment {

    private ImageView circuito_miniatura;
    private TextView circuito_nombre;
    private TextView circuito_numero;
    private TextView circuito_largo;
    private TextView circuito_curvas;


    final static String CIRCUITO = "Circuito";
    private Circuito mCircuito;
    private Button mBotonIr;


    public FragmentoCircuito() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mCircuito = (Circuito) savedInstanceState.getSerializable(CIRCUITO);
        }

        Bundle args = getArguments();
        mCircuito = (Circuito) args.getSerializable(CIRCUITO);



        View view = inflater.inflate(R.layout.fragmento_circuito, container, false);

        circuito_nombre = (TextView) view.findViewById(R.id.circuito_nombre);
        circuito_numero = (TextView) view.findViewById(R.id.circuito_numero);
        circuito_largo = (TextView) view.findViewById(R.id.circuito_largo);
        circuito_curvas = (TextView) view.findViewById(R.id.circuito_curvas);
        circuito_miniatura = (ImageView) view.findViewById(R.id.circuito_miniatura2);

        circuito_nombre.setText(mCircuito.getNombre().toString());
        circuito_numero.setText(mCircuito.getNumero().toString());
        circuito_largo.setText(String.valueOf(mCircuito.getLargo()).toString());
        circuito_curvas.setText(String.valueOf(mCircuito.getCurvas()).toString());

        Glide.with(view.getContext())
                // .load("http://superturismo.com.uy/wp-content/uploads/2015/10/rama-foto.jpg")
                .load("http://www.baremos.uy:8000/images_pilotos/"+mCircuito.getIdDrawable())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                // .placeholder(R.drawable.placeholder)
                .error(R.drawable.auvo_error)
                // .centerCrop()
                .fitCenter()
                .into(circuito_miniatura);
        mBotonIr = (Button) view.findViewById(R.id.btnIr);
        mBotonIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent intent;
                    if(Utils.isOnline(getActivity())){
                        intent = new Intent(getActivity(),MapsActivity.class);
                        // String auxLatitud=latitud.getText().toString();
                        // String auxLongitud=longitud.getText().toString();

                        String auxLatitud = mCircuito.getLatitud()+"";
                        String auxLongitud = mCircuito.getLongitud()+"";

                        intent.putExtra("latitud",auxLatitud);
                        intent.putExtra("longitud",auxLongitud);

                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getActivity(), "Sin conexion no se puede visualizar el mapa", Toast.LENGTH_SHORT).show();




            }
        });
        return view;
    }





}


