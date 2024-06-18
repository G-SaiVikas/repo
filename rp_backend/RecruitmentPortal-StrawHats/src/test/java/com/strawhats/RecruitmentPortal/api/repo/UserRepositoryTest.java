// package com.strawhats.RecruitmentPortal.api.repo;

// import java.util.List;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.context.ActiveProfiles;

// import com.strawhats.RecruitmentPortal.model.User;
// import com.strawhats.RecruitmentPortal.repo.JobRepository;
// import com.strawhats.RecruitmentPortal.repo.UserRepository;

// @DataJpaTest
// @ActiveProfiles("test")
// //@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class UserRepositoryTest {
	
// 	@Autowired
// 	private UserRepository userRepository;
	
// 	@Autowired
// 	private JobRepository jobRepository;
	
// 	@Test
// 	public void UserRepository_SaveAll_ReturnSavedUser() {
		
// 		//		Arrange
		
// 		User testUser = User.builder()
// 								  .fullName("Test test")
// 								  .email("test@test.com")
// 								  .password("test")
// 								  .phoneNumber("123456789")
// 								  .build();
		
// 		//		Act
		
// 		User savedUser = userRepository.save(testUser);
		
// 		//		Assert
		
// 		Assertions.assertThat(savedUser).isNotNull();
// 		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
// 	}
// 	@Test
// 	public void testGetAllUsers() {
// 		User user1 = User.builder()
// 				  .fullName("Test1 test1")
// 				  .email("test@test.com")
// 				  .password("test")
// 				  .phoneNumber("123456789")
// 				  .build();
		
// 		User user2 = User.builder()
// 				  .fullName("Test2 test2")
// 				  .email("test@test.com")
// 				  .password("test")
// 				  .phoneNumber("123456789")
// 				  .build();
		
// 		 user1 = userRepository.save(user1);
// 		 user2 = userRepository.save(user2);
		 
// 		 List<User> users = userRepository.findAll();
		 
// 		 Assertions.assertThat(users).isNotNull();
// 		 Assertions.assertThat(users.size()).isEqualTo(2);

// 	}

// }