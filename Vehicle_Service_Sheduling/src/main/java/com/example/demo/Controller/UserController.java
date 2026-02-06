package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            User user = userService.findByEmail(email).orElse(null);
            
            if (user == null) {
                return "redirect:/login";
            }
            
            model.addAttribute("user", user);
            return "profile";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/profile/update")
    public String showUpdateProfileForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email).orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "users/update-profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            User existingUser = userService.findByEmail(email).orElse(null);
            
            if (existingUser == null) {
                redirectAttributes.addFlashAttribute("error", "User not found");
                return "redirect:/profile";
            }
            
            // Update only the allowed fields
            existingUser.setUsername(user.getUsername());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());
            
            userService.updateUser(existingUser);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update profile: " + e.getMessage());
        }
        return "redirect:/profile";
    }

    @GetMapping("/change-password")
    public String changePasswordForm(Model model) {
        model.addAttribute("user", new User());
        return "users/change_password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                               @RequestParam("newPassword") String newPassword,
                               @RequestParam("confirmPassword") String confirmPassword,
                               RedirectAttributes redirectAttributes) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            User user = userService.findByEmail(email).orElse(null);

            if (user == null) {
                redirectAttributes.addFlashAttribute("error", "User not found");
                return "redirect:/user/change-password";
            }

            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "Current password is incorrect");
                return "redirect:/user/change-password";
            }

            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "New passwords do not match");
                return "redirect:/user/change-password";
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("success", "Password changed successfully");
            return "redirect:/user/profile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to change password: " + e.getMessage());
            return "redirect:/user/change-password";
        }
    }

    @PostMapping("/users/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Get the current authenticated user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = auth.getName();
            User currentUser = userService.findByEmail(currentUserEmail).orElse(null);
            
            // Prevent admin from deleting themselves
            if (currentUser != null && currentUser.getId().equals(id)) {
                redirectAttributes.addFlashAttribute("error", "You cannot delete your own account");
                return "redirect:/admin/dashboard";
            }
            
            // Check if user has related bookings
            if (userService.hasRelatedBookings(id)) {
                redirectAttributes.addFlashAttribute("error", "Cannot delete user. User has related bookings. Please delete the bookings first.");
                return "redirect:/admin/dashboard";
            }
            
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("success", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete user: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/users/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/users/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@Valid @ModelAttribute("newUser") User user,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Log validation errors for debugging
            result.getAllErrors().forEach(error -> {
                System.out.println("Validation error: " + error.getDefaultMessage());
            });
            redirectAttributes.addFlashAttribute("error", "Please check the form data. All fields are required.");
            return "redirect:/admin/dashboard";
        }

        try {
            // Check if email already exists
            if (userService.findByEmail(user.getEmail()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Email already exists. Please use a different email.");
                return "redirect:/admin/dashboard";
            }

            // Check if username already exists
            if (userService.usernameExists(user.getUsername())) {
                redirectAttributes.addFlashAttribute("error", "Username already exists. Please use a different username.");
                return "redirect:/admin/dashboard";
            }

            // Validate password length
            if (user.getPassword() == null || user.getPassword().length() < 6) {
                redirectAttributes.addFlashAttribute("error", "Password must be at least 6 characters long.");
                return "redirect:/admin/dashboard";
            }

            // Encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Set default values if not provided
            if (user.getRole() == null || user.getRole().trim().isEmpty()) {
                user.setRole("USER");
            } else {
                user.setRole(user.getRole().toUpperCase());
            }
            
            // Set default values for optional fields
            if (user.getPhone() == null) {
                user.setPhone("");
            }
            if (user.getAddress() == null) {
                user.setAddress("");
            }
            
            // Save the user
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("success", "User '" + user.getUsername() + "' added successfully with role '" + user.getRole() + "'!");
        } catch (Exception e) {
            System.err.println("Error adding user: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add user: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/users/test-add")
    @PreAuthorize("hasRole('ADMIN')")
    public String testAddUser(RedirectAttributes redirectAttributes) {
        try {
            User testUser = new User();
            testUser.setUsername("testuser" + System.currentTimeMillis());
            testUser.setEmail("test" + System.currentTimeMillis() + "@example.com");
            testUser.setPassword(passwordEncoder.encode("password123"));
            testUser.setRole("USER");
            testUser.setPhone("");
            testUser.setAddress("");
            
            userService.saveUser(testUser);
            redirectAttributes.addFlashAttribute("success", "Test user created successfully: " + testUser.getUsername());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Test user creation failed: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/dashboard";
    }
}
