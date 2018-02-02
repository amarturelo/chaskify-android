package com.chaskify.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.MarkerData;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import nz.co.trademe.mapme.annotations.AnnotationFactory;
import nz.co.trademe.mapme.annotations.MapAnnotation;
import nz.co.trademe.mapme.annotations.MarkerAnnotation;
import nz.co.trademe.mapme.mapbox.MapboxMapMeAdapter;
import timber.log.Timber;

public class MapboxAdapter extends MapboxMapMeAdapter {

    private List<MarkerData> markers;

    public MapboxAdapter(@NonNull Context context) {
        super(context);
        Timber.tag(MapboxAdapter.class.getSimpleName());
        this.markers = new ArrayList<>();
    }

    @NotNull
    @Override
    public MapAnnotation onCreateAnnotation(AnnotationFactory<MapboxMap> mapFactory, int position, int i1) {
        MarkerData item = this.markers.get(position);
        return mapFactory.createMarker(item.getLatLng(), null, item.getTitle());
    }

    @Override
    public void onBindAnnotation(MapAnnotation mapAnnotation, int position, Object payload) {
        if (mapAnnotation instanceof MarkerAnnotation) {
            MarkerData item = this.markers.get(position);

            if (item.getStatus().equals("CANCELED")) {
                markers.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return markers.size();
    }

    public MarkerData getItem(int position) {
        return markers.get(position);
    }

    public static class MarkerDiffCallback extends DiffUtil.Callback {
        private List<MarkerData> mOldList;
        private List<MarkerData> mNewList;

        public MarkerDiffCallback(List<MarkerData> oldList, List<MarkerData> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
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

    public void update(List<MarkerData> newMarkers) {
        Timber.d("update " + newMarkers);
        MarkerDiffCallback callback = new MarkerDiffCallback(this.markers, newMarkers);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        this.markers.clear();
        this.markers.addAll(newMarkers);
        diffResult.dispatchUpdatesTo(this);
    }

}