package com.chaskify.android.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.NotificationItemModel;
import com.chaskify.android.ui.model.TaskHistoryItemModel;

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
                //holder.task_status.setVisibility(View.VISIBLE);
                break;
            case "campaign":
                holder.image_push_type.setImageResource(R.drawable.ic_message_black_24dp);
                //holder.task_status.setVisibility(View.GONE);
                break;
        }
        /*if (itemModel.getStatus() != null)
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
            }*/
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

    public static class NotificationsDiffCallback extends DiffUtil.Callback {
        private List<NotificationItemModel> mOldList;
        private List<NotificationItemModel> mNewList;

        public NotificationsDiffCallback(List<NotificationItemModel> mOldList, List<NotificationItemModel> mNewList) {
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
            return mNewList.get(newItemPosition).getPushId().equals(mOldList.get(oldItemPosition).getPushId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
        }

    }

    public void update(List<NotificationItemModel> mNewList) {
        NotificationsDiffCallback callback = new NotificationsDiffCallback(this.mNotificationItemModels, mNewList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.mNotificationItemModels.clear();
        this.mNotificationItemModels.addAll(mNewList);
        diffResult.dispatchUpdatesTo(this);
    }
}
