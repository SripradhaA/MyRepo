/**
 * 
 */
package com.EMD.LSDB.common.framework.notificationservice;

/**
 * @author ps57222
 *
 */

/*******************************************************************************************
 *  * * *		This CLASS is used to SCHEDULE THE MAIL JOB
 *  
 * @author  	Satyam Computer Services Ltd  
 * @version 	1.0  
 
 ******************************************************************************************/

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

import com.EMD.LSDB.common.logger.LogUtil;

/**
 * <p>
 * ScheduleController sets up a Quartz Scheduler and exposes it for other
 * classes to use the scheduler. This scheduler is automatically started when
 * the application is deployed.
 * </p>
 * 
 *
 */

public class ScheduleController {
	
	/**
	 *  Holds the instance of the quartz scheduler
	 **/
	private static Scheduler m_quartzScheduler;
	
	/**
	 * Constructor
	 * 
	 */
	
	public ScheduleController(String p_schedlerName) {
		startScheduler(p_schedlerName);
	}
	
	public ScheduleController() {
		startDefaultScheduler();
	}
	
	/**
	 * Temporarily halts the QuartzScheduler from firing the Triggers
	 * The scheduler is not destroyed, and can be resumed at any time.
	 * @return true if the QuartzScheduler paused successfully
	 * @throws SchedulerException
	 */
	
	public static boolean pauseScheduler() throws SchedulerException {
		
		if (!getScheduler().isPaused()) {
			getScheduler().pause();
		}
		return true;
	}
	
	/**
	 * Resume (un-pause) the QuartzScheduler
	 * @return true if the QuartzScheduler re-started successfully
	 * @throws SchedulerException
	 */
	
	public static boolean resumeScheduler() throws SchedulerException {
		getScheduler().resumeAll();
		return true;
	}
	
	/**
	 * Halts the QuartzScheduler's firing of Triggers,
	 * and cleans up all resources associated with the QuartzScheduler.
	 * The scheduler cannot be re-started.This method should not be called unintentionally.
	 * 
	 * @param p_waitForJobsToCompleteOnShutdown
	 *          if true the scheduler will not allow this method
	 *          to return until all currently executing jobs have completed.
	 *          if false the scheduler is shutdown immediately
	 * 
	 * @return true if the QuartzScheduler shutdown successfully    
	 * @throws SchedulerException
	 */
	
	public static boolean shutdownScheduler(
			boolean p_waitForJobsToCompleteOnShutdown)
	throws SchedulerException {
		
		getScheduler().shutdown(p_waitForJobsToCompleteOnShutdown);
		return true;
	}
	
	/**
	 * Add the given job to the Scheduler.
	 * Overwrites the existing job with the same name if 
	 * @param p_overwriteExistingJob is set to true.
	 * The Job must by definition be 'durable', if it is not, 
	 * SchedulerException will be thrown.
	 * @param jobDetail -  job to add
	 * @param p_trigger -  trigger to trigger the job.
	 * @param p_overwriteExistingJob  - to indicate override the existing job 
	 * with same name or not
	 * @return true if the job was actually added, false if it is not added
	 * @throws SchedulerException
	 * 
	 */
	
	public static boolean addJobToScheduler(JobDetail p_jobDetail,
			Trigger p_trigger, boolean p_overwriteExistingJob)
	throws SchedulerException {
		
		p_jobDetail.setDurability(true);
		if (p_overwriteExistingJob
				|| getScheduler().getJobDetail(p_jobDetail.getName(),
						p_jobDetail.getGroup()) == null) {
			Date runAt = getScheduler().scheduleJob(p_jobDetail, p_trigger);
			LogUtil.logMessage("THIS JOB RUN AT " + runAt);
			return true;
		}
		return false;
	}
	
	/**
	 * Deletes a job.
	 * @param p_jobName -  job name.
	 * @param p_jobGroup - job group.
	 * @throws SchedulerException
	 */
	
	public static boolean deleteJob(String p_jobName, String p_jobGroup)
	throws SchedulerException {
		
		getScheduler().deleteJob(p_jobName, p_jobGroup);
		return true;
		
	}
	
	/**
	 * Returns the trigger of a specified job.
	 * @param p_jobName - job name.
	 * @param p_jobGroup - job group.
	 * @return A trigger.
	 * @throws SchedulerException when something goes wrong.
	 */
	
