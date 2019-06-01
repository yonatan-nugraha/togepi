package com.example.togepi.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class CSVUserJobListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(CSVUserJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Before job execution");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("After job execution");
    }
}