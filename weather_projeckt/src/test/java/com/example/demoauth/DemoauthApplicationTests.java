package com.example.demoauth;

import com.example.demoauth.controllers.AdminController;
import com.example.demoauth.repository.CitiesRepository;
import com.example.demoauth.repository.UserRepository;
import com.example.demoauth.service.AdminService;
import com.example.demoauth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DemoauthApplicationTests.class)
class DemoauthApplicationTests {

	@MockBean
	private AdminController adminController;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private CitiesRepository citiesRepository;

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

	@Test
	void hasIndieAuthControllerConfigured(ApplicationContext context) {
		assertThat(context.getBean(AdminController.class)).isNotNull();
	}
}
