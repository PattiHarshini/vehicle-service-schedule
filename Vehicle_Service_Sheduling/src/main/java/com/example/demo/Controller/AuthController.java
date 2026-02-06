package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email is already registered.");
            return "register";
        }

        try {
            // Ensure role is one of the allowed values
            String role = user.getRole();
            if (role == null || (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN"))) {
                role = "USER";
            }
            user.setRole(role.toUpperCase());

            // Encode password securely
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Save user
            userService.saveUser(user);

            // Set success message
            model.addAttribute("success", "Registered successfully! You can now log in.");

            // Clear form
            model.addAttribute("user", new User());

            return "redirect:/login"; // Redirect to login page
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Try again.");
            return "register";
        }
    }
    


    @GetMapping("/logout-success")
    public String logout() {
        return "logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access_denied";
    }
}