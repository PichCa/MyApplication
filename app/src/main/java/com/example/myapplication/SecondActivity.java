package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import okhttp3.internal.Platform;

public class SecondActivity extends AppCompatActivity {

    private MapView map_view;
    private Double latitude;
    private Double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("key");
        Brewery brewery = gson.fromJson(strObj, Brewery.class);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        map_view = findViewById(R.id.mapview);
        map_view.setTileSource(TileSourceFactory.MAPNIK);
        map_view.setBuiltInZoomControls(true);
        map_view.setMultiTouchControls(true);
        IMapController mapController = map_view.getController();
        mapController.setZoom(14);
        latitude = Double.parseDouble(brewery.getLatitude());
        longitude = Double.parseDouble(brewery.getLongitude());
        GeoPoint startPoint = new GeoPoint(latitude, longitude);

        Marker startMarker = new Marker(map_view);
        startMarker.setIcon(getDrawable(R.drawable.beer));
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map_view.getOverlays().add(startMarker);

        mapController.setCenter(startPoint);
    }



}
