package com.votingsystem;

import com.votingsystem.model.User;
import com.votingsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VotingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.votingsystem.VotingsystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserService userService) {
		return (args) -> {
			// Create an admin user if it doesn't exist
			if (userService.findByEmail("admin@example.com") == null) {
				User admin = new User();
				admin.setNombre("Admin");
				admin.setCorreo("admin@example.com");
				admin.setContrasena("adminpassword"); // In a real app, use proper password hashing
				admin.setRol("administrador");
				userService.save(admin);
			}
		};
	}
}

