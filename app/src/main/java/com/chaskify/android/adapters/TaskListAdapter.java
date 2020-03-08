package com.chaskify.android.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.ui.model.TaskItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 15/12/17.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private List<TaskItemModel> mTaskItemModels;

    public TaskListAdapter() {
        this.mTaskItemModels = new ArrayList<>();
    }

    public TaskItemModel getItem(int position) {
        return mTaskItemModels.get(position);
    }

    private OnItemListened mOnItemListened;

    public void setOnItemListened(OnItemListened onItemListened) {
        this.mOnItemListened = onItemListened;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskItemModel taskItemModel = mTaskItemModels.get(position);

        holder.taskType.setText(taskItemModel.getTransType());

        switch (taskItemModel.getTransType()) {
            case "service":
                holder.taskTypeColor.setBackgroundResource(R.drawable.bg_task_type_service);
                break;
            case "delivery":
                holder.taskTypeColor.setBackgroundResource(R.drawable.bg_task_type_delivery);
                break;
            case "pickup":
                holder.taskTypeColor.setBackgroundResource(R.drawable.bg_task_type_pickup);
                break;
        }

        holder.taskDate.setText(DateUtils.formatDateTime(
                holder.itemView.getContext()
                , taskItemModel.getDelivery_date().getTime()
                , DateUtils.FORMAT_SHOW_TIME));
        holder.taskPlace.setText(taskItemModel.getDelivery_address());
        holder.taskClientName.setText(taskItemModel.getCustomer_name());
        holder.taskOrderNumber.setText(taskItemModel.getOrderNumber());

        switch (taskItemModel.getStatus()) {
            case "CANCELED":
                holder.taskStatus.setBackgroundResource(R.color.task_canceled);
                break;
            case "UNASSIGNED":
                holder.taskStatus.setBackgroundResource(R.color.task_unassigned);
                break;
            case "ASSIGNED":
                holder.taskStatus.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                holder.taskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                holder.taskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                holder.taskStatus.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                holder.taskStatus.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                holder.taskStatus.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                holder.taskStatus.setBackgroundResource(R.color.task_arrived);
                break;
        }

        /*if (taskItemModel.getStatus().equals("CANCELED")) {
            mTaskItemModels.remove(position);
            notifyItemRemoved(position);
        }*/
    }

    @Override
    public int getItemCount() {
        return mTaskItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView taskDate;
        public TextView taskOrderNumber;
        public TextView taskPlace;
        public TextView taskType;
        public TextView taskClientName;
        public TextView taskDateComing;
        public View formTaskDateComing;
        public View taskTypeColor;
        public View taskStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            taskClientName = itemView.findViewById(R.id.task_customer_name);
            taskDate = itemView.findViewById(R.id.task_time);
            taskPlace = itemView.findViewById(R.id.task_place);
            taskDateComing = itemView.findViewById(R.id.task_date_coming);
            formTaskDateComing = itemView.findViewById(R.id.form_task_date_coming);
            taskType = itemView.findViewById(R.id.task_type);
            taskTypeColor = itemView.findViewById(R.id.task_type_color);
            taskOrderNumber = itemView.findViewById(R.id.task_order_number);
            taskStatus = itemView.findViewById(R.id.task_status);

            itemView.setOnClickListener(v -> {
                if (mOnItemListened != null)
                    mOnItemListened.onClickItem(itemView, getAdapterPosition());
            });
        }
    }

    public static class TaskSnapDiffCallback extends DiffUtil.Callback {
        private List<TaskItemModel> mOldList;
        private List<TaskItemModel> mNewList;

        public TaskSnapDiffCallback(List<TaskItemModel> mOldList, List<TaskItemModel> mNewList) {
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
            return mNewList.get(newItemPosition).getTaskId().equals(mOldList.get(oldItemPosition).getTaskId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
        }

    }

    public void update(List<TaskItemModel> mNewList) {
        TaskSnapDiffCallback callback = new TaskSnapDiffCallback(this.mTaskItemModels, mNewList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.mTaskItemModels.clear();
        this.mTaskItemModels.addAll(mNewList);
        diffResult.dispatchUpdatesTo(this);
    }

}
