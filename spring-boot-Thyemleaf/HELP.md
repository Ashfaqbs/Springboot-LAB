```

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.*;

@Controller
public class YourController {

    private final YourService yourService; // Autowire your service class

    // Constructor injection
    public YourController(YourService yourService) {
        this.yourService = yourService;
    }

    @GetMapping("/yourEndpoint")
    public String yourEndpoint(Model model) {
        // Create a thread pool with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            // List to store your CompletableFuture tasks
            List<CompletableFuture<Void>> tasks = new CopyOnWriteArrayList<>();

            // Submit your tasks to the thread pool
            tasks.add(CompletableFuture.runAsync(() -> yourService.method1(), executor));
            tasks.add(CompletableFuture.runAsync(() -> yourService.method2(), executor));
            // Add more tasks as needed

            // Wait for all tasks to complete
            CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
            allOf.get(); // This will block until all tasks are done
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
        } finally {
            // Shut down the thread pool
            executor.shutdown();
        }

        return "yourThymeleafPage"; // Return the name of your Thymeleaf template
    }
}



<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <!-- Other head elements -->

    <!-- Add this line for the favicon -->
    <link rel="icon" type="image/png" th:href="@{/path/to/your/favicon.png}"/>

    <!-- Other head elements -->
</head>
<body>
    <!-- Body content -->
</body>
</html>



import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleService {

    private Date nextRunTime;
    private int executionCount = 0;

    @Scheduled(cron = "0 0 6,12,18,24 * * ?")
    public void scheduledTask() {
        // Your task logic here
        executionCount++;
        nextRunTime = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000); // Set to next day for daily schedule
    }

    public Date getNextRunTime() {
        return nextRunTime;
    }

    public int getExecutionCount() {
        return executionCount;
    }
}



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/status")
    public Map<String, Object> getScheduleStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("nextRunTime", scheduleService.getNextRunTime());
        status.put("executionCount", scheduleService.getExecutionCount());
        return status;
    }
}


```


