package com.strawhats.RecruitmentPortal.api.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strawhats.RecruitmentPortal.api.config.TestSecurityConfig;
import com.strawhats.RecruitmentPortal.controller.JobController;
import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.security.JWTGenerator;
import com.strawhats.RecruitmentPortal.service.JobService;
import com.strawhats.RecruitmentPortal.service.MailService;
import com.strawhats.RecruitmentPortal.service.UserService;

@WebMvcTest(controllers = JobController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class JobControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Autowired
    private ObjectMapper objectMapper;
   
    @MockBean
    private JobRepository jobRepository;
    
    @InjectMocks
    private JobController jobController;
    
    private Job job1; 
    
    private Job job2;
    
    private List<Job> jobList;
    
    
    @BeforeEach
    void init() {
    	job1 = Job.builder()
                .id(1L)
                .company("Company1")
                .jobDescription("Description1")
                .jobTitle("Title1")
                .skillsRequired("Skill1, Skill2")
                .location("City1")
                .salary("10000")
                .build();

        job2 = Job.builder()
                .id(2L)
                .company("Company2")
                .jobDescription("Description2")
                .jobTitle("Title2")
                .skillsRequired("Skill3, Skill4")
                .location("City2")
                .salary("20000")
                .build();

        jobList = Arrays.asList(job1, job2);
        
        jobController.jobService = jobService;
        
    }
    
    @Test
    void testGetJobs() throws Exception {
    	
    	JobDTO j1 = JobDTO.builder()
                .id(job1.getId())
                .company(job1.getCompany())
                .jobTitle(job1.getJobTitle())
                .jobDescription(job1.getJobDescription())
                .skillsRequired(job1.getSkillsRequired())
                .location(job1.getLocation())
                .salary(job1.getSalary())
                .build();

        JobDTO j2 = JobDTO.builder()
                .id(job2.getId())
                .company(job2.getCompany())
                .jobTitle(job2.getJobTitle())
                .jobDescription(job2.getJobDescription())
                .skillsRequired(job2.getSkillsRequired())
                .location(job2.getLocation())
                .salary(job2.getSalary())
                .build();
        
        List<JobDTO> jobDTOList = List.of(j1, j2);
        
        when(jobService.getAllJobs()).thenReturn(jobDTOList);
        
        mockMvc.perform(get("/jobs/getjobs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company").value("Company1"))
                .andExpect(jsonPath("$[1].company").value("Company2"));
    }
    
    
    
    @Test
    void testSearchJobBySkill() throws Exception {
    	JobDTO j1 = JobDTO.builder()
                .id(job1.getId())
                .company(job1.getCompany())
                .jobTitle(job1.getJobTitle())
                .jobDescription(job1.getJobDescription())
                .skillsRequired(job1.getSkillsRequired())
                .location(job1.getLocation())
                .salary(job1.getSalary())
                .build();

        JobDTO j2 = JobDTO.builder()
                .id(job2.getId())
                .company(job2.getCompany())
                .jobTitle(job2.getJobTitle())
                .jobDescription(job2.getJobDescription())
                .skillsRequired(job2.getSkillsRequired())
                .location(job2.getLocation())
                .salary(job2.getSalary())
                .build();
        
        List<JobDTO> jobDTOList = List.of(j1, j2);
    	
    	when(jobService.searchJob("Skill1")).thenReturn(jobDTOList);
    	
    	mockMvc.perform(get("/jobs/searchJob")
    			.param("skill", "Skill1")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$[0].skillsRequired").value("Skill1, Skill2"));
    }
    
    
}