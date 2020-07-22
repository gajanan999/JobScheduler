package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.jobs.JobOne;
import com.app.service.TaskSchedulerService;

@RestController
public class RestRequestController {

	private final Logger logger = LoggerFactory.getLogger(RestRequestController.class);
	
	@Autowired
	TaskSchedulerService taskSchedulerService;
	
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String schedule(@RequestParam("jobName") String jobName,@RequestParam("cronExpression") String cronExpression)
	{
		logger.info("Requested for job run {} with cron {}", jobName, cronExpression);
		JobOne jobOne = new JobOne(jobName);
		taskSchedulerService.addTaskToScheduler("1", jobOne, cronExpression);
        return "Started";
    }

}
