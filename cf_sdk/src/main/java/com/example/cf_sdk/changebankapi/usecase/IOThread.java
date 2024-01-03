package com.example.cf_sdk.changebankapi.usecase;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class IOThread implements ExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}