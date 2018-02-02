package com.chaskify.android.ui.fragments.settings;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.ListPreference;
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
import com.chaskify.android.helper.LocaleManager;
import com.chaskify.android.helper.ToastIfError;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.rx.RxImageToBase64;
import com.chaskify.android.service.ChaskifyService;
import com.chaskify.android.ui.activities.LaunchActivity;
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

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    private ListPreference mPreferenceLanguage;

    private SwitchPreference mPreferencePushNotifications;

    private ListPreference mPreferenceNotificationsSound;

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
            summarySoundUpdate((String) newValue);
            return true;
        });

        mPreferenceLanguage.setOnPreferenceChangeListener((preference, newValue) -> {
            setNewLocale((String) newValue);
            summaryLanguageUpdate((String) newValue);
            return true;
        });
    }

    private void setNewLocale(String language) {
        LocaleManager.setNewLocale(getActivity(), language);

        /*Intent i = new Intent(getActivity(), LaunchActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        if (restartProcess) {
            System.exit(0);
        } else {
            Toast.makeText(getActivity(), "Activity restarted", Toast.LENGTH_SHORT).show();
        }*/

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_language_change)
                .setMessage(R.string.message_language_change)
                .setPositiveButton(R.string.action_positive_change_language, (dialog, which) -> restartApp())
                .setNegativeButton(R.string.action_negative_change_language, (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void restartApp() {
        Intent i = new Intent(getActivity(), LaunchActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    private void initComponents() {
        mPreferenceEmail = findPreference(getResources().getString(R.string.key_preference_driver_mail));
        mPreferenceContact = findPreference(getResources().getString(R.string.key_preference_driver_phone));
        mPreferenceVehicleType = findPreference(getResources().getString(R.string.key_preference_vehicle_type));
        mPreferenceVehicleDescription = findPreference(getResources().getString(R.string.key_preference_vehicle_description));
        mPreferenceVehicleLicense = findPreference(getResources().getString(R.string.key_preference_vehicle_license));
        mPreferenceVehicleColor = findPreference(getResources().getString(R.string.key_preference_vehicle_color));
        mPreferencePushNotifications = (SwitchPreference) findPreference(getResources().getString(R.string.key_preference_enable_push));
        mPreferenceNotificationsSound = (ListPreference) findPreference(getResources().getString(R.string.key_preference_notifications_sound));
        summarySoundUpdate(null);
        mPreferenceLanguage = (ListPreference) findPreference(getResources().getString(R.string.key_preference_language));
        summaryLanguageUpdate(null);

        mProfilePreferenceWidget = (ProfilePreferenceWidget) findPreference(getResources().getString(R.string.key_preference_profile));
    }

    private void summaryLanguageUpdate(String newValue) {
        for (int i = 0; i < mPreferenceLanguage.getEntryValues().length; i++) {
            if (mPreferenceLanguage.getEntryValues()[i].equals(newValue != null ? newValue : mPreferenceLanguage.getValue())) {
                mPreferenceLanguage.setSummary(mPreferenceLanguage.getEntries()[i]);
            }
        }
    }

    private void summarySoundUpdate(String newValue) {
        for (int i = 0; i < mPreferenceNotificationsSound.getEntryValues().length; i++) {
            if (mPreferenceNotificationsSound.getEntryValues()[i].equals(newValue != null ? newValue : mPreferenceNotificationsSound.getValue())) {
                mPreferenceNotificationsSound.setSummary(mPreferenceNotificationsSound.getEntries()[i]);
            }
        }
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
        if (mListener != null)
            mListener.goToLauncher();
    }

    @Override
    public void complete() {
    }

    @Override
    public void showError(Throwable throwable) {
        ToastIfError.showError(getActivity(), (Exception) throwable);
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
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> presenter.updateImageProfile(s)
                        , throwable -> ToastIfError.showError(getActivity(), (Exception) throwable));
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

        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {

            }
        });

        new RxPermissions(getActivity())
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .filter(granted -> granted)
                .flatMap(aBoolean -> RxGallery.photoCapture(getActivity()).toObservable())
                .filter(uri -> uri != null)
                .map(uri -> {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    return bitmap;
                })
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> presenter.updateImageProfile(s), throwable -> ToastIfError.showError(getActivity(), (Exception) throwable));
    }

}
