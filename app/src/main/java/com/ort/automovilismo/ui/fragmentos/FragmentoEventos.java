package com.ort.automovilismo.ui.fragmentos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ort.automovilismo.R;
import com.ort.automovilismo.modelo.Actividad;
import com.ort.automovilismo.modelo.Circuito;
import com.ort.automovilismo.modelo.Evento;
import com.ort.automovilismo.modelo.ResultadoEvento;
import com.ort.automovilismo.modelo.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentoEventos extends Fragment {
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Evento> ListaEventos = new ArrayList<Evento>();
    private ProgressDialog progressDiag;

    public FragmentoEventos() {
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetDataTask(getActivity()).execute(Utils.getServidor() + "eventos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progressDiag = new ProgressDialog(getActivity());
        progressDiag.setMessage("loading");
        progressDiag.show();
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);
        }
        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar);

        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);
    }

    private void poblarViewPager() {
        viewPager = (ViewPager) getView().findViewById(R.id.pager);

        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        for (int i = 0; i < ListaEventos.size(); i++) {
            adapter.addFragment(FragmentoEvento.nuevaInstancia(ListaEventos.get(i)), (i + 1) + "");

        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBarLayout.removeView(tabLayout);
    }

    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }

    //Voy a buscar al servicio todas las Eventos
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
                    JSONObject jsonObject = new JSONObject(result);

                    //Instancio el array de Pilotos en JSON
                    JSONArray jsonArray = jsonObject.getJSONArray("desc");

                    //Cargo los datos del JSONArray a arrayList de pilotos
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectEvento = jsonArray.getJSONObject(i);

                        //Datos de evento
                        int numeroEvento = jsonObjectEvento.optInt("numero");
                        String idEvento = jsonObjectEvento.optString("_id");
                        String titulo = jsonObjectEvento.optString("titulo");
                        String sFecha = jsonObjectEvento.optString("fechas");

                        //Obtengo circuito
                        JSONObject jsonObjectCitcuito = jsonObjectEvento.getJSONObject("circuito");
                        String nombreCircuito = jsonObjectCitcuito.optString("nombre");
                        String numeroCircuito = jsonObjectCitcuito.optString("numero");
                        double longitudCircuito = jsonObjectCitcuito.optDouble("longitud");
                        double latitudCircuito = jsonObjectCitcuito.optDouble("latitud");
                        double largoCircuito = jsonObjectCitcuito.optDouble("largo");
                        int curvasCircuito = jsonObjectCitcuito.optInt("curvas");
                        String recordCircuito = jsonObjectCitcuito.optString("record");
                        String historiaCircuito = jsonObjectCitcuito.optString("historia");
                        String idDrawableCircuito = jsonObjectCitcuito.optString("idDrawable");
                        //Circuito circuito = new Circuito(nombreCircuito, numeroCircuito, longitudCircuito, curvasCircuito, recordCircuito, historiaCircuito, idDrawableCircuito);
                        Circuito circuito = new Circuito(nombreCircuito, numeroCircuito, largoCircuito,  curvasCircuito, recordCircuito,historiaCircuito, idDrawableCircuito, latitudCircuito, longitudCircuito);

                        //Obtengo las actividades
                        ArrayList<Actividad> listaActividad = new ArrayList<Actividad>();
                        JSONArray jsonArrayActividad = jsonObjectEvento.getJSONArray("actividades");
                        for (int j = 0; j < jsonArrayActividad.length(); j++) {
                            JSONObject jsonObjectActividad = jsonArrayActividad.getJSONObject(j);
                            String tituloAct = jsonObjectActividad.optString("actividad");
                            String horaDH = jsonObjectActividad.optString("hora");
                            Actividad actividad = new Actividad(tituloAct, horaDH);
                            listaActividad.add(actividad);
                        }

                        //Obtengo los resultados (Tener cuidado que el orden de los resultados es el orden de posicion OJO)
                        ArrayList<ResultadoEvento> listaResultado = new ArrayList<ResultadoEvento>();
                        JSONArray jsonArrayResultados = jsonObjectEvento.getJSONArray("resultadosCarrera");
                        for (int b = 0; b < jsonArrayResultados.length(); b++) {
                            JSONObject jsonObjectResultado = jsonArrayResultados.getJSONObject(b);
                            int pos = b + 1;
                            int numero = jsonObjectResultado.optInt("numero");
                            String nombrePiloto = jsonObjectResultado.optString("nombre");
                            ResultadoEvento resultado = new ResultadoEvento(pos, numero, nombrePiloto);
                            listaResultado.add(resultado);
                        }
                        Evento evento = new Evento(numeroEvento, idEvento, titulo, sFecha, circuito, listaActividad, listaResultado);

                        Log.d("Eventos:", evento.toString());
                        ListaEventos.add(evento);
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getMessage());
                }

                poblarViewPager();
                progressDiag.dismiss();
            }

        }
    }
}
