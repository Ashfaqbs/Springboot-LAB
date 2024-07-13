package com.ashfaq.dev.snips.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.dev.snips.service.SchedulerService;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/app-status/start")
    public String startAppStatusScheduler() {
        schedulerService.startAppStatusScheduler();
        return "App status scheduler started.";
    }

    @PostMapping("/app-status/stop")
    public String stopAppStatusScheduler() {
        schedulerService.stopAppStatusScheduler();
        return "App status scheduler stopped.";
    }

    @PostMapping("/db-health/start")
    public String startDBHealthScheduler() {
        schedulerService.startDBHealthScheduler();
        return "DB health scheduler started.";
    }

    @PostMapping("/db-health/stop")
    public String stopDBHealthScheduler() {
        schedulerService.stopDBHealthScheduler();
        return "DB health scheduler stopped.";
    }
}


///api/scheduler/app-status/start to start the app status scheduler.
///api/scheduler/app-status/stop to stop the app status scheduler.
///api/scheduler/db-health/start to start the DB health scheduler.
///api/scheduler/db-health/stop to stop the DB health scheduler.
