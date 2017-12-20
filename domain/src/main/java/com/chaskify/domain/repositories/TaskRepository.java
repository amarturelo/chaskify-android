package com.chaskify.domain.repositories;

import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 14/12/17.
 */

public interface TaskRepository {
    Single<List<Task>> tasks(Date date);
}