package com.example.cf_sdk.logic.sdk_module.singleton;


import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Implementation of the ExecutionThread which an IO scheduler.
 */

public class IOThread implements ExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
