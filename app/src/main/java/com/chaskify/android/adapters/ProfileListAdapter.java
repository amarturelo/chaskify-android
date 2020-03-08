package com.chaskify.android.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.chaskify.android.R;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.adapters.viewholder.ProfileItemViewHolder;
import com.chaskify.android.ui.model.ProfileItemModel;
import com.chaskify.android.ui.model.TaskHistoryItemModel;

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

    public ProfileItemModel getItem(int position) {
        return mProfileItemModels.get(position);
    }

    public void update(List<ProfileItemModel> mNewList) {
        ProfileDiffCallback callback = new ProfileDiffCallback(this.mProfileItemModels, mNewList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.mProfileItemModels.clear();
        this.mProfileItemModels.addAll(mNewList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class ProfileDiffCallback extends DiffUtil.Callback {
        private List<ProfileItemModel> mOldList;
        private List<ProfileItemModel> mNewList;

        public ProfileDiffCallback(List<ProfileItemModel> mOldList, List<ProfileItemModel> mNewList) {
            this.mOldList = mOldList;
            this.mNewList = mNewList;
        }

        @Override
        public int getOldListSize() {
            return mOldList != null ? mOldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewList != null ? mNewList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewList.get(newItemPosition).getDriverId().equals(mOldList.get(oldItemPosition).getDriverId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
        }

    }
}
