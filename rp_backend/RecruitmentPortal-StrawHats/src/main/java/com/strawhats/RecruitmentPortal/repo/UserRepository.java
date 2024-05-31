package com.strawhats.RecruitmentPortal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strawhats.RecruitmentPortal.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}


