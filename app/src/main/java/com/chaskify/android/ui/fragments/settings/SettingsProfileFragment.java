package com.chaskify.android.ui.fragments.settings;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.service.ChaskifyService;
import com.chaskify.android.ui.fragments.ChangePasswordDialogFragment;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.android.ui.model.SettingsModel;
import com.chaskify.android.ui.widget.ProfilePreferenceWidget;
import com.chaskify.chaskify_sdk.crypto.Base64;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.SettingsRepositoryImpl;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;
import com.marchinram.rxgallery.RxGallery;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsProfileFragment extends PreferenceFragment implements SettingsProfileContract.View, ProfilePreferenceWidget.Listened {


    public SettingsProfileFragment() {
        Timber.tag(this.getClass().getSimpleName());
        setHasOptionsMenu(true);
    }

    private SettingsProfilePresenter presenter;

    private Preference mPreferenceEmail;
    private Preference mPreferenceContact;


    private Preference mPreferenceVehicleType;
    private Preference mPreferenceVehicleDescription;
    private Preference mPreferenceVehicleLicense;
    private Preference mPreferenceVehicleColor;

    private SwitchPreference mPreferencePushNotifications;

    private Preference mPreferenceNotificationsSound;

    private ProfilePreferenceWidget mProfilePreferenceWidget;

    public interface ListenerInteractorFragment {
        void goToLauncher();

    }

    private ListenerInteractorFragment mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(this.getClass().getSimpleName());


        addPreferencesFromResource(R.xml.preference_profile_preferences);
        initComponents();

        Chaskify.getInstance().getDefaultSession()
                .executeIfAbsent(this::goToLaunch)
                .ifPresent(chaskifySession -> {
                    presenter = new SettingsProfilePresenter(
                            new ProfileInteractor(
                                    new ProfileRepositoryImpl(
                                            new ProfileCacheImpl()
                                    )
                            )
                            , new SettingsInteractor(
                            new SettingsRepositoryImpl(
                                    new SettingsCacheImpl()
                            ))
                            , chaskifySession);
                    presenter.bindView(this);
                    initListenedPreferenceChange();

                    presenter.profile();
                    presenter.settings();
                });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListenerInteractorFragment)
            mListener = (ListenerInteractorFragment) context;
    }

    private void initListenedPreferenceChange() {
        mPreferenceContact.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfile(newValue.toString());
            return false;
        });

        mPreferenceVehicleType.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(newValue.toString()
                    , mPreferenceVehicleDescription.getSummary().toString()
                    , mPreferenceVehicleLicense.getSummary().toString()
                    , mPreferenceVehicleColor.getSummary().toString());
            return false;
        });

        mPreferenceVehicleDescription.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(mPreferenceVehicleType.getSummary().toString()
                    , newValue.toString()
                    , mPreferenceVehicleLicense.getSummary().toString()
                    , mPreferenceVehicleColor.getSummary().toString());
            return false;
        });

        mPreferenceVehicleLicense.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(mPreferenceVehicleType.getSummary().toString()
                    , mPreferenceVehicleDescription.getSummary().toString()
                    , newValue.toString()
                    , mPreferenceVehicleColor.getSummary().toString());
            return false;
        });

        mPreferenceVehicleColor.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateProfileVehicle(mPreferenceVehicleType.getSummary().toString()
                    , mPreferenceVehicleDescription.getSummary().toString()
                    , mPreferenceVehicleLicense.getSummary().toString()
                    , newValue.toString());
            return false;
        });

        mPreferencePushNotifications.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateSettingsPush((Boolean) newValue);
            return false;
        });

        mPreferenceNotificationsSound.setOnPreferenceChangeListener((preference, newValue) -> {
            presenter.updateSettingsSound((String) newValue);
            return false;
        });
    }

    private void initComponents() {
        mPreferenceEmail = findPreference(getResources().getString(R.string.key_preference_driver_mail));
        mPreferenceContact = findPreference(getResources().getString(R.string.key_preference_driver_phone));
        mPreferenceVehicleType = findPreference(getResources().getString(R.string.key_preference_vehicle_type));
        mPreferenceVehicleDescription = findPreference(getResources().getString(R.string.key_preference_vehicle_description));
        mPreferenceVehicleLicense = findPreference(getResources().getString(R.string.key_preference_vehicle_license));
        mPreferenceVehicleColor = findPreference(getResources().getString(R.string.key_preference_vehicle_color));
        mPreferencePushNotifications = (SwitchPreference) findPreference(getResources().getString(R.string.key_preference_enable_push));
        mPreferenceNotificationsSound = findPreference(getResources().getString(R.string.key_preference_notifications_sound));
        mProfilePreferenceWidget = (ProfilePreferenceWidget) findPreference(getResources().getString(R.string.key_preference_profile));
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void renderSettings(SettingsModel settingsModel) {
        mPreferencePushNotifications.setChecked(settingsModel.isEnabledPush());
    }

    @Override
    public void logoutComplete() {
        goToLaunch();
    }

    private void goToLaunch() {
        if(mListener!=null)
            mListener.goToLauncher();
    }

    @Override
    public void complete() {
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
        mPreferenceEmail.setDefaultValue(profileModel.getEmail());
        mPreferenceContact.setSummary(profileModel.getPhone());
        mPreferenceContact.setDefaultValue(profileModel.getPhone());
        mPreferenceVehicleType.setSummary(profileModel.getTransportTypeId());
        mPreferenceVehicleType.setDefaultValue(profileModel.getTransportTypeId());
        mPreferenceVehicleDescription.setSummary(profileModel.getTransportDescription());
        mPreferenceVehicleDescription.setDefaultValue(profileModel.getTransportDescription());
        mPreferenceVehicleLicense.setSummary(profileModel.getLicencePlate());
        mPreferenceVehicleLicense.setDefaultValue(profileModel.getLicencePlate());
        mPreferenceVehicleColor.setSummary(profileModel.getColor());
        mPreferenceVehicleColor.setDefaultValue(profileModel.getColor());

        mProfilePreferenceWidget.setProfileWidgetModel(profileModel);
        mProfilePreferenceWidget.setListened(this);
    }

    @Override
    public void onClickImage() {
        pickImageDialog();
    }

    private void pickImageDialog() {
        new AlertDialog.Builder(getActivity())
                .setItems(R.array.array_picker_image_values, (dialog, which) -> {
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
                .map(uris -> {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Stream.of(uris).findFirst().get());
                    return bitmap;
                })
                .map(bitmap -> {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();

                    return Base64.encodeBytes(byteArray);
                })
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .subscribe(s -> presenter.updateImageProfile(s), new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_password) {
            doChangePassword();
        }

        if (id == R.id.action_logout) {
            doLogout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.release();
    }

    private void doLogout() {
        ChaskifyService.stop(getActivity());
        presenter.logout();
    }

    private void doChangePassword() {
        ChangePasswordDialogFragment changePasswordDialogFragment = ChangePasswordDialogFragment.newInstance(Chaskify
                .getInstance()
                .getDefaultSession()
                .get()
                .getCredentials()
                .getUsername());
        changePasswordDialogFragment.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), ChangePasswordDialogFragment.class.getSimpleName());
    }

    private void pickFromCamera() {
        Timber.d("::pickFromCamera");
        new RxPermissions(getActivity())
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .filter(granted -> granted)
                .flatMap(aBoolean -> RxGallery.photoCapture(getActivity()).toObservable())
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
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .subscribe(s -> presenter.updateImageProfile(s), throwable -> Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show());
    }

}
