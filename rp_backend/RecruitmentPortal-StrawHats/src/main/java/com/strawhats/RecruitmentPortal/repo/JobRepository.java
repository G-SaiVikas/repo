package com.strawhats.RecruitmentPortal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.User;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