	public static Trigger getTrigger(String p_jobName, String p_jobGroup)
	throws SchedulerException {
		
		String[] triggerGroups = getScheduler().getTriggerGroupNames();
		
		for (int groupIndex = 0; groupIndex < triggerGroups.length; groupIndex++) {
			String[] triggerNames = getScheduler().getTriggerNames(
					triggerGroups[groupIndex]);
			
			for (int nameIndex = 0; nameIndex < triggerNames.length; nameIndex++) {
				
				Trigger trigger = getScheduler().getTrigger(
						triggerNames[nameIndex], triggerGroups[groupIndex]);
				
				if (trigger.getJobGroup().equals(p_jobGroup)
						&& trigger.getJobName().equals(p_jobName)) {
					return trigger;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Immediately executes the specified job.
	 * @param p_jobName - job name.
	 * @param p_jobGroup - job group.
	 * @throws SchedulerException when something goes wrong.
	 */
	
	public static void executeNow(String p_jobName, String p_jobGroup)
	throws SchedulerException {
		
		getScheduler().triggerJob(p_jobName, p_jobGroup);
		
	}
	
	/**
	 * Returns the scheduler.
	 * @return a scheduler.
	 */
	
	private static Scheduler getScheduler() {
		return m_quartzScheduler;
	}
	
	/**
	 * Create the Scheduler instance for the scheduler name using the standard 
	 * Quartz scheduler factory.
	 * @param schedulerName - the name of the scheduler to create
	 * @return true if the QuartzScheduler started successfully
	 * @throws SchedulerException if thrown by Quartz methods
	 */
	
	private Scheduler createScheduler(String p_schedulerName) {
		try {
			SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
			if (p_schedulerName != null && p_schedulerName.trim().length() < 1) {
				
				m_quartzScheduler = schedulerFactory
				.getScheduler(p_schedulerName);
			} else {
				m_quartzScheduler = schedulerFactory.getScheduler();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m_quartzScheduler;
	}
	
	/**
	 * Starts the QuartzScheduler's threads that fire the Triggers
	 * @param schedulerName - the name of the scheduler to create
	 * @return true if the QuartzScheduler started successfully
	 * @throws SchedulerException if thrown by Quartz methods
	 */
	
	private boolean startScheduler(String p_schedulerName) {
		
		try {
			
			createScheduler(p_schedulerName).start();
			LogUtil.logMessage(getScheduler().getSchedulerName()
					+ " Scheduler  Successfully Started");
		} catch (Exception e) {
			
			e.printStackTrace();
			LogUtil.logMessage("Scheduler Cannot be  Started");
		}
		return true;
	}
	
	public boolean defaultScheduler() throws SchedulerException {
		
		SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
		
		m_quartzScheduler = schedulerFactory.getScheduler();
		
		LogUtil.logMessage("SchedulerController:defaultScheduler:"
				+ getScheduler().getSchedulerName()
				+ " Scheduler  Successfully Started");
		
		// Initiate JobDetail with job name, job group, and executable job class
		
		JobDetail jobDetail = new JobDetail(
				"mail",
				"MailingService",
				com.EMD.LSDB.common.framework.notificationservice.MailService.class);
		
		// Initiate CronTrigger with its name and group name
		
		CronTrigger cronTrigger = new CronTrigger("cronTrigger",
		"MailingService");
		try {
			
			// Assign the CronExpression to CronTrigger(This Cron Expression Trigger's The Job for every Two Minutes)
			
			cronTrigger.setCronExpression("0 0/2 * * * ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// schedule a job with JobDetail and Trigger
		
		m_quartzScheduler.scheduleJob(jobDetail, cronTrigger);
		
		m_quartzScheduler.start();
		
		return true;
	}
	
	/**
	 * Starts the Default's QuartzScheduler's threads that fire the Triggers
	 * @param 
	 * @return true if the QuartzScheduler started successfully
	 * @throws SchedulerException if thrown by Quartz methods
	 */
	
	private boolean startDefaultScheduler() {
		
		try {
			
			LogUtil
			.logMessage("Inside SchedulerController:startDefaultScheduler:");
			defaultScheduler();
			LogUtil.logMessage(getScheduler().getSchedulerName()
					+ " Scheduler  Successfully Started");
		} catch (Exception e) {
			
			e.printStackTrace();
			LogUtil.logMessage("Scheduler Cannot be  Started");
		}
		return true;
	}
}
