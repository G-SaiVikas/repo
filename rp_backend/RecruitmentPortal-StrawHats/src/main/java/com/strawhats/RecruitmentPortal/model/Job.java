package com.strawhats.RecruitmentPortal.model;

import java.util.List;

import com.strawhats.RecruitmentPortal.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
 
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String company;
	
	private String jobTitle;
	
	private String jobDescription;
	
	private String skillsRequired;
	
	private String location;
	
	private String salary;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	  @JoinTable(name = "job_applicants",
	      joinColumns = @JoinColumn(name = "job_id"),
	      inverseJoinColumns = @JoinColumn(name = "user_id"))
	  private List<User> applicants;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	  @JoinTable(name = "savedJob_applicants",
	      joinColumns = @JoinColumn(name = "job_id"),
	      inverseJoinColumns = @JoinColumn(name = "user_id"))
	  private List<User> savedJobsApplicants;
}