package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.chaskify.android.R;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.adapters.viewholder.ProfileItemViewHolder;
import com.chaskify.android.ui.model.ProfileItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 8/01/18.
 */

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileItemViewHolder> {

    private List<ProfileItemModel> mProfileItemModels;

    public ProfileListAdapter() {
        mProfileItemModels = new ArrayList<>();
    }

    private ProfileItemViewHolder.OnProfileItemListened onListened;

    @Override
    public ProfileItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_cache_item_view, parent, false);
        return new ProfileItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileItemViewHolder holder, int position) {
        if (holder.getProfileItemModel() == null || !holder.getProfileItemModel().equals(mProfileItemModels.get(position)))
            holder.onBind(mProfileItemModels.get(position));
        holder.setListened(onListened);
    }

    public void setOnListened(ProfileItemViewHolder.OnProfileItemListened onListened) {
        this.onListened = onListened;
    }

    @Override
    public int getItemCount() {
        return mProfileItemModels.size();
    }

    public void render(List<ProfileItemModel> listCacheModels) {
        this.mProfileItemModels.clear();
        this.mProfileItemModels.addAll(listCacheModels);
        notifyDataSetChanged();
    }

    public ProfileItemModel getItem(int position) {
        return mProfileItemModels.get(position);
    }

    public void remove(String driverId) {
        Stream.of(mProfileItemModels)
                .filter(value -> value.getDriverId().equals(driverId))
                .findFirst()
                .ifPresent(profileItemModel -> {
                    int index = mProfileItemModels.lastIndexOf(profileItemModel);
                    mProfileItemModels.remove(index);
                    notifyItemRemoved(index);
                });

    }
}
