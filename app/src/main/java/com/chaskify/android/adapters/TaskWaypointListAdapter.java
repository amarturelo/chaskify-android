package com.chaskify.android.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.model.TaskWaypointItemModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
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

    public TaskWaypointListAdapter() {
        this.mTaskWaypointItemModels = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_waypoint_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskWaypointItemModel taskWaypointItemModel = mTaskWaypointItemModels.get(position);

        switch (taskWaypointItemModel.getStatus()) {
            case "ASSIGNED":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_assigned));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_successful));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_in_route));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_accepted));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_signature));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_arrived));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_arrived);
                break;

            case "PENDING":
                holder.mWayPointStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.task_pending));
                holder.mWayPointStatusColor.setBackgroundResource(R.color.task_pending);
                break;
        }

        holder.mWayPointType.setText(taskWaypointItemModel.getType());
        holder.mWayPointAddress.setText(taskWaypointItemModel.getDeliveryAddress());
        holder.mWayPointId.setText(taskWaypointItemModel.getId());
        holder.mWayPointStatus.setText(taskWaypointItemModel.getStatus());


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

        public TextView mWayPointStatus;
        public TextView mWayPointId;
        public TextView mWayPointAddress;
        public TextView mWayPointType;
        public View mWayPointStatusColor;

        public ViewHolder(View itemView) {
            super(itemView);
            mWayPointStatus = itemView.findViewById(R.id.way_point_status);
            mWayPointId = itemView.findViewById(R.id.way_point_id);
            mWayPointAddress = itemView.findViewById(R.id.way_point_address);
            mWayPointType = itemView.findViewById(R.id.way_point_type);
            mWayPointStatusColor = itemView.findViewById(R.id.way_point_status_color);
        }
    }

    public TaskWaypointItemModel getItem(int position) {
        return mTaskWaypointItemModels.get(position);
    }

    public static class TaskWaypointDiffCallback extends DiffUtil.Callback {
        private List<TaskWaypointItemModel> mOldList;
        private List<TaskWaypointItemModel> mNewList;

        public TaskWaypointDiffCallback(List<TaskWaypointItemModel> mOldList, List<TaskWaypointItemModel> mNewList) {
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

    public void update(List<TaskWaypointItemModel> mNewList) {
        TaskWaypointDiffCallback callback = new TaskWaypointDiffCallback(this.mTaskWaypointItemModels, mNewList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        this.mTaskWaypointItemModels.clear();
        this.mTaskWaypointItemModels.addAll(mNewList);
        diffResult.dispatchUpdatesTo(this);
    }

}
