package com.chaskify.android.ui.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseActivity;

public class SettingsProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_settings_profile;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SettingsProfileActivity.class);
    }
}
