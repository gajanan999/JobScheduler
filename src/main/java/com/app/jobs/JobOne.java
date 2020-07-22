package com.app.jobs;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobOne implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(JobOne.class);

	private String jobName;

	public JobOne(String jobName) {
		this.jobName = jobName;
	}


	@Override
	public void run() {
		try {
			logger.info("Running job {} at {}", jobName, LocalDateTime.now());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
