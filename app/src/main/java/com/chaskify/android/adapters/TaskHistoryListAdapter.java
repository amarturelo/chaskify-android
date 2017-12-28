package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskHistoryItemModel;

import java.util.List;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskHistoryListAdapter extends RecyclerView.Adapter<TaskHistoryListAdapter.ViewHolder> {

    private List<TaskHistoryItemModel> mItemModelList;

    public TaskHistoryListAdapter(List<TaskHistoryItemModel> itemModelList) {
        this.mItemModelList = itemModelList;
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
            case "SUCCESSFUL":
                holder.mTaskStatusColor.setBackgroundResource(R.color.task_successful);
                holder.mTaskStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                break;
            case "COMPLETE":
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
}
