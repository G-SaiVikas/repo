package com.strawhats.RecruitmentPortal.model;

import java.util.List;

import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
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
}