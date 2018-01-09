package com.chaskify.android.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.CredentialsCacheItemModel;
import com.chaskify.android.ui.model.ProfileItemModel;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.RealmProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;

/**
 * Created by alberto on 8/01/18.
 */

public class CredentialsCacheItemViewHolder extends RecyclerView.ViewHolder implements CredentialsCacheItemContract.View {

    public ImageView mProfileImage;
    public TextView mProfileUsername;
    public TextView mProfileTeamName;
    public View mActionMenu;

    private CredentialsCacheItemModel mCredentialsCacheItemModel;

    private CredentialsCacheItemPresenter presenter;

    public CredentialsCacheItemViewHolder(View itemView) {
        super(itemView);
        mProfileImage = itemView.findViewById(R.id.profile_image);
        mProfileUsername = itemView.findViewById(R.id.profile_username);
        mProfileTeamName = itemView.findViewById(R.id.profile_team_name);
        mActionMenu = itemView.findViewById(R.id.action_menu);
        presenter = new CredentialsCacheItemPresenter(
                new ProfileInteractor(
                        new RealmProfileRepositoryImpl(
                                new DiskProfileDataStore(
                                        new ProfileCacheImpl()
                                )
                        )
                ));
    }

    public void onBind(CredentialsCacheItemModel itemModel) {
        presenter.release();

        this.mCredentialsCacheItemModel = itemModel;
        presenter.bindView(this);
        mProfileUsername.setText(itemModel.getDriverUsername());
        presenter.profile(itemModel.getDriverId());
    }

    public CredentialsCacheItemModel getCredentialsCacheItemModel() {
        return mCredentialsCacheItemModel;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void renderProfile(ProfileItemModel profileItemModel) {
        mProfileTeamName.setText(profileItemModel.getmTeamName());

        Glide.with(itemView.getContext())
                .load(profileItemModel.getmProfileImage())
                .into(mProfileImage);
    }
}
