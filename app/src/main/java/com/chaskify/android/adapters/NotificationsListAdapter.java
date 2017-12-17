package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.NotificationItemModel;
import com.chaskify.android.ui.model.TaskItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationsListAdapter extends RecyclerView.Adapter<NotificationsListAdapter.ViewHolder> {

    private List<NotificationItemModel> mNotificationItemModels;

    public NotificationsListAdapter() {
        mNotificationItemModels = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public void add(List<NotificationItemModel> notificationItemModels) {
        Stream.of(notificationItemModels)
                .forEach(notificationItemModel -> {
                    mNotificationItemModels.add(notificationItemModel);
                    notifyItemInserted(mNotificationItemModels.size() - 1);
                });
    }

    public void clear() {
        mNotificationItemModels.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mNotificationItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
