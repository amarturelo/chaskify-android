package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskItemModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 15/12/17.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private List<TaskItemModel> mTaskItemModels;

    public TaskListAdapter() {
        this.mTaskItemModels = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskItemModel taskItemModel = mTaskItemModels.get(position);

        holder.taskType.setText(taskItemModel.getTrans_type());
        holder.taskDate.setText(DateUtils
                .getRelativeTimeSpanString(holder.itemView.getContext(), taskItemModel.getDelivery_date().getTime()));
        holder.taskPlace.setText(taskItemModel.getDelivery_address());
        holder.taskClientName.setText(taskItemModel.getCustomer_name());
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
        public TextView taskPlace;
        public TextView taskType;
        public TextView taskClientName;
        public TextView taskDateComing;
        public View formTaskDateComing;
        public View taskTypeColor;

        public ViewHolder(View itemView) {
            super(itemView);
            taskClientName = itemView.findViewById(R.id.task_customer_name);
            taskDate = itemView.findViewById(R.id.task_time);
            taskPlace = itemView.findViewById(R.id.task_place);
            taskDateComing = itemView.findViewById(R.id.task_date_coming);
            formTaskDateComing = itemView.findViewById(R.id.form_task_date_coming);
            taskType = itemView.findViewById(R.id.task_type);
            taskTypeColor = itemView.findViewById(R.id.task_type_color);
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
