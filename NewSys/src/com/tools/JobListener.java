package com.tools;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.web.context.ContextLoader;


public class JobListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent se) {
		Scheduler job = (Scheduler)ContextLoader.getCurrentWebApplicationContext().getBean("stsmqifQuertz");
		try {
			if(job.isStarted()){
				job.shutdown();
				Thread.sleep(1000);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void contextInitialized(ServletContextEvent arg0) {

	}
}