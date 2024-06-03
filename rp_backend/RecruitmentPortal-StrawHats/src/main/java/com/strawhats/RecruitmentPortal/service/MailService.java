package com.strawhats.RecruitmentPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.model.MailStructure;

@Service
public class MailService {
	
	public void sendMail(String mail, MailStructure mailStructure) {
	}
}
