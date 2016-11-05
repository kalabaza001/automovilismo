package com.ort.automovilismo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Circuito;
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


public class FragmentoCircuitos extends Fragment {
    private RecyclerView reciclador;
    private AdaptadorCircuito adaptador;
    private ProgressDialog progressDiag;
    private Button botonIr;


    public FragmentoCircuitos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Consumo servicio
        View view = inflater.inflate(R.layout.fragmento_circuitos, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
       // new GetDataTask(getActivity()).execute("http://10.0.2.2:8080/circuitos");
      //  new GetDataTask(getActivity()).execute("http://192.168.2.106:8080/circuitos");
        new GetDataTask(getActivity()).execute(Utils.getServidor() + "circuitos");
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
                List<Circuito> listaCircuitos = new ArrayList<>();
                try {
                    progressDiag.dismiss();
                    // Instacio el JSON Object
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Circuitos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    //Cargo los datos del JSONArray a arrayList de circuitos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectCircuito = jsonArray.getJSONObject(i);

                        //Datos de circuito
                        String idRowCircuito = jsonObjectCircuito.optString("_id");
                        String nombre = jsonObjectCircuito.optString("nombre");
                        String numero = jsonObjectCircuito.optString("numero");
                        int largo = jsonObjectCircuito.optInt("largo");
                        int curvas = jsonObjectCircuito.optInt("curvas");
                        String record = jsonObjectCircuito.optString("record");
                        String historia = jsonObjectCircuito.optString("historia");
                        String idDrawable = jsonObjectCircuito.optString("idDrawable");
                        String latitud = jsonObjectCircuito.optString("latitud");
                        String longitud = jsonObjectCircuito.optString("longitud");

                        double auxLatitud = Double.parseDouble(latitud);
                        double auxLongitud = Double.parseDouble(longitud);


                        //Circuito
                        Circuito circuito = new Circuito(idRowCircuito, nombre, numero, largo, curvas, record, historia, idDrawable, auxLatitud, auxLongitud);
                        Log.d("Circuito: ", circuito.getNombre() + " " + circuito.getNumero());
                        listaCircuitos.add(circuito);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }
                adaptador = new AdaptadorCircuito(listaCircuitos);
                adaptador.setListener(new RecyclerClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // getFragmentManager().beginTransaction().replace(R.id.contenedor_principal, FragmentoPilotos.newInstance()).addToBackStack(null).commit();


                        // Create fragment and give it an argument for the selected article
                        FragmentoCircuito newFragment = new FragmentoCircuito();
                        Bundle args = new Bundle();
                       //Paso los datos al otro fragment
                        Log.d("ANTES DE MANDAR: ", String.valueOf(position));
                        Circuito circuito = adaptador.getListaCircuitos().get(position);
                        args.putSerializable(FragmentoCircuito.CIRCUITO, circuito);
                        newFragment.setArguments(args);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack so the user can navigate back
                        transaction.replace(R.id.contenedor_principal, newFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();

                        //   Toast.makeText(getContext(), adaptador.getRowCampeonatoList().get(position).getPosicion()+"", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(), adaptador.getRowCampeonatoList().get(position).getPosicion() + " - " + adaptador.getRowCampeonatoList().get(position).getPiloto().getApellido(), Toast.LENGTH_LONG).show();

                      //  Intent intent = new Intent(getActivity(), ActividadPrincipal.class);
                      //  startActivity(intent);

                    }
                });
                reciclador.setAdapter(adaptador);
                progressDiag.dismiss();



            }
        }
    }
}


