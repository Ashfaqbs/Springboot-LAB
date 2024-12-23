
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SchedulerController {

	@Autowired
	private SchedulerControlService schedulerControlService;

	@GetMapping("/scheduler-control")
	public String schedulerControlPage(Model model) {
		model.addAttribute("job1Enabled", schedulerControlService.isJob1Enabled());
		model.addAttribute("job2Enabled", schedulerControlService.isJob2Enabled());
		return "scheduler-control";
	}

	@PostMapping("/toggle-job1")
	public String toggleJob1() {
		schedulerControlService.setJob1Enabled(!schedulerControlService.isJob1Enabled());
		return "redirect:/scheduler-control";
	}

	@PostMapping("/toggle-job2")
	public String toggleJob2() {
		schedulerControlService.setJob2Enabled(!schedulerControlService.isJob2Enabled());
		return "redirect:/scheduler-control";
	}
}
