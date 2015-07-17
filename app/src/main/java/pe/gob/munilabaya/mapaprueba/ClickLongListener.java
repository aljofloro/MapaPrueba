package pe.gob.munilabaya.mapaprueba;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by UTI on 08/07/2015.
 */
public class ClickLongListener implements GoogleMap.OnMapLongClickListener {

    private GoogleMap mapa;

    public ClickLongListener(GoogleMap mMap){
        this.mapa = mMap;
    }
    @Override
    public void onMapLongClick(LatLng latLng) {

        Log.v("ClickLongListener latitude: ", String.valueOf(latLng.latitude));
        Log.v("ClickLongListener longitude: ", String.valueOf(latLng.longitude));

        this.mapa.addMarker(new MarkerOptions().position(latLng)
        .title("Coordenadas:")
        .snippet("Latitud: "+String.valueOf(latLng.latitude)+" Longitud: "+String.valueOf(latLng.longitude))
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


    }
}
