package com.chaskify.chaskify_sdk.mapper;

import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskHistory;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWaypoint;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alberto on 29/12/17.
 */

public class TaskDeserializer implements JsonDeserializer<ChaskifyTask> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());


    @Override
    public ChaskifyTask deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject node = json.getAsJsonObject();
        ChaskifyTask chaskifyTask = new ChaskifyTask();
        chaskifyTask.setTaskId(node.has("task_id") ? node.get("task_id").getAsString() : "");
        chaskifyTask.setCustomerId(node.has("customer_id") ? node.get("customer_id").getAsString() : "");
        chaskifyTask.setTaskDescription(node.has("task_description") ? node.get("task_description").getAsString() : "");
        chaskifyTask.setTransType(node.has("trans_type") ? node.get("trans_type").getAsString() : "");
        chaskifyTask.setContactNumber(node.has("contact_number") ? node.get("contact_number").getAsString() : "");
        chaskifyTask.setEmailAddress(node.has("email_address") ? node.get("email_address").getAsString() : "");
        chaskifyTask.setCustomerName(node.has("customer_name") ? node.get("customer_name").getAsString() : "");
        chaskifyTask.setDeliveryAddress(node.has("delivery_address") ? node.get("delivery_address").getAsString() : "");
        chaskifyTask.setTeamId(node.has("team_id") ? node.get("team_id").getAsString() : "");
        chaskifyTask.setDriverId(node.has("driver_id") ? node.get("driver_id").getAsString() : "");
        chaskifyTask.setTaskLat(node.has("task_lat") ? node.get("task_lat").getAsDouble() : 0);
        chaskifyTask.setTaskLng(node.has("task_lng") ? node.get("task_lng").getAsDouble() : 0);
        chaskifyTask.setStatus(node.has("status") ? node.get("status").getAsString() : "");
        chaskifyTask.setDeliveryDate(node.has("delivery_date") ? (Date) context.deserialize(node.get("delivery_date"), Date.class) : null);
        chaskifyTask.setDateCreated(node.has("date_created") ? node.get("date_created").getAsString() : "");
        chaskifyTask.setTeamName(node.has("team_name") ? node.get("team_name").getAsString() : "");
        if (node.has("waypoints")) {
            Type type = new TypeToken<List<ChaskifyTaskWaypoint>>() {
            }.getType();
            chaskifyTask.setWaypoints(node.get("waypoints") instanceof JsonArray ? (List<ChaskifyTaskWaypoint>) context.deserialize(node.get("waypoints"), type) : new ArrayList<ChaskifyTaskWaypoint>());
        } else
            chaskifyTask.setWaypoints(new ArrayList<ChaskifyTaskWaypoint>());

        if (node.has("history")) {
            Type type = new TypeToken<List<ChaskifyTaskHistory>>() {
            }.getType();
            chaskifyTask.setHistory(node.get("history") instanceof JsonArray ? (List<ChaskifyTaskHistory>) context.deserialize(node.get("history"), type) : new ArrayList<ChaskifyTaskHistory>());
        } else
            chaskifyTask.setHistory(new ArrayList<ChaskifyTaskHistory>());

        return chaskifyTask;
    }
}
