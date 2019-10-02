package com.alex.che.stateoflowa.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/")
public class VotersController {

    private JobLauncher jobLauncher;
    private Job job;
    private VoterService voterService;

    @Autowired
    public VotersController(JobLauncher jobLauncher,
                            Job job,
                            VoterService voterService) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.voterService = voterService;
    }

    @PostConstruct
    public void performJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }

    @GetMapping("/get_voters")
    public ResponseEntity<MonthlyVoterRegistration> getBookById(@RequestParam(name = "county", required = false) String county,
                                                                @RequestParam(name = "month", required = false) Integer month,
                                                                @RequestParam(name = "party", required = false) String party,
                                                                @RequestParam(name = "active_status", required = false) String active_status,
                                                                @RequestParam(name = "limit", required = false) Integer limit) {
        voterService.getVotersByParams(county, month, party, active_status, limit);
        return ResponseEntity.ok(null);
    }
}
