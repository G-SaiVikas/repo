package com.strawhats.RecruitmentPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    public JobService jobService;

    @GetMapping("/getjobs")
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/searchJob")
    public List<JobDTO> searchJob(@RequestParam String skill) {
        return jobService.searchJob(skill);
    }
}
