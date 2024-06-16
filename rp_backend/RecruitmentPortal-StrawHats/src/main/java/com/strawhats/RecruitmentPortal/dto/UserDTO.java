package com.strawhats.RecruitmentPortal.dto;
import java.sql.Date;
import java.util.List;

import com.strawhats.RecruitmentPortal.model.Job;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
    private Long id;
    
    private String fullName;
    
    private String email;
    
    private String phoneNumber;
    
    private byte[] profilePic;

    private byte[] resume; 
    
    private Date dateOfBirth;
    
}
