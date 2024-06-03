package com.strawhats.RecruitmentPortal.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MailStructure {
	private String subject;
	private String message;
}
