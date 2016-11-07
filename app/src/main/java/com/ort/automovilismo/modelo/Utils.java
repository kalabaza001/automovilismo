package com.ort.automovilismo.modelo;

/**
 * Created by andres on 17/10/16.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.InputStream;
import java.io.OutputStream;

public class    Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }

    public static boolean isGPS(Context context){
        LocationManager locManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        return locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static void guardar(Context context,String propiedad,String valor)
    {
        SharedPreferences prefs = context.getSharedPreferences("Configuracion", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(propiedad, valor);
        editor.commit();
    }

    public static String leer(Context context,String propiedad)
    {
        SharedPreferences prefs =
                context.getSharedPreferences("Configuracion", Context.MODE_PRIVATE);

        return prefs.getString(propiedad, "");
    }

    public static String getServidor(){
        return "http://10.0.2.2:8080/";
        //return "http://baremos.uy:8000/";
    }

}