package com.example.demo.Controller;

import com.example.demo.Entity.ServiceType;
import com.example.demo.Service.ServiceTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping
    public String listServices(Model model) {
        try {
            model.addAttribute("services", serviceTypeService.getAllServiceTypes());
            return "services/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load services: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/add")
    public String addServiceForm(Model model) {
        model.addAttribute("service", new ServiceType());
        return "services/add";
    }

    @PostMapping("/save")
    public String saveService(@Valid @ModelAttribute("service") ServiceType service,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "services/add";
        }

        try {
            serviceTypeService.createServiceType(service);
            redirectAttributes.addFlashAttribute("success", "Service added successfully!");
            return "redirect:/services";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save service: " + e.getMessage());
            return "redirect:/services/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editService(@PathVariable Long id, Model model) {
        try {
            ServiceType service = serviceTypeService.getServiceTypeById(id);
            model.addAttribute("service", service);
            return "services/add";
        } catch (Exception e) {
            model.addAttribute("error", "Service not found");
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateService(@PathVariable Long id,
                              @Valid @ModelAttribute("service") ServiceType service,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "services/add";
        }

        try {
            service.setId(id);
            serviceTypeService.updateServiceType(id, service);
            redirectAttributes.addFlashAttribute("success", "Service updated successfully!");
            return "redirect:/services";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update service: " + e.getMessage());
            return "redirect:/services/edit/" + id;
        }
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteService(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Check if service has related bookings
            if (serviceTypeService.hasRelatedBookings(id)) {
                redirectAttributes.addFlashAttribute("error", "Cannot delete service. Service has related bookings. Please delete the bookings first.");
                return "redirect:/admin/dashboard";
            }
            
            serviceTypeService.deleteServiceType(id);
            redirectAttributes.addFlashAttribute("success", "Service deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete service: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/toggle-availability/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String toggleAvailability(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            serviceTypeService.toggleAvailability(id);
            redirectAttributes.addFlashAttribute("success", "Service availability updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update service availability.");
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addServiceFromAdmin(@Valid @ModelAttribute("newService") ServiceType newService,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        System.out.println("Received new service: " + newService);
        if (result.hasErrors()) {
            // Collect all validation error messages
            String errors = result.getAllErrors()
                                .stream()
                                .map(e -> e.getDefaultMessage())
                                .collect(Collectors.joining(", "));
            System.out.println("Validation errors: " + errors);
            redirectAttributes.addFlashAttribute("error", "Failed to add service. Please correct the errors: " + errors);
                return "redirect:/admin/dashboard";
            }
            
        try {
            // By default, a new service should be available
            newService.setAvailable(true);
            serviceTypeService.createServiceType(newService);
            System.out.println("Service saved successfully: " + newService);
            redirectAttributes.addFlashAttribute("success", "Service added successfully!");
        } catch (Exception e) {
            System.out.println("Exception while saving service: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to add service: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    // Debug endpoint to check bookings for a service
    @GetMapping("/debug/bookings/{serviceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String debugServiceBookings(@PathVariable Long serviceId, Model model) {
        try {
            ServiceType service = serviceTypeService.getServiceTypeById(serviceId);
            if (service != null) {
                model.addAttribute("service", service);
                model.addAttribute("hasRelatedBookings", serviceTypeService.hasRelatedBookings(serviceId));
                return "service/debug_bookings";
            } else {
                model.addAttribute("error", "Service not found");
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "error";
        }
    }
}
