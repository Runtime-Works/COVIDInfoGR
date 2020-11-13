package com.example.b10z.covidinfo;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;


import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Boolean mLocationPermissionsGranted = false;
    private HTTPHandler conHandler;
    private GeoJsonLayer layer;
    private Marker mMarker;
    private LatLng user_touch;
    private TextView area, population, cases, deaths, new_deaths, new_cases;
    private Button drawer_bringer;
    private RelativeLayout relativeLayout;
    private ArrayList<String[]> greeceDataHolderTimeline, greeceDataHolderDeaths, PDFParseHolder, greeceDataHolderCases,globalStatsDataHolder;
    private ArrayList<String[]> greeceDataHolder;

    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        area = (TextView) findViewById(R.id.area);
        area.setVisibility(View.INVISIBLE);
        population = (TextView) findViewById(R.id.population);
        population.setVisibility(View.INVISIBLE);
        cases = (TextView) findViewById(R.id.cases);
        cases.setVisibility(View.INVISIBLE);
        deaths = (TextView) findViewById(R.id.deaths);
        deaths.setVisibility(View.INVISIBLE);
        new_cases = (TextView) findViewById(R.id.new_cases);
        new_cases.setVisibility(View.INVISIBLE);
        new_deaths = (TextView) findViewById(R.id.new_deaths);
        new_deaths.setVisibility(View.INVISIBLE);

        drawer_bringer = (Button) findViewById(R.id.frag_butt);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().removeItem(R.id.greece_map);



        relativeLayout = (RelativeLayout) findViewById(R.id.mapLayout);
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(MapsActivity.this) {
            public void onSwipeTop() {
                Intent intent = new Intent(MapsActivity.this, GraphsActivity.class);
                if(greeceDataHolderTimeline!=null && greeceDataHolderCases != null) {
                    intent.putExtra("CasesData" , greeceDataHolder);
                    intent.putExtra("TimelineData", greeceDataHolderTimeline);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                Toast.makeText(MapsActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                System.out.println("SWIPE");
                Intent intent = new Intent(MapsActivity.this, StatsActivity.class);
                if(globalStatsDataHolder!=null) {
                    System.out.println("NON NULL");
                    intent.putExtra("GlobalStatsData", globalStatsDataHolder);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

        });

        drawer_bringer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                drawer.openDrawer(Gravity.LEFT);
            }

        });

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {

            }

            @Override
            public void onDrawerOpened(View view) {

            }

            @Override
            public void onDrawerClosed(View view) {
                // your refresh code can be called from here
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });


        conHandler = new HTTPHandler(this); //new temp thread
        conHandler.execute(); //execute


        initMap();

    }
    //Listener gia Taps sto NavView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.world_stats:
                Intent intentStats = new Intent(MapsActivity.this, StatsActivity.class);
                if(globalStatsDataHolder!=null) {
                    System.out.println("NON NULL");
                    intentStats.putExtra("GlobalStatsData", globalStatsDataHolder);
                }
                startActivity(intentStats);
                //finish();
                break;
            case R.id.greece_stats:
                Intent intentGraphs = new Intent(MapsActivity.this, GraphsActivity.class);
                if(greeceDataHolderTimeline!=null && greeceDataHolderCases != null) {
                    intentGraphs.putExtra("CasesData" , greeceDataHolder);
                    intentGraphs.putExtra("TimelineData", greeceDataHolderTimeline);
                }
                startActivity(intentGraphs);
                //finish();
                break;
        }
        return true;
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapsActivity.this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;


            try{
                //Change map style to be transparent so when GeoJson layer is added it will only show that
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_trans));
                //TODO:FIX THIS:
                layer = new GeoJsonLayer(mMap, R.raw.merged1111, getApplicationContext()); //there might be something wrong with the file that corrupts the actionlistener (bellow)
                Log.d(TAG, "onGeoJson: file ready");  // res/raw/greece is the correct geoJson file. Non compressed.

                GeoJsonPolygonStyle polyStyle = layer.getDefaultPolygonStyle(); //loading polygons
                polyStyle.setStrokeColor(Color.parseColor("#0466C8"));

                polyStyle.setStrokeWidth(4);


                layer.addLayerToMap();

                Log.d(TAG, "onGeoJson: "+ layer.isLayerOnMap());



            } catch(IOException e){
                System.out.println("error1");

            }catch (JSONException e){
                System.out.println(e);
            }

            //Country's Coords to fixate position
            LatLngBounds GREECE = new LatLngBounds(new LatLng(34.91,  22.50), new LatLng( 41.82,26.60));
            mMap.setLatLngBoundsForCameraTarget(GREECE);
            //map locks and restrictions
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GREECE.getCenter(), 5.8f));
            mMap.getUiSettings().setZoomGesturesEnabled(false);
            mMap.getUiSettings().setZoomGesturesEnabled(false);
            mMap.getUiSettings().setAllGesturesEnabled(false);
            mMap.getUiSettings().setCompassEnabled(false);
            mMap.getUiSettings().setScrollGesturesEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

    }





    public class HTTPHandler extends AsyncTask<Void, Integer, String> { //For more information about AsyncTask thread goto dev.google.com


        private HttpsURLConnection urlConnectionGreeceRegions, urlConnectionGreeceTimeline, urlConnectionGlobalStats, urlConnectionGreeceCases, urlConnectionGreeceDeaths, urlConnectionPDFParse;
        private static final String TAG = "HTTPHandler";
        private WeakReference<MapsActivity> activityWeakReference;


        HTTPHandler(MapsActivity activity){
            activityWeakReference = new WeakReference<MapsActivity>(activity);
        }

        private void arrayMerger(){

            if(!greeceDataHolderCases.isEmpty() && !greeceDataHolderDeaths.isEmpty()) {

                for (int i = 1; i < greeceDataHolderCases.size() - 1; i++) {
                    String[] entry = new String[7];
                    if (greeceDataHolderDeaths.get(i)[0].matches(greeceDataHolderCases.get(i)[0])) {
                        entry[0] = greeceDataHolderCases.get(i)[0];
                        entry[1] = greeceDataHolderCases.get(i)[1];
                        entry[2] = greeceDataHolderCases.get(i)[2];
                        entry[3] = greeceDataHolderCases.get(i)[greeceDataHolderCases.get(i).length - 1];
                        entry[4] = greeceDataHolderDeaths.get(i)[greeceDataHolderDeaths.get(i).length - 1];
                        entry[5] = String.valueOf((int) Float.parseFloat(greeceDataHolderCases.get(i)[greeceDataHolderCases.get(i).length - 1]) - (int) Float.parseFloat(greeceDataHolderCases.get(i)[greeceDataHolderCases.get(i).length  - 2]));
                        entry[6] = String.valueOf((int) Float.parseFloat(greeceDataHolderDeaths.get(i)[greeceDataHolderDeaths.get(i).length  - 1]) - (int) Float.parseFloat(greeceDataHolderDeaths.get(i)[greeceDataHolderDeaths.get(i).length  - 2]));

                        greeceDataHolder.add(entry);
                    }
                }
            }

        }

        private ArrayList<String[]> loadData(HttpsURLConnection connection) throws IOException { //loading data from csv
            InputStream inTimeline = new BufferedInputStream(connection.getInputStream());
            String readLine;
            String splitter[];
            ArrayList<String[]> array = new ArrayList<String[]>();

            BufferedReader brTm = new BufferedReader(new InputStreamReader(inTimeline));
            while (((readLine = brTm.readLine()) != null)) {
                splitter = readLine.split(",");
                array.add(splitter);

            }
            brTm.close();
            return array;

        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL urlGreeceRegion = new URL("https://raw.githubusercontent.com/iMEdD-Lab/open-data/master/COVID-19/greece_v2.csv"); //not used yet
                URL urlGreeceTimeline = new URL("https://raw.githubusercontent.com/iMEdD-Lab/open-data/master/COVID-19/greeceTimeline.csv");
                URL urlGreeceCases = new URL("https://raw.githubusercontent.com/iMEdD-Lab/open-data/master/COVID-19/regions_greece_cases.csv");
                URL urlGreeceDeaths = new URL("https://raw.githubusercontent.com/iMEdD-Lab/open-data/master/COVID-19/regions_greece_deaths.csv");
                URL urlPDFParse = new URL("https://raw.githubusercontent.com/iMEdD-Lab/open-data/master/COVID-19/pdfsDataFrame.csv");
                URL urlGlobalStats = new URL("https://raw.githubusercontent.com/iMEdD-Lab/open-data/master/COVID-19/wom_data.csv");

                urlConnectionGreeceRegions = (HttpsURLConnection) urlGreeceRegion.openConnection(); //establishing connection
                urlConnectionGreeceTimeline = (HttpsURLConnection) urlGreeceTimeline.openConnection();
                urlConnectionGreeceDeaths = (HttpsURLConnection) urlGreeceDeaths.openConnection();
                urlConnectionPDFParse = (HttpsURLConnection) urlPDFParse.openConnection();
                urlConnectionGreeceDeaths= (HttpsURLConnection) urlGreeceDeaths.openConnection();
                urlConnectionGreeceCases= (HttpsURLConnection) urlGreeceCases.openConnection();
                urlConnectionGlobalStats = (HttpsURLConnection) urlGlobalStats.openConnection();

                //World stats data loading
                greeceDataHolder=new ArrayList<String[]>();

                InputStream inGlobal = new BufferedInputStream(urlConnectionGlobalStats.getInputStream());
                String readLine;
                String splitter[];
                globalStatsDataHolder = new ArrayList<String[]>();

                BufferedReader brGlobal = new BufferedReader(new InputStreamReader(inGlobal));
                int outterCounter = 0;
                while (((readLine = brGlobal.readLine()) != null)) {//find and replace ,"000,000", with ,000.000,
                    if (outterCounter>0) {
                        byte[] readLineBytes = readLine.getBytes();
                        int counter = 0;
                        for (int i = 0; i < readLineBytes.length; i++) {
                            if (readLineBytes[i] == '\"' && counter == 0) {
                                readLineBytes[i] = ' ';
                                counter = 1;
                            } else if (readLineBytes[i] == '\"' && counter == 1) {
                                readLineBytes[i] = ' ';
                                counter = 0;
                            } else if (readLineBytes[i] == ',' && counter == 1) {
                                readLineBytes[i] = '.';
                                //counter = -1;
                            }
                        }
                        String newReadLine = new String(readLineBytes);

                        splitter = newReadLine.split(",", -1);
                        //Log.d(TAG, Arrays.toString(splitter));
                        globalStatsDataHolder.add(splitter);
                    }
                    outterCounter++;
                }

                //rest of the data loading
                greeceDataHolderTimeline = loadData(urlConnectionGreeceTimeline);
                greeceDataHolderCases=loadData(urlConnectionGreeceCases);
                greeceDataHolderDeaths=loadData(urlConnectionGreeceDeaths);

                arrayMerger();

                //Format globalStatsData
                for(int i=0; i< globalStatsDataHolder.size();i++){
                    if (!globalStatsDataHolder.get(i)[2].isEmpty())
                        globalStatsDataHolder.get(i)[2] = String.format("%,.0f", Double.parseDouble(globalStatsDataHolder.get(i)[2]));
                    if (!globalStatsDataHolder.get(i)[4].isEmpty())
                        globalStatsDataHolder.get(i)[4] = String.format("%,.0f", Double.parseDouble(globalStatsDataHolder.get(i)[4]));
                    if (!globalStatsDataHolder.get(i)[5].isEmpty())
                        globalStatsDataHolder.get(i)[5] = String.format("%,.0f", Double.parseDouble(globalStatsDataHolder.get(i)[5]));
                }

            } catch (IOException e) {
                Log.d(TAG, e.toString());

            } finally {
                urlConnectionGreeceRegions.disconnect();
                urlConnectionGreeceTimeline.disconnect();
                urlConnectionGreeceDeaths.disconnect();
                urlConnectionPDFParse.disconnect();
            }
            return "Finished";
        }

        @Override
        protected void onPostExecute(String s) {

            MapsActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()) {
                return;
            }



                activity.layer.setOnFeatureClickListener(new GeoJsonLayer.OnFeatureClickListener() { //layer action listener
                    @Override
                    public void onFeatureClick(Feature feature) { //sometimes this listeners picks wrong features. need fix.

                        int refArea = 0;
                        int index = 0;
                        System.out.println(feature.getProperty("PER"));
                        for (String[] splitter: greeceDataHolder){
                            String tempData = splitter[0];
                            System.out.println(splitter[0]);
                            System.out.println(feature.getProperty("PER"));
                            System.out.println((feature.getProperty("PER")).matches(".*"+tempData.toUpperCase(Locale.forLanguageTag("el-GR")).replace("ΠΕΡΙΦΕΡΕΙΑ ","")+".*"));


                            if ((feature.getProperty("PER")).matches(".*"+tempData.toUpperCase(Locale.forLanguageTag("el-GR")).replace("ΠΕΡΙΦΕΡΕΙΑ ","")+".*"))
                                refArea = index;

                            index++;
                        }

                        if(refArea!=0) {
                            String selectedLocation[] = greeceDataHolder.get(refArea);

                            area.setText(selectedLocation[0]);
                            area.setVisibility(View.VISIBLE);
                            population.setText("Population: " + selectedLocation[2].replace(".0", ""));
                            population.setVisibility(View.VISIBLE);
                            cases.setText("Cases: " + selectedLocation[3].replace(".0", ""));
                            cases.setVisibility(View.VISIBLE);
                            deaths.setText("Deaths: " + selectedLocation[4].replace(".0", ""));
                            deaths.setVisibility(View.VISIBLE);
                            new_cases.setText("New Cases: " + selectedLocation[selectedLocation.length - 1].replace(".0", ""));
                            new_cases.setVisibility(View.VISIBLE);
                           // new_deaths.setText("Deaths: " + selectedLocation[4].replace(".0", ""));
                            //new_deaths.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, feature.getProperty("PER"));

                    }
                });

                        super.onPostExecute(s);
            }

        }
}
