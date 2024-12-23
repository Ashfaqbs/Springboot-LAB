package com.ashfaq.example;

import org.springframework.stereotype.Service;

@Service
public class SchedulerControlService {
	private boolean job1Enabled = true;
	private boolean job2Enabled = true;

	public boolean isJob1Enabled() {
		return job1Enabled;
	}

	public void setJob1Enabled(boolean job1Enabled) {
		this.job1Enabled = job1Enabled;
	}

	public boolean isJob2Enabled() {
		return job2Enabled;
	}

	public void setJob2Enabled(boolean job2Enabled) {
		this.job2Enabled = job2Enabled;
	}
}
