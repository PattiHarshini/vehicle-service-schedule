package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Entity.VehicleBrand;
import com.example.demo.Entity.VehicleModel;
import com.example.demo.Entity.Booking;
import com.example.demo.Service.BookingService;
import com.example.demo.Service.ServiceTypeService;
import com.example.demo.Service.UserService;
import com.example.demo.Service.VehicleService;
import com.example.demo.Service.VehicleBrandService;
import com.example.demo.Service.VehicleModelService;
import com.example.demo.dto.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final VehicleService vehicleService;
    private final ServiceTypeService serviceTypeService;
    private final UserService userService;
    private final VehicleBrandService vehicleBrandService;
    private final VehicleModelService vehicleModelService;

    @Autowired
    public BookingController(BookingService bookingService, VehicleService vehicleService, 
                           ServiceTypeService serviceTypeService, UserService userService,
                           VehicleBrandService vehicleBrandService, VehicleModelService vehicleModelService) {
        this.bookingService = bookingService;
        this.vehicleService = vehicleService;
        this.serviceTypeService = serviceTypeService;
        this.userService = userService;
        this.vehicleBrandService = vehicleBrandService;
        this.vehicleModelService = vehicleModelService;
    }

    @GetMapping("/new")
    public String showBookingForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found while showing booking form"));
        
        model.addAttribute("bookingRequest", new BookingRequest());
        model.addAttribute("vehicles", vehicleService.getVehiclesByUser(user));
        model.addAttribute("serviceTypes", serviceTypeService.getAllServiceTypes());
        model.addAttribute("vehicleBrands", vehicleBrandService.getAllBrands());
        return "booking/book_service";
    }

    @GetMapping("/api/models")
    public ResponseEntity<List<VehicleModel>> getModelsByBrand(@RequestParam Long brandId) {
        List<VehicleModel> models = vehicleModelService.getModelsByBrandId(brandId);
        return ResponseEntity.ok(models);
    }

    @PostMapping("/new")
    public String processBooking(@ModelAttribute("bookingRequest") BookingRequest bookingRequest,
                                 Authentication authentication,
                                 RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        try {
            Booking savedBooking = bookingService.createBooking(bookingRequest, user);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Service booked successfully! Booking ID: " + savedBooking.getId() + 
                " - " + savedBooking.getServiceType().getName() + 
                " for " + savedBooking.getVehicleBrand() + " " + savedBooking.getVehicleModel());
            return "redirect:/dashboard/user";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("bookingRequest", bookingRequest);
            return "redirect:/booking/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            redirectAttributes.addFlashAttribute("bookingRequest", bookingRequest);
            return "redirect:/booking/new";
        }
    }

    @GetMapping("/list")
    public String showUserBookings(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found while showing bookings"));
        
        model.addAttribute("bookings", bookingService.findActiveBookingsByUser(user));
        return "booking/booking_list";
    }

    @GetMapping("/history")
    public String showUserBookingHistory(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found while showing booking history"));
        
        model.addAttribute("bookings", bookingService.findByUser(user));
        return "booking/history";
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        try {
            bookingService.cancelBooking(id);
            redirectAttributes.addFlashAttribute("successMessage", "Booking cancelled successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while cancelling the booking.");
        }
        
        return "redirect:/booking/list";
    }
}