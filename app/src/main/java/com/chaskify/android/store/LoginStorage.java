package com.chaskify.android.store;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
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

    public LoginStorage(Context mContext) {
        this.mContext = mContext;
        mAccountManager = AccountManager.get(mContext);
    }

    public List<Credentials> getCredentials() {
        return Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .map(account -> new Credentials()
                        .setUsername(account.name)
                        .setAccessToken(mAccountManager.peekAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS)))
                .toList();
    }

    public boolean addCredentials(Credentials credentials, String password) {
        if (!Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst()
                .isPresent()) {
            Account account = new Account(credentials.getUsername(), mContext.getString(R.string.ACCOUNT_TYPE));
            if (mAccountManager.addAccountExplicitly(account
                    , password
                    , null)) {
                mAccountManager.setAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, credentials.getAccessToken());
                return true;
            }
        }
        return false;
    }

    public void removeCredentials(Credentials credentials) {
        if (!Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst()
                .isPresent()) {
            Account account = new Account(credentials.getUsername(), mContext.getString(R.string.ACCOUNT_TYPE));
            mAccountManager.removeAccount(account
                    , null
                    , null
            );

        }
    }

    public void replaceCredentials(Credentials credentials, String password) {
        Optional<Account> account = Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst();
        if (account.isPresent()) {
            mAccountManager.setPassword(account.get()
                    , password);
            mAccountManager.setAuthToken(account.get(), mContext.getString(R.string.ACCOUNT_TYPE), credentials.getAccessToken());
        }
    }

}
