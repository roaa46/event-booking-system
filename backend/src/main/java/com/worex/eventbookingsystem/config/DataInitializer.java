package com.worex.eventbookingsystem.config;

import com.worex.eventbookingsystem.constant.Role;
import com.worex.eventbookingsystem.model.Person;
import com.worex.eventbookingsystem.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initSuperAdmin(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String superAdminEmail = "superadmin@system.com";

            if (personRepository.findByEmail(superAdminEmail).isEmpty()) {
                Person superAdmin = new Person();
                superAdmin.setFirstName("Super");
                superAdmin.setLastName("Admin");
                superAdmin.setUsername("superadmin");
                superAdmin.setEmail(superAdminEmail);
                superAdmin.setPhone("01000000000");
                superAdmin.setPassword(passwordEncoder.encode("Admin@123"));
                superAdmin.setRole(Role.ADMIN);

                personRepository.save(superAdmin);
                System.out.println("Super Admin created: " + superAdminEmail);
            } else {
                System.out.println("Super Admin already exists, skipping creation.");
            }
        };
    }
}
