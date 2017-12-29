package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskWaypointItemModel;

import java.util.List;

/**
 * Created by alberto on 27/12/17.
 */

public class TaskWaypointListAdapter extends RecyclerView.Adapter<TaskWaypointListAdapter.ViewHolder> {

    private List<TaskWaypointItemModel> taskWaypointItemModels;

    public TaskWaypointListAdapter(List<TaskWaypointItemModel> taskWaypointItemModels) {
        this.taskWaypointItemModels = taskWaypointItemModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_waypoint_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return taskWaypointItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
