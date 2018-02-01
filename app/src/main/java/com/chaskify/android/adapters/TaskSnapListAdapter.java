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
import com.chaskify.android.ui.model.MarkerData;
import com.chaskify.android.ui.model.TaskItemSnapModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 6/12/17.
 */

public class TaskSnapListAdapter extends RecyclerView.Adapter<TaskSnapListAdapter.ViewHolder> {

    private List<TaskItemSnapModel> mTaskItemModels;

    public TaskSnapListAdapter() {
        this.mTaskItemModels = new ArrayList<>();
    }

    public TaskItemSnapModel getItem(int position) {
        return mTaskItemModels.get(position);
    }

    private OnItemListened mOnItemListened;

    public void setOnItemListened(OnItemListened onItemListened) {
        this.mOnItemListened = onItemListened;
    }

    @Override
    public TaskSnapListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_snap_view, parent, false);
        return new TaskSnapListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskSnapListAdapter.ViewHolder holder, int position) {
        TaskItemSnapModel taskItemModel = mTaskItemModels.get(position);

        holder.itemView.setOnClickListener(v -> {
            if (mOnItemListened != null)
                mOnItemListened.onClickItem(holder.itemView, position);
        });

        holder.taskType.setText(taskItemModel.getTrans_type());
        holder.taskTime.setText(DateUtils.formatDateTime(
                holder.itemView.getContext()
                , taskItemModel.getDelivery_date().getTime()
                , DateUtils.FORMAT_SHOW_TIME));
        holder.taskPlace.setText(taskItemModel.getDelivery_address());
        holder.taskOrderNumber.setText(holder.itemView.getResources().getText(R.string.title_task) + " #" + taskItemModel.getOrderNumber());

        switch (taskItemModel.getStatus()) {
            case "CANCELED":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_canceled));
                holder.taskStatus.setBackgroundResource(R.color.task_canceled);
                break;
            case "UNASSIGNED":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_unassigned));
                holder.taskStatus.setBackgroundResource(R.color.task_unassigned);
                break;
            case "ASSIGNED":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_assigned));
                holder.taskStatus.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                holder.taskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                holder.taskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_in_route));
                holder.taskStatus.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_accepted));
                holder.taskStatus.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_signature));
                holder.taskStatus.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_arrived));
                holder.taskStatus.setBackgroundResource(R.color.task_arrived);
                break;

            case "PENDING":
                holder.taskOrderNumber.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_pending));
                holder.taskStatus.setBackgroundResource(R.color.task_pending);
                break;
        }

        if (taskItemModel.getStatus().equals("CANCELED")) {
            mTaskItemModels.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mTaskItemModels.size();
    }

    public List<TaskItemSnapModel> getItems() {
        return mTaskItemModels;
    }


    public int getItemPositionById(String id) {
        for (int i = 0; i < mTaskItemModels.size(); i++)
            if (mTaskItemModels.get(i).getTaskId().equals(id))
                return i;
        return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView taskTime;
        public TextView taskOrderNumber;
        public TextView taskPlace;
        public TextView taskType;
        public View taskStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            taskTime = itemView.findViewById(R.id.task_time);
            taskPlace = itemView.findViewById(R.id.task_place);
            taskType = itemView.findViewById(R.id.task_type);
            taskOrderNumber = itemView.findViewById(R.id.task_order_number);
            taskStatus = itemView.findViewById(R.id.task_status);
        }
    }

    public static class TaskSnapDiffCallback extends DiffUtil.Callback {
        private List<TaskItemSnapModel> mOldList;
        private List<TaskItemSnapModel> mNewList;

        public TaskSnapDiffCallback(List<TaskItemSnapModel> mOldList, List<TaskItemSnapModel> mNewList) {
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

    public void update(List<TaskItemSnapModel> mTaskItemModels) {
        TaskSnapDiffCallback callback = new TaskSnapDiffCallback(this.mTaskItemModels, mTaskItemModels);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.mTaskItemModels.clear();
        this.mTaskItemModels.addAll(mTaskItemModels);
        diffResult.dispatchUpdatesTo(this);
    }
}
