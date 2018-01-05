package com.chaskify.android.ui.fragments.settings;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.android.ui.widget.ProfilePreferenceWidget;
import com.chaskify.chaskify_sdk.crypto.Base64;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.marchinram.rxgallery.RxGallery;

import java.io.ByteArrayOutputStream;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsProfileFragment extends PreferenceFragment implements SettingsProfileContract.View, ProfilePreferenceWidget.Listened {

    private static final int PHOTO_SELECTED = 100;

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
        Timber.tag(this.getClass().getSimpleName());

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
        mProfilePreferenceWidget.setListened(this);
    }

    @Override
    public void onClickImage() {
        pickImageDialog();
    }

    private void pickImageDialog() {
        new AlertDialog.Builder(getActivity())
                .setItems(R.array.picker_image_values, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            pickFromCamera();
                            break;
                        case 1:
                            pickFromGallery();
                            break;
                    }
                })
                .show();
    }

    private void pickFromGallery() {
        Timber.d("::pickFromGallery");

        RxGallery.gallery(getActivity(), false, RxGallery.MimeType.IMAGE)
                .filter(uris -> !uris.isEmpty())
                .map(new Function<List<Uri>, Bitmap>() {
                    @Override
                    public Bitmap apply(List<Uri> uris) throws Exception {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Stream.of(uris).findFirst().get());
                        return bitmap;
                    }
                })
                .map(new Function<Bitmap, String>() {
                    @Override
                    public String apply(Bitmap bitmap) throws Exception {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();

                        return Base64.encodeBytes(byteArray);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        presenter.updateImageProfile(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void pickFromCamera() {
        Timber.d("::pickFromCamera");
        RxGallery.photoCapture(getActivity())
                .filter(uri -> uri != null)
                .map(uri -> {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    return bitmap;
                })
                .map(bitmap -> {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    return Base64.encodeBytes(byteArray);
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        presenter.updateImageProfile(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}
