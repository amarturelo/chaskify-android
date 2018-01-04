package com.chaskify.android.ui.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.AbstractSwipeBackActivity;

public class VehicleActivity extends AbstractSwipeBackActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_vehicle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, VehicleActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId())
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
