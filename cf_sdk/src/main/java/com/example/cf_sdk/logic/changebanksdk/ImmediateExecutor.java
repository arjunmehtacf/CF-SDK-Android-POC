package com.example.cf_sdk.logic.changebanksdk;

import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Executor providing an immediate scheduler.
 */

public class ImmediateExecutor implements ExecutionThread {

    @Override
    public Scheduler getScheduler() {
        return Schedulers.trampoline();
    }
}
