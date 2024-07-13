package com.ashfaq.dev.snips.service;

import org.springframework.stereotype.Service;

import com.ashfaq.dev.snips.scheduler.config.SchedulerConfig;

@Service
public class SchedulerService {

    private final SchedulerConfig schedulerConfig;

    public SchedulerService(SchedulerConfig schedulerConfig) {
        this.schedulerConfig = schedulerConfig;
    }

    public void startAppStatusScheduler() {
        schedulerConfig.startAppStatusScheduler();
    }

    public void stopAppStatusScheduler() {
        schedulerConfig.stopAppStatusScheduler();
    }

    public void startDBHealthScheduler() {
        schedulerConfig.startDBHealthScheduler();
    }

    public void stopDBHealthScheduler() {
        schedulerConfig.stopDBHealthScheduler();
    }
}
