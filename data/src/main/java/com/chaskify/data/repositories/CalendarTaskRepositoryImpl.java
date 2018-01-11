package com.chaskify.data.repositories;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.CalendarTaskRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyCalendarTask;
import com.chaskify.domain.model.CalendarTask;
import com.chaskify.domain.repositories.CalendarTaskRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by alberto on 15/12/17.
 */

public class CalendarTaskRepositoryImpl implements CalendarTaskRepository {

    private CalendarTaskRestClient calendarTaskRestClient;

    public CalendarTaskRepositoryImpl(CalendarTaskRestClient calendarTaskRestClient) {
        this.calendarTaskRestClient = calendarTaskRestClient;
    }

    @Override
    public Single<List<CalendarTask>> calendarTasks(Date start, Date end) {
        return Single.create((SingleOnSubscribe<List<ChaskifyCalendarTask>>) emitter -> calendarTaskRestClient.getCalendarTaskByRangeOfDate(start, end, new ApiCallback<List<ChaskifyCalendarTask>>() {
            @Override
            public void onSuccess(List<ChaskifyCalendarTask> chaskifyCalendarTasks) {
                emitter.onSuccess(chaskifyCalendarTasks);
            }

            @Override
            public void onNetworkError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {

            }
        })).map(chaskifyCalendarTasks -> Stream.of(chaskifyCalendarTasks)
                .map(chaskifyCalendarTask -> new CalendarTask()
                        .setDay(chaskifyCalendarTask.getDay())
                        .setId(chaskifyCalendarTask.getId())
                        .setMonth(chaskifyCalendarTask.getMonth())
                        .setYear(chaskifyCalendarTask.getYear())
                        .setTitle(chaskifyCalendarTask.getTitle())
                )
                .toList());
    }
}
