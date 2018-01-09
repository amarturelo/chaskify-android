package com.chaskify.android.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaskify.android.R;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.ui.model.ProfileItemModel;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.RealmProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;

/**
 * Created by alberto on 8/01/18.
 */

public class ProfileItemViewHolder extends RecyclerView.ViewHolder implements ProfileItemContract.View, View.OnClickListener {

    private OnItemListened mListened;

    public ImageView mProfileImage;
    public TextView mProfileUsername;
    public TextView mProfileTeamName;
    public View mActionMenu;

    private ProfileItemModel mProfileItemModel;

    private ProfileItemPresenter presenter;

    public ProfileItemViewHolder(View itemView) {
        super(itemView);
        mProfileImage = itemView.findViewById(R.id.profile_image);
        mProfileUsername = itemView.findViewById(R.id.profile_username);
        mProfileTeamName = itemView.findViewById(R.id.profile_team_name);
        mActionMenu = itemView.findViewById(R.id.action_menu);
        presenter = new ProfileItemPresenter(
                new ProfileInteractor(
                        new RealmProfileRepositoryImpl(
                                new DiskProfileDataStore(
                                        new ProfileCacheImpl()
                                )
                        )
                ));

        itemView.setOnClickListener(this);
    }

    public ProfileItemViewHolder setListened(OnItemListened mListened) {
        this.mListened = mListened;
        return this;
    }

    public void onBind(ProfileItemModel itemModel) {
        presenter.release();

        this.mProfileItemModel = itemModel;
        presenter.bindView(this);
        mProfileUsername.setText(itemModel.getDriverUsername());
        presenter.profile(itemModel.getDriverId());
    }

    public ProfileItemModel getProfileItemModel() {
        return mProfileItemModel;
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
        this.mProfileItemModel.setProfileImage(profileItemModel.getProfileImage());
        mProfileTeamName.setText(profileItemModel.getTeamName());
        Glide.with(itemView.getContext())
                .load(profileItemModel.getProfileImage())
                .into(mProfileImage);
    }

    @Override
    public void onClick(View v) {
        if (mListened != null)
            mListened.onClickItem(v, getAdapterPosition());
    }
}
