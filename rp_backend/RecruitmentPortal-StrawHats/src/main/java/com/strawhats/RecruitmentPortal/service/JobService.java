package com.strawhats.RecruitmentPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.repo.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    
    public Job registerJob(Job job) {
        return jobRepository.save(job);
    }
    
    public List<Job> getAllJobs() {
    	return jobRepository.findAll();
    }
    public List<Job> searchJob(String skill){
    	return jobRepository.findBySkillsRequiredContaining(skill);
    }
    
}
