package com.chaskify.android.ui.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.activities.NotificationsActivity;
import com.chaskify.android.ui.base.AbstractSwipeBackActivity;
import com.chaskify.android.ui.base.BaseActivity;
import com.chaskify.android.ui.fragments.ChangePasswordDialogFragment;
import com.chaskify.android.ui.fragments.settings.SettingsProfileFragment;

public class SettingsProfileActivity extends AbstractSwipeBackActivity implements SettingsProfileFragment.ListenerInteractorFragment {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void goToLauncher() {
        Navigator.goToLaunchActivity(this);
    }
}
