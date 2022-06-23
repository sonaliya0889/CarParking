package com.car.parking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
 
@SpringBootApplication
@EnableScheduling
public class CarParkingApplication {
    private static final Log logger = LogFactory.getLog(CarParkingApplication.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("readCSVFileJob")
    Job readCSVFileJob;

    @Autowired
    @Qualifier("readRESTJob")
    Job readRESTJob;
      
    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        SpringApplication app = new SpringApplication(CarParkingApplication.class);
        ConfigurableApplicationContext ctx = app.run(args);

        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        Job job = ctx.getBean("readCSVFileJob", Job.class);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        BatchStatus batchStatus = jobExecution.getStatus();
       logger.info(job.getName()+" batchStatus: "+batchStatus);
    }
      
    @Scheduled(initialDelay = 5000, fixedRate = 60000) //relaunch in evey 1 min
    public void perform() throws Exception {
        JobParameters readRESParams = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(readRESTJob, readRESParams);
    }
}