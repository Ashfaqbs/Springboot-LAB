
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Autowired
	private SchedulerControlService schedulerControlService;

	@Scheduled(cron = "0 * * * * *") // Runs every minute
	public void job1() {
		if (schedulerControlService.isJob1Enabled()) {
			System.out.println("Job 1 is running...");
		}else {
			System.out.println("Job 1 is Paused");

		}
	}

	@Scheduled(cron = "30 * * * * *") // Runs every 30 seconds
	public void job2() {
		if (schedulerControlService.isJob2Enabled()) {
			System.out.println("Job 2 is running...");
		}else {
			System.out.println("Job 2 is Paused");

		}
	}
}
