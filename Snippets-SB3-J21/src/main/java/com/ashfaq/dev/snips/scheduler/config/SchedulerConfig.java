package com.ashfaq.dev.snips.scheduler.config;

import java.util.concurrent.ScheduledFuture;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final ThreadPoolTaskScheduler taskScheduler;
    private ScheduledFuture<?> appStatusTask;
    private ScheduledFuture<?> dbHealthTask;

    public SchedulerConfig() {
        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.setPoolSize(2);
        this.taskScheduler.initialize();
    }

    @PostConstruct
    public void startSchedulers() {
        startAppStatusScheduler();
        startDBHealthScheduler();
    }

    public void startAppStatusScheduler() {
        if (appStatusTask == null || appStatusTask.isCancelled()) {
            appStatusTask = taskScheduler.schedule(this::sendAppStatusMail, new CronTrigger("*/10 * * * * *")); // Runs every 10 seconds
        }
    }

    public void stopAppStatusScheduler() {
        if (appStatusTask != null && !appStatusTask.isCancelled()) {
            appStatusTask.cancel(false);
        }
    }

    public void startDBHealthScheduler() {
        if (dbHealthTask == null || dbHealthTask.isCancelled()) {
            dbHealthTask = taskScheduler.schedule(this::sendDBHealthMail, new CronTrigger("0 * * * * *")); // Runs every minute
        }
    }

    public void stopDBHealthScheduler() {
        if (dbHealthTask != null && !dbHealthTask.isCancelled()) {
            dbHealthTask.cancel(false);
        }
    }

    public void sendAppStatusMail() {
        System.out.println("Sending app status mail...");
    }

    public void sendDBHealthMail() {
        System.out.println("Sending DB health status mail...");
    }
}

