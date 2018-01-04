package com.chaskify.android.ui.fragments.settings;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.android.ui.widget.ProfilePreferenceWidget;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;

import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsProfileFragment extends PreferenceFragment implements SettingsProfileContract.View {

    public SettingsProfileFragment() {
    }

    private SettingsProfilePresenter presenter;

    private Preference mPreferenceEmail;
    private Preference mPreferenceContact;


    private Preference mPreferenceVehicleType;
    private Preference mPreferenceVehicleDescription;
    private Preference mPreferenceVehicleLicense;
    private Preference mPreferenceVehicleColor;

    private ProfilePreferenceWidget mProfilePreferenceWidget;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        presenter = new SettingsProfilePresenter(
                new ProfileInteractor(
                        new ProfileRepositoryImpl(
                                new DiskProfileDataStore(
                                        new ProfileCacheImpl()
                                )
                                , new CloudProfileDataStore(
                                Chaskify.getInstance()
                                        .getDefaultSession()
                                        .get().getProfileRestClient()
                                , new ProfileCacheImpl()
                        )
                        )
                )
                , Chaskify.getInstance()
                .getDefaultSession()
                .get()
        );

        presenter.bindView(this);

        addPreferencesFromResource(R.xml.preference_profile_preferences);
        initComponents();

        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        presenter.profile(Chaskify.getInstance()
                .getDefaultSession()
                .get()
                .getCredentials()
                .getDriverId());
    }

    private void initComponents() {
        mPreferenceEmail = findPreference(getResources().getString(R.string.key_preference_driver_mail));
        mPreferenceContact = findPreference(getResources().getString(R.string.key_preference_driver_phone));
        mPreferenceContact.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfile(newValue.toString());
            return false;
        });
        mPreferenceVehicleType = findPreference(getResources().getString(R.string.key_preference_vehicle_type));
        mPreferenceVehicleType.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(newValue.toString()
                    , mPreferenceVehicleDescription.getSummary().toString()
                    , mPreferenceVehicleLicense.getSummary().toString()
                    , mPreferenceVehicleColor.getSummary().toString());
            return false;
        });
        mPreferenceVehicleDescription = findPreference(getResources().getString(R.string.key_preference_vehicle_description));
        mPreferenceVehicleDescription.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(mPreferenceVehicleType.getSummary().toString()
                    , newValue.toString()
                    , mPreferenceVehicleLicense.getSummary().toString()
                    , mPreferenceVehicleColor.getSummary().toString());
            return false;
        });
        mPreferenceVehicleLicense = findPreference(getResources().getString(R.string.key_preference_vehicle_license));
        mPreferenceVehicleLicense.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(mPreferenceVehicleType.getSummary().toString()
                    , mPreferenceVehicleDescription.getSummary().toString()
                    , newValue.toString()
                    , mPreferenceVehicleColor.getSummary().toString());
            return false;
        });
        mPreferenceVehicleColor = findPreference(getResources().getString(R.string.key_preference_vehicle_color));
        mPreferenceVehicleColor.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(mPreferenceVehicleType.getSummary().toString()
                    , mPreferenceVehicleDescription.getSummary().toString()
                    , mPreferenceVehicleLicense.getSummary().toString()
                    , newValue.toString());
            return false;
        });

        mProfilePreferenceWidget = (ProfilePreferenceWidget) findPreference(getResources().getString(R.string.key_preference_profile));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void complete() {
        Toast.makeText(getActivity(), "Update complete", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(Throwable throwable) {
        Timber.d(throwable);
        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void renderProfile(ProfileModel profileModel) {
        mPreferenceEmail.setSummary(profileModel.getEmail());
        mPreferenceContact.setSummary(profileModel.getPhone());
        mPreferenceVehicleType.setSummary(profileModel.getTransportTypeId());
        mPreferenceVehicleDescription.setSummary(profileModel.getTransportDescription());
        mPreferenceVehicleLicense.setSummary(profileModel.getLicencePlate());
        mPreferenceVehicleColor.setSummary(profileModel.getColor());

        mProfilePreferenceWidget.setProfileWidgetModel(profileModel);
    }
}
