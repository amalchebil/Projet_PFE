package com.example.demo;

import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.demo.entities.Role.ADMIN;
import static com.example.demo.entities.Role.SUPER_ADMIN;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	/*@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service, UserRepository userRepository
	) {
		return args -> {
			if(userRepository.findByEmail("super_admin@gmail.com").isEmpty()){
				var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.cin("123")
					.id_agence(25L)
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());}
		};}

*/













}
