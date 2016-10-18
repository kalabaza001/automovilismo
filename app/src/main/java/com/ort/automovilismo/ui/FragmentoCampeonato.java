package com.ort.automovilismo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Piloto;
import com.ort.automovilismo.modelo.RowCampeonato;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FragmentoCampeonato extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorCampeonato adaptador;
    private ProgressDialog progressDiag;
    final ArrayList<RowCampeonato> LRows = new ArrayList<RowCampeonato>();
    private Bitmap imagenPiloto;

    public FragmentoCampeonato() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Consumo servicio
        new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/campeonato");
        //Lo duermo porque a veces tarde minimo
        progressDiag = new ProgressDialog(getActivity());
        progressDiag.setMessage("loading");
        progressDiag.show();
        //ProgressDialog progressBar = ProgressDialog.show(getActivity(), "Espere por favor...", "Pilotos...", true);
        SystemClock.sleep(1000);

        View view = inflater.inflate(R.layout.fragmento_campeonato, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        //adaptador = new AdaptadorPilotos(LPilotos);
        //reciclador.setAdapter(adaptador);
        return view;
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
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    progressDiag.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Pilotos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    //Cargo los datos del JSONArray a arrayList de pilotos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectRowCampeonato = jsonArray.getJSONObject(i);


                        //Datos de puntaje
                        String idRowCampeonato = jsonObjectRowCampeonato.optString("_id");
                        int posicion = jsonObjectRowCampeonato.optInt("posicion");
                        String primeraFecha = jsonObjectRowCampeonato.optString("1");
                        String segundaFecha = jsonObjectRowCampeonato.optString("2");
                        String terceraFecha = jsonObjectRowCampeonato.optString("3");
                        String cuartaFecha = jsonObjectRowCampeonato.optString("4");
                        String quintaFecha = jsonObjectRowCampeonato.optString("5");
                        String sextaFecha = jsonObjectRowCampeonato.optString("6");
                        String septimaFecha = jsonObjectRowCampeonato.optString("7");
                        String octavaFecha = jsonObjectRowCampeonato.optString("8");
                        String novenaFecha = jsonObjectRowCampeonato.optString("9");
                        String decimaFecha = jsonObjectRowCampeonato.optString("10");

                        RowCampeonato rc = new RowCampeonato(idRowCampeonato,posicion,primeraFecha,segundaFecha);



                        //Piloto
                        /*
                        JSONArray jsonArray2 = jsonObject.getJSONArray("piloto");
                        String apellido = jsonObjectPiloto.optString("apellido");
                        String fNac = jsonObjectPiloto.optString("fNac");
                        int numero = jsonObjectPiloto.optInt("numero");
                        String marcaAuto = jsonObjectPiloto.optString("marcaAuto");
                        String equipo = jsonObjectPiloto.optString("equipo");
                        String nacionalidad = jsonObjectPiloto.optString("nacionalidad");
                        int puntos = jsonObjectPiloto.optInt("puntos");
                        int podios = jsonObjectPiloto.optInt("podios");
                        int campeonatos = jsonObjectPiloto.optInt("campeonatos");
                        String idDrawable = jsonObjectPiloto.optString("idDrawable");
                        //new DownloadImage().execute("http://www.mividanueva.org/images/flores.png");


                        //Date ffNac = formatter.parse(fNac);

                        Date ffNac = new Date();
                        Piloto p = new Piloto(idPiloto, nombre, apellido, ffNac, numero, marcaAuto, equipo, nacionalidad, puntos, podios, campeonatos, idDrawable);
                        Log.e("piloto:", p.getNombre() + " " + p.getApellido());*/
                        Log.e("pos: ", Integer.toString(posicion));
                        LRows.add(rc);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }
                adaptador = new AdaptadorCampeonato(LRows);
                reciclador.setAdapter(adaptador);
                progressDiag.dismiss();
                //viewPilotos();
            }
        }
    }


    /*private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ProgressDialog mProgressDialog ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Download Image Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            imagenPiloto = result;
            //image.setImageBitmap(result);
            // Close progressdialog
            mProgressDialog.dismiss();
        }
    }*/
}


