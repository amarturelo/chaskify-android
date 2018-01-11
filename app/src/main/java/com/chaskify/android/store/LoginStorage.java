package com.chaskify.android.store;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.R;
import com.chaskify.android.account.AccountGeneral;
import com.chaskify.domain.model.Credentials;

import java.util.List;


/**
 * Created by alberto on 13/12/17.
 */

public class LoginStorage {
    private Context mContext;

    private AccountManager mAccountManager;
    private String DRIVER_ID = "driver_id";

    public LoginStorage(Context mContext) {
        this.mContext = mContext;
        mAccountManager = AccountManager.get(mContext);
    }

    public List<Credentials> getCredentials() {
        return Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .map(account -> new Credentials()
                        .setUsername(account.name)
                        .setDriverId(mAccountManager.getUserData(account, DRIVER_ID))
                        .setAccessToken(mAccountManager.peekAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS)))
                .toList();
    }

    public boolean addCredentials(Credentials credentials, String password) {
        if (!Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst()
                .isPresent()) {
            Account account = new Account(credentials.getUsername(), mContext.getString(R.string.ACCOUNT_TYPE));
            Bundle bundle = new Bundle();
            bundle.putString(DRIVER_ID, credentials.getDriverId());
            if (mAccountManager.addAccountExplicitly(account
                    , password
                    , bundle)) {
                mAccountManager.setAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, credentials.getAccessToken());
                return true;
            }
        }
        return false;
    }

    public void removeCredentials(Credentials credentials) {
        Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst()
                .ifPresent(account -> mAccountManager.removeAccount(account
                        , null
                        , null
                ));


    }

    public void replaceCredentials(Credentials credentials, String password) {
        Optional<Account> account = Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst();
        if (account.isPresent()) {
            mAccountManager.setPassword(account.get()
                    , password);
            mAccountManager.setAuthToken(account.get(), AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, credentials.getAccessToken());
        }
    }

    public void invalidateCredentials(String username) {
        Stream.of(getCredentials())
                .filter(value -> value.getUsername().equals(username))
                .forEach(credentials -> {
                    mAccountManager.invalidateAuthToken(mContext.getString(R.string.ACCOUNT_TYPE), credentials.getAccessToken());
                    mAccountManager.clearPassword(new Account(username, mContext.getString(R.string.ACCOUNT_TYPE)));
                });
    }
}
