package com.strawhats.RecruitmentPortal.api.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.strawhats.RecruitmentPortal.api.config.TestSecurityConfig;
import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.service.JobService;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)

public class JobServiceTest {
	
	@Mock
	private JobRepository jobRepository;
	
	@InjectMocks
	private JobService jobService;

	private Job job1;
	
	private Job job2;
	
	@BeforeEach
	void init() {
		this.job1 = Job.builder()
				.company("Test Company")
				.jobDescription("Test description")
				.jobTitle("Test title")
				.skillsRequired("Test1, test2, test3")
				.location("test city")
				.salary("10000").build();
		this.job2 = Job.builder()
				.company("Test2 Company")
				.jobDescription("Test2 description")
				.jobTitle("Test2 title")
				.skillsRequired("Test1, test2, test3")
				.location("test city")
				.salary("10000").build();
	
	}
	
	@Test
	public void testRegisterJob() {
        when(jobRepository.save(Mockito.any(Job.class))).thenReturn(job1);
        
        Job registeredJob = jobService.registerJob(job1);
        
        Assertions.assertThat(registeredJob).isNotNull();

	}
	
	@Test
	public void testGetAllJobs() {
		when(jobRepository.findAll()).thenReturn(List.of(job1, job2));
		
		List<JobDTO> jobs = jobService.getAllJobs();
		
		Assertions.assertThat(jobs).isNotNull();
	}
	
	@Test
    public void testSearchJobsBySkill() {
        when(jobRepository.findBySkillsRequiredContaining("test2")).thenReturn(List.of(job1, job2));
        
        List<JobDTO> jobs = jobService.searchJob("test2");
        
        Assertions.assertThat(jobs).isNotNull();
        Assertions.assertThat(jobs).hasSize(2);
        Assertions.assertThat(jobs).contains(jobService.convertToDTO(job1), jobService.convertToDTO(job2));
    }
	
}