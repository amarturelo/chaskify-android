package com.chaskify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.model.TaskSnapModel;

import java.util.List;

/**
 * Created by alberto on 6/12/17.
 */

public class TaskSnapListAdapter extends RecyclerView.Adapter<TaskSnapListAdapter.ViewHolder> {

    private List<TaskSnapModel> taskSnapModels;

    public TaskSnapListAdapter(List<TaskSnapModel> taskSnapModels) {
        this.taskSnapModels = taskSnapModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskSnapModel taskSnapModel = taskSnapModels.get(position);
        //holder.mDate.setText(DateUtils.getRelativeTimeSpanString(taskSnapModel.getDate().getTime()));
    }

    @Override
    public int getItemCount() {
        return taskSnapModels.size();
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
