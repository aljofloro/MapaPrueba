package pe.gob.munilabaya.mapaprueba;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private final LatLng UPT = new LatLng(-18.0048043, -70.2261402);


    //dibujar rutas
    private static final PolylineOptions POLILINEA = new PolylineOptions()
            .add(new LatLng(-18.000983, -70.234734))
            .add(new LatLng(-18.002034, -70.234080))
            .add(new LatLng(-18.003646, -70.232846))
            .add(new LatLng(-18.005013, -70.231440))
            .add(new LatLng(-18.007217, -70.229541))
            .add(new LatLng(-18.007626, -70.228994))
            .add(new LatLng(-18.005360, -70.225668))
            .add(new LatLng(-18.004871, -70.226129));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


    }

    public void drawPolyline(PolylineOptions options){
        Polyline polyline = mMap.addPolyline(options);
        polyline.setColor(Color.BLUE);
    }

    public void moveCamera(View view) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UPT));
    }

    public void animateCamera(View view) {
        if (mMap.getMyLocation() != null)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng( mMap.getMyLocation().getLatitude(),
                            mMap.getMyLocation().getLongitude()), 15));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.getUiSettings().setTiltGesturesEnabled(true);
                mMap.setMyLocationEnabled(true);
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UPT,18));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setCompassEnabled(true);
                mMap.addMarker(new MarkerOptions().position(UPT).title("UPT").snippet("Universidad Privada de Tacna")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).anchor(0.5f,0.5f));
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    }
                });

                mMap.setOnMapLongClickListener( new ClickLongListener(mMap));

                setUpMap();
                drawPolyline(POLILINEA);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

}
