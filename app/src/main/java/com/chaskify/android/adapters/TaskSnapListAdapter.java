package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskItemSnapModel;

import java.util.List;

/**
 * Created by alberto on 6/12/17.
 */

public class TaskSnapListAdapter extends RecyclerView.Adapter<TaskSnapListAdapter.ViewHolder> {

    private List<TaskItemSnapModel> taskItemSnapModels;

    public TaskSnapListAdapter(List<TaskItemSnapModel> taskItemSnapModels) {
        this.taskItemSnapModels = taskItemSnapModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_snap_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskItemSnapModel taskItemSnapModel = taskItemSnapModels.get(position);
        //holder.mDate.setText(DateUtils.getRelativeTimeSpanString(taskItemSnapModel.getDate().getTime()));
    }

    @Override
    public int getItemCount() {
        return taskItemSnapModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mDate;
        public TextView mClient;
        public TextView mAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.task_date);
        }
    }
}
