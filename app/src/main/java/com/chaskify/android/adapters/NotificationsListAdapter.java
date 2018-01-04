package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.NotificationItemModel;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationItemModel itemModel = mNotificationItemModels.get(position);
        holder.push_message.setText(itemModel.getPushMessage());
        holder.push_title.setText(itemModel.getPushTitle());
        holder.push_date.setText(itemModel.getDateCreated());

        switch (itemModel.getPushType()) {
            case "task":
                holder.image_push_type.setImageResource(R.drawable.ic_assignment_black_24dp);
                holder.task_status.setVisibility(View.VISIBLE);
                break;
            case "campaign":
                holder.image_push_type.setImageResource(R.drawable.ic_message_black_24dp);
                holder.task_status.setVisibility(View.GONE);
                break;
        }

        switch (itemModel.getStatus()) {
            case "ASSIGNED":
                holder.task_status.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                holder.task_status.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                holder.task_status.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                holder.task_status.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                holder.task_status.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                holder.task_status.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                holder.task_status.setBackgroundResource(R.color.task_arrived);
                break;
            case "PENDING":
                holder.task_status.setBackgroundResource(R.color.task_pending);
                break;
        }
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

        public ImageView image_push_type;
        public TextView push_title;
        public TextView push_message;
        public TextView push_date;
        public View task_status;

        public ViewHolder(View itemView) {
            super(itemView);
            image_push_type = itemView.findViewById(R.id.image_push_type);
            push_title = itemView.findViewById(R.id.push_title);
            push_message = itemView.findViewById(R.id.push_message);
            push_date = itemView.findViewById(R.id.push_date);
            task_status = itemView.findViewById(R.id.task_status);

        }
    }
}
