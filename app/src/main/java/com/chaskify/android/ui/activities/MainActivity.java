package com.chaskify.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.ui.fragments.TaskMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Toolbar toolbar;
    private GoogleMap mMap;
    String json = "({\"code\":1,\"msg\":\"Autenticaci\\u00f3n Satisfactoria\",\"details\":{\"username\":\"amarturelo\",\"password\":\"demo\",\"remember\":\"\",\"todays_date\":\"Dic, 07\",\"on_duty\":1,\"token\":\"50e6136270e33c7ce418e9c9581b4d18\",\"duty_status\":\"1\",\"location_accuracy\":1,\"icons\":{\"logo_company\":\"https:\\/\\/s3-us-west-2.amazonaws.com\\/chaskify-backend-uploads\\/logo_company-42261060377.png\",\"logo_customer_location\":\"logo_customer_location-GAKSJMKC86PUZVGWQV98.png\",\"driver_icon_offline\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/driver-offline.png\",\"logo_driver\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_driver.png\",\"logo_driver_route\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_driver_route.png\",\"logo_delivery_location\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_delivery_location.png\",\"logo_completed_delivery\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_completed_delivery.png\",\"delivery_icon_failed\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/delivery_icon_failed.png\",\"logo_pickup_location\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_pickup_location.png\",\"logo_completed_pickup\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_completed_pickup.png\",\"pickup_icon_failed\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/delivery_icon_failed.png\",\"logo_service_location\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_service_location.png\",\"logo_completed_service\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/logo_completed_service.png\",\"service_icon_failed\":\"http:\\/\\/customer.chaskify.com\\/assets\\/images\\/delivery_icon_failed.png\"}}})\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();

        initSpinner();

        initActivity(savedInstanceState);

        /*// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

        /*LoginRestClient loginRestClient = new LoginRestClient(null);
        loginRestClient.loginWithUser("amarturelo", "demo", new ApiCallback<Credentials>() {
            @Override
            public void onSuccess(Credentials info) {

            }

            @Override
            public void onNetworkError(Exception e) {

            }

            @Override
            public void onMatrixError(ChaskifyError e) {

            }

            @Override
            public void onUnexpectedError(Exception e) {

            }
        });*/

    }

    private void initActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, TaskMapFragment.newInstance())
                    .commit();
    }

    private void initSpinner() {
        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext()
                , new String[]{
                "NOV, 05",
        }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_notification) {
            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }

}
