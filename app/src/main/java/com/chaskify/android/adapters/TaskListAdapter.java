package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.chaskify.android.R;
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

    public interface OnItemListened {
        void onClickItem(View view, int position);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemListened != null)
                    mOnItemListened.onClickItem(holder.itemView, position);
            }
        });

        holder.taskType.setText(taskItemModel.getTrans_type());
        holder.taskDate.setText(DateUtils.formatDateTime(
                holder.itemView.getContext()
                , taskItemModel.getDelivery_date().getTime()
                , DateUtils.FORMAT_SHOW_TIME));
        holder.taskPlace.setText(taskItemModel.getDelivery_address());
        holder.taskClientName.setText(taskItemModel.getCustomer_name());
        holder.taskId.setText(taskItemModel.getTask_id());

        switch (taskItemModel.getStatus()) {
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
    }

    @Override
    public int getItemCount() {
        return mTaskItemModels.size();
    }

    public void clear() {
        mTaskItemModels.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView taskDate;
        public TextView taskId;
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
            taskId = itemView.findViewById(R.id.task_id);
            taskStatus = itemView.findViewById(R.id.task_status);
        }
    }

    public void add(List<TaskItemModel> taskItemModels) {
        Stream.of(taskItemModels)
                .forEach(taskItemModel -> {
                    int pos = mTaskItemModels.indexOf(taskItemModel);
                    if (pos != -1) {
                        mTaskItemModels.remove(pos);
                        mTaskItemModels.add(pos, taskItemModel);
                        notifyItemChanged(pos);
                    } else {
                        mTaskItemModels.add(taskItemModel);
                        notifyItemInserted(mTaskItemModels.size() - 1);
                    }
                });
    }

}
