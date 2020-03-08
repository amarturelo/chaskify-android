package com.chaskify.android.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskHistoryItemModel;
import com.chaskify.android.ui.model.TaskWaypointItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskHistoryListAdapter extends RecyclerView.Adapter<TaskHistoryListAdapter.ViewHolder> {

    private List<TaskHistoryItemModel> mItemModelList;

    public TaskHistoryListAdapter() {
        this.mItemModelList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_history_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskHistoryItemModel taskHistoryItemModel = mItemModelList.get(position);

        holder.mTaskTime.setText(DateUtils.formatDateTime(
                holder.itemView.getContext()
                , taskHistoryItemModel.getDateCreated().getTime()
                , DateUtils.FORMAT_SHOW_TIME));

        holder.mTaskDate.setText(DateUtils.formatDateTime(
                holder.itemView.getContext()
                , taskHistoryItemModel.getDateCreated().getTime()
                , DateUtils.FORMAT_SHOW_DATE));

        holder.mTaskStatus.setText(taskHistoryItemModel.getStatus());

        switch (taskHistoryItemModel.getStatus()) {
            case "ASSIGNED":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_assigned);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_assigned));
                break;
            case "COMPLETED":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_successful);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                break;
            case "SUCCESSFUL":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_successful);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                break;
            case "IN ROUTE":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_in_route);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_in_route));
                break;
            case "ACCEPTED":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_accepted);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_accepted));
                break;
            case "SIGNATURE":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_signature);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_signature));
                break;
            case "ARRIVED":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_arrived);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_arrived));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View mTaskStatusColor;
        public TextView mTaskStatus;
        public TextView mTaskDate;
        public TextView mTaskTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mTaskStatusColor = itemView.findViewById(R.id.task_status_color);
            mTaskStatus = itemView.findViewById(R.id.task_status);
            mTaskDate = itemView.findViewById(R.id.task_date);
            mTaskTime = itemView.findViewById(R.id.task_time);
        }
    }

    public static class TaskHistoryDiffCallback extends DiffUtil.Callback {
        private List<TaskHistoryItemModel> mOldList;
        private List<TaskHistoryItemModel> mNewList;

        public TaskHistoryDiffCallback(List<TaskHistoryItemModel> mOldList, List<TaskHistoryItemModel> mNewList) {
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
            return mNewList.get(newItemPosition).getId().equals(mOldList.get(oldItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
        }

    }

    public void update(List<TaskHistoryItemModel> mNewList) {
        TaskHistoryDiffCallback callback = new TaskHistoryDiffCallback(this.mItemModelList, mNewList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.mItemModelList.clear();
        this.mItemModelList.addAll(mNewList);
        diffResult.dispatchUpdatesTo(this);
    }
}
