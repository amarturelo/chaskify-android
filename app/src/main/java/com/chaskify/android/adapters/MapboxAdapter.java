package com.chaskify.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

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

public class MapboxAdapter extends MapboxMapMeAdapter {

    private List<MarkerData> markers;

    public MapboxAdapter(@NonNull Context context) {
        super(context);
        this.markers = new ArrayList<>();
    }

    @NotNull
    @Override
    public MapAnnotation onCreateAnnotation(AnnotationFactory<MapboxMap> mapFactory, int position, int i1) {
        MarkerData item = this.markers.get(position);
        return mapFactory.createMarker(item.getLatLng(), getIconBitmap(item), item.getTitle());
    }

    @Override
    public void onBindAnnotation(MapAnnotation mapAnnotation, int position, Object payload) {
        if (mapAnnotation instanceof MarkerAnnotation) {
            MarkerData item = this.markers.get(position);
            ((MarkerAnnotation) mapAnnotation).setIcon(getIconBitmap(item));
        }
    }

    @Override
    public int getItemCount() {
        return markers.size();
    }

    public void addAll(List<MarkerData> markers) {
        this.markers.addAll(markers);
        notifyDataSetChanged();
    }

    public void clear() {
        this.markers.clear();
        notifyDataSetChanged();
    }

    private Bitmap getIconBitmap(MarkerData item) {
        Drawable icon;

        MarkerData.MarkerColour colour = item.getMarkerColour();

        if (item.isSelected()) {
            colour = MarkerData.MarkerColour.GREEN;
        }

        icon = ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher);

        return ((BitmapDrawable) icon).getBitmap();
    }

}