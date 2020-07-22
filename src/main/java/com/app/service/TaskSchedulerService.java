package com.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class TaskSchedulerService {

	Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

	ThreadPoolTaskScheduler threadPoolTaskScheduler;

	public TaskSchedulerService(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
		this.threadPoolTaskScheduler = threadPoolTaskScheduler;
	}

	public void addTaskToScheduler(String id, Runnable task, String cronExpression) {
		ScheduledFuture<?> scheduledTask = threadPoolTaskScheduler.schedule(task, 
																		new CronTrigger(cronExpression,TimeZone.getTimeZone(TimeZone.getDefault().getID())));
		jobsMap.put(id, scheduledTask);
	}
	
	// Remove scheduled task 
	public void removeTaskFromScheduler(String id) {
		ScheduledFuture<?> scheduledTask = jobsMap.get(id);
		if(scheduledTask != null) {
			scheduledTask.cancel(true);
			jobsMap.put(id, null);
		}
	}
		
	
}
