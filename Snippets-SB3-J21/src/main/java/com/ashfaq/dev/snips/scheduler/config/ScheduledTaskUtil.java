
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskUtil {

	// Inject the cron expression from the properties file
	@Value("${app.schedule.cron}")
	private String cronExpression;

	// Use the cron expression in the @Scheduled annotation
	@Scheduled(cron = "${app.schedule.cron}")
	public void executeScheduledTask() {
		System.out.println("Scheduled task is running with cron expression: " + cronExpression);
	}
}

//appplication.prop 
// # Runs every minute
// app.schedule.cron=* * * * * *
