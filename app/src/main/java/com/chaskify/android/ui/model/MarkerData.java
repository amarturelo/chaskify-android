package com.chaskify.android.ui.model;

import nz.co.trademe.mapme.LatLng;

public class MarkerData {

    private String id;

    private final LatLng latLng;
    private String title;

    private String icon;

    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public MarkerData(String id, LatLng latLng) {
        this(id, latLng, null, null);

    }

    public MarkerData(String id, LatLng latLng, String title) {
        this(id, latLng, title, null);
    }

    public MarkerData(String id, LatLng latLng, String title, String icon) {
        this.id = id;
        this.latLng = latLng;
        this.title = title;
        this.icon = icon;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getTitle() {
        return title;
    }


    public String getId() {
        return id;
    }

    public MarkerData setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "MarkerData{" +
                "latLng=" + latLng +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarkerData)) return false;

        MarkerData that = (MarkerData) o;

        if (selected != that.selected) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (latLng != null ? !latLng.equals(that.latLng) : that.latLng != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return icon != null ? icon.equals(that.icon) : that.icon == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latLng != null ? latLng.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (selected ? 1 : 0);
        return result;
    }
}