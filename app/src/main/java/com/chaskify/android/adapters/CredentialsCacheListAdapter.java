package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;
import com.chaskify.android.adapters.viewholder.CredentialsCacheItemViewHolder;
import com.chaskify.android.ui.model.CredentialsCacheItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 8/01/18.
 */

public class CredentialsCacheListAdapter extends RecyclerView.Adapter<CredentialsCacheItemViewHolder> {

    private List<CredentialsCacheItemModel> mListCacheModels;

    public CredentialsCacheListAdapter() {
        mListCacheModels = new ArrayList<>();
    }

    @Override
    public CredentialsCacheItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_cache_item_view, parent, false);
        return new CredentialsCacheItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CredentialsCacheItemViewHolder holder, int position) {
        if (holder.getCredentialsCacheItemModel() == null || !holder.getCredentialsCacheItemModel().equals(mListCacheModels.get(position)))
            holder.onBind(mListCacheModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mListCacheModels.size();
    }

    public void render(List<CredentialsCacheItemModel> listCacheModels) {
        this.mListCacheModels.clear();
        this.mListCacheModels.addAll(listCacheModels);
        notifyDataSetChanged();
    }
}
