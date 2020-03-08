package com.chaskify.domain.interactors;

import com.annimon.stream.Optional;
import com.chaskify.domain.model.Settings;
import com.chaskify.domain.repositories.SettingsRepository;

import io.reactivex.Flowable;

/**
 * Created by alberto on 10/01/18.
 */

public class SettingsInteractor {

    private SettingsRepository settingsRepository;

    public SettingsInteractor(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Flowable<Optional<Settings>> settingsByDriverId(String driverId) {
        return settingsRepository.settingsByDriverId(driverId);
    }
}
