package com.example.demo.config;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create default admin user if it doesn't exist
        createDefaultAdmin();
    }
    
    private void createDefaultAdmin() {
        try {
            // Check if admin@gmail.com already exists
            if (!userRepository.findByEmail("admin@gmail.com").isPresent()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setEmail("admin@gmail.com");
                adminUser.setPassword(passwordEncoder.encode("1234"));
                adminUser.setRole("ADMIN");
                adminUser.setPhone("");
                adminUser.setAddress("");
                
                userRepository.save(adminUser);
                System.out.println("Default admin user created: admin@gmail.com / 1234");
            } else {
                System.out.println("Default admin user already exists");
            }
        } catch (Exception e) {
            System.err.println("Error creating default admin user: " + e.getMessage());
        }
    }
} 