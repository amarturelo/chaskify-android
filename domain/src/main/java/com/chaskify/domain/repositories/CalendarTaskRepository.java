package com.chaskify.domain.repositories;

import com.chaskify.domain.model.CalendarTask;
import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 15/12/17.
 */

public interface CalendarTaskRepository {
    Single<List<CalendarTask>> calendarTasks(Date start, Date end);
}
