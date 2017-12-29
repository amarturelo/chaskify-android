package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.ui.model.TaskWaypointItemModel;

import java.util.List;

/**
 * Created by alberto on 27/12/17.
 */

public class TaskWaypointListAdapter extends RecyclerView.Adapter<TaskWaypointListAdapter.ViewHolder> {

    private List<TaskWaypointItemModel> mTaskWaypointItemModels;

    private OnItemListened mOnItemListened;

    public void setOnItemListened(OnItemListened onItemListened) {
        this.mOnItemListened = onItemListened;
    }

    public TaskWaypointListAdapter(List<TaskWaypointItemModel> taskWaypointItemModels) {
        this.mTaskWaypointItemModels = taskWaypointItemModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_waypoint_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemListened != null)
                mOnItemListened.onClickItem(v, position);
        });
    }

    @Override
    public int getItemCount() {
        return mTaskWaypointItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public TaskWaypointItemModel getItem(int position) {
        return mTaskWaypointItemModels.get(position);
    }

}
