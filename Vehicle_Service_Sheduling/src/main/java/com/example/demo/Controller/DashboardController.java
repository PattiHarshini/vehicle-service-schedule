package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.Service.ServiceTypeService;
import com.example.demo.Service.BookingService;
import com.example.demo.Entity.ServiceType;
import com.example.demo.Entity.Booking;
import com.example.demo.Service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/dashboard/user")
    public String userDashboard(Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // Get logged-in email

        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not found. Please log in again.");
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        // Add recent bookings and vehicles
        model.addAttribute("bookings", bookingService.findActiveBookingsByUser(user));
        model.addAttribute("vehicles", vehicleService.getVehiclesByUser(user));
        return "dashboard/user_dashboard";  // Thymeleaf template path
    }

    @GetMapping("/dashboard/admin")
    public String adminDashboard(Model model) {
        List<User> users = userService.getAllUsers();
        List<ServiceType> services = serviceTypeService.getAllServiceTypes();
        List<Booking> bookings = bookingService.findAllActiveBookings();
        model.addAttribute("users", users);
        model.addAttribute("services", services);
        model.addAttribute("bookings", bookings);
        model.addAttribute("newService", new ServiceType());
        model.addAttribute("newUser", new User());
        return "dashboard/admin_dashboard";
    }
}
