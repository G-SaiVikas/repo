package com.strawhats.RecruitmentPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
    private JobService jobService;
	
	@GetMapping("/getjobs")
	public List<Job> getAllJobs() {
		return jobService.getAllJobs();
	}
	
	@GetMapping("/searchJob")
	public List<Job> searchJob(@RequestParam String skill) {
		return jobService.searchJob(skill);
	}
	
}
