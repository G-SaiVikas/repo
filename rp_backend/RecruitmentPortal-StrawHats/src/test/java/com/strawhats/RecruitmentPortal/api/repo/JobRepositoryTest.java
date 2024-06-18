// package com.strawhats.RecruitmentPortal.api.repo;

// import java.util.List;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.context.ActiveProfiles;

// import com.strawhats.RecruitmentPortal.model.Job;
// import com.strawhats.RecruitmentPortal.repo.JobRepository;

// @DataJpaTest
// @ActiveProfiles("test")
// class JobRepositoryTest {
	
// @Autowired
// 	private JobRepository jobRepository;

// 	@Test
// 	public void testApplyJobsRepo() {
// 		Job job = Job.builder()
// 					.company("Test Company")
// 					.jobDescription("Test description")
// 					.jobTitle("Test title")
// 					.skillsRequired("Test1, test2, test3")
// 					.location("test city")
// 					.salary("10000").build();
		
// 		Job savedJob = jobRepository.save(job);
		
// 		Assertions.assertThat(savedJob).isNotNull();
					
// 	}
	
// 	@Test
// 	public void testGetAllJobs() {
// 		Job job1 = Job.builder()
// 				.company("Test1 Company")
// 				.jobDescription("Test1 description")
// 				.jobTitle("Test1 title")
// 				.skillsRequired("Test1, test2, test3")
// 				.location("test1 city")
// 				.salary("10000").build();
		
// 		Job job2 = Job.builder()
// 				.company("Test2 Company")
// 				.jobDescription("Test2 description")
// 				.jobTitle("Test2 title")
// 				.skillsRequired("Test1, test2, test3")
// 				.location("test2 city")
// 				.salary("10000").build();
		
// 		Job savedJob1 = jobRepository.save(job1);
// 		Job savedJob2 = jobRepository.save(job2);
		
// 		List<Job> jobs = jobRepository.findAll();
		
// 		Assertions.assertThat(jobs.size()).isEqualTo(2);
// 	}

// 	@Test 
// 	public void testSearchJobBySkill() {
		
// 		Job job = Job.builder()
// 				.company("Test Company")
// 				.jobDescription("Test description")
// 				.jobTitle("Test title")
// 				.skillsRequired("Test1, test2, test3")
// 				.location("test city")
// 				.salary("10000").build();
		
// 		List<Job> retrievedJobs = jobRepository.findBySkillsRequiredContaining("test1");
// 		Assertions.assertThat(retrievedJobs).isNotNull();
// 	}
	
// }
