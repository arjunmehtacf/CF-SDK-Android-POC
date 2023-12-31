package com.example.cf_sdk.changebankapi.usecase;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 *
 * Implementation of the ExecutionThread which an IO scheduler.
 */

public class UIThread implements ExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
