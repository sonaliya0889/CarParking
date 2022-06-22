package com.car.parking;
 
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
 
@SpringBootApplication
@EnableScheduling
public class CarParkingApplication
{
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("readCSVFileJob")
    Job readCSVFileJob;

    @Autowired
    @Qualifier("readRESTJob")
    Job readRESTJob;
      
    public static void main(String[] args)
    {
        SpringApplication.run(CarParkingApplication.class, args);
    }
      
    @Scheduled(fixedRate = 900000) //relaunch in evey 15 min
    public void perform() throws Exception
    {
        JobParameters readCSVFileParams = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        JobParameters readRESParams = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(readRESTJob, readRESParams);
        jobLauncher.run(readCSVFileJob, readCSVFileParams);
    }
}