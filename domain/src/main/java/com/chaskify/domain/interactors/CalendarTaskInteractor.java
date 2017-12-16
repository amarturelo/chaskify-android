package com.chaskify.domain.interactors;

import com.chaskify.domain.model.CalendarTask;
import com.chaskify.domain.repositories.CalendarTaskRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 15/12/17.
 */

public class CalendarTaskInteractor {

    private CalendarTaskRepository calendarTaskRepository;

    public CalendarTaskInteractor(CalendarTaskRepository calendarTaskRepository) {
        this.calendarTaskRepository = calendarTaskRepository;
    }

    public Single<List<CalendarTask>> calendarTasks(Date start, Date end) {
        return calendarTaskRepository.calendarTasks(start, end);
    }
}
