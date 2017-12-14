package com.chaskify.android.store;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.R;
import com.chaskify.domain.model.Credentials;

/**
 * Created by alberto on 13/12/17.
 */

public class AccountStorage {
    private Context mContext;

    private AccountManager mAccountManager;

    public AccountStorage(Context mContext) {
        this.mContext = mContext;
        mAccountManager = AccountManager.get(mContext);
    }

    public boolean insert(Credentials credentials) {
        Optional<Account> account = Stream.of(mAccountManager.getAccountsByType(mContext.getString(R.string.ACCOUNT_TYPE)))
                .filter(value -> value.name.equals(credentials.getUsername()))
                .findFirst();
        if (!account.isPresent())
            return mAccountManager.addAccountExplicitly(new Account(credentials.getUsername()
                            , mContext.getString(R.string.ACCOUNT_TYPE))
                    , ""
                    , null);
        return true;
    }

    public String getDefaultAccount() {
        return "";

    }
}
