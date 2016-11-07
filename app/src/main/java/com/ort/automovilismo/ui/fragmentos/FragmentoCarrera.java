package com.ort.automovilismo.ui.fragmentos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.RowCarrera;
import com.ort.automovilismo.modelo.Utils;
import com.ort.automovilismo.ui.RecyclerClickListener;
import com.ort.automovilismo.ui.adaptadores.AdaptadorCarrera;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class FragmentoCarrera extends Fragment {
    private RecyclerView reciclador;
    private AdaptadorCarrera adaptador;
    private ProgressDialog progressDiag;
    private int llamada = 2;

    public FragmentoCarrera() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Consumo servicio
        View view = inflater.inflate(R.layout.fragmento_carrera, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setRepeatingAsyncTask();
    }

    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            new GetDataTask(getActivity()).execute(Utils.getServidor() + "carrera/" + llamada);
                            llamada = llamada + 1;
                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 60 * 200);  // interval of one minute

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDiag = new ProgressDialog(getActivity());
        progressDiag.setMessage("loading");
        progressDiag.show();
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
    }

    private class GetDataTask extends AsyncTask<String, Void, String> {
        private Activity m_activity;

        public GetDataTask(Activity activity) {
            m_activity = activity;
        }


        protected String doInBackground(String... urls) {
            try {
                // CONEXION
                URL url = new URL(urls[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //con.setRequestProperty("_id", String.valueOf(idAct));
                con.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String valor = reader.readLine();
                reader.close();
                con.disconnect();
                return valor;
            } catch (Exception e) {
                Log.e("ERROR ", e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String result) {
            if (m_activity != null) {
                List<RowCarrera> LRows = new ArrayList<>();
                try {
                    progressDiag.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Pilotos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    Date tiempoAnterior = new Date();
                    //Cargo los datos del JSONArray de carrera
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectRowCarrera = jsonArray.getJSONObject(i);

                        //Datos de la vuelta
                        int notificacion = jsonObjectRowCarrera.optInt("notificacion");
                        int vuelta = jsonObjectRowCarrera.optInt("vuelta");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss.SSS");
                        String diferencia = "0";

                        double sec = 0.0;
                        //Datos de la pasada
                        JSONArray jsonArrayPasada = jsonObjectRowCarrera.getJSONArray("tabla");
                        for (int j = 0; j < jsonArrayPasada.length(); j++) {
                            JSONObject jsonObjectRowPasada = jsonArrayPasada.getJSONObject(j);
                            String posicion = jsonObjectRowPasada.getString("posicion");
                            String tiempo = jsonObjectRowPasada.getString("tiempo");
                            if (!posicion.equals("1")) {
                                Date tiempoActual = simpleDateFormat.parse(tiempo);
                                long millis = tiempoActual.getTime() - tiempoAnterior.getTime();
                                sec = millis / 1000.000;
                                diferencia = String.valueOf(Math.abs(sec));
                            }
                            tiempoAnterior = simpleDateFormat.parse(tiempo);


                            //Datos del piloto
                            JSONObject jsonObjectPiloto = jsonObjectRowPasada.getJSONObject("piloto");
                            String nombre = jsonObjectPiloto.optString("nombre");
                            String apellido = jsonObjectPiloto.optString("apellido");
                            int numero = jsonObjectPiloto.optInt("numero");
                            String marcaAuto = jsonObjectPiloto.optString("marcaAuto");

                            Piloto piloto = new Piloto(nombre, apellido, numero, marcaAuto);
                            RowCarrera rowCarrera = new RowCarrera(posicion, "0", tiempo, diferencia, piloto);
                            LRows.add(rowCarrera);
                        }
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }
                adaptador = new AdaptadorCarrera(LRows);
                adaptador.setListener(new RecyclerClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
                reciclador.setAdapter(adaptador);
                progressDiag.dismiss();
                //viewPilotos();
            }
        }
    }
}


