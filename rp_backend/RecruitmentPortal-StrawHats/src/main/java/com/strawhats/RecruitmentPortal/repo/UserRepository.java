package com.strawhats.RecruitmentPortal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User findByFullNameAndPhoneNumber(String fullName, String phoneNumber);
    List<Job> findByApplicants(User user);
}


