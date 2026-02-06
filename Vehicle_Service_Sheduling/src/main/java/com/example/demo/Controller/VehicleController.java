package com.example.demo.Controller;

import com.example.demo.Entity.Vehicle;
import com.example.demo.Service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String vehicleList(Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            model.addAttribute("vehicles", vehicleService.getVehiclesByUsername(username));
            return "vehicles/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load vehicles: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/add")
    public String addVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicles/add";
    }

    @PostMapping("/add")
    public String saveVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "vehicles/add";
        }

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            vehicle.setUsername(auth.getName());
            vehicleService.createVehicle(vehicle);
            redirectAttributes.addFlashAttribute("success", "Vehicle added successfully!");
            return "redirect:/vehicles";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save vehicle: " + e.getMessage());
            return "redirect:/vehicles/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Vehicle vehicle = vehicleService.getVehicleById(id);
            
            if (!vehicle.getUsername().equals(username)) {
                model.addAttribute("error", "You don't have permission to edit this vehicle");
                return "error";
            }
            
            model.addAttribute("vehicle", vehicle);
            return "vehicles/add";
        } catch (Exception e) {
            model.addAttribute("error", "Vehicle not found");
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateVehicle(@PathVariable Long id,
                              @Valid @ModelAttribute("vehicle") Vehicle vehicle,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "vehicles/add";
        }

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Vehicle existingVehicle = vehicleService.getVehicleById(id);
            
            if (!existingVehicle.getUsername().equals(username)) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to update this vehicle");
                return "redirect:/vehicles";
            }
            
            vehicle.setId(id);
            vehicle.setUsername(username);
            vehicleService.updateVehicle(id, vehicle);
            redirectAttributes.addFlashAttribute("success", "Vehicle updated successfully!");
            return "redirect:/vehicles";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update vehicle: " + e.getMessage());
            return "redirect:/vehicles/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Vehicle vehicle = vehicleService.getVehicleById(id);
            
            if (!vehicle.getUsername().equals(username)) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this vehicle");
                return "redirect:/vehicles";
            }
            
            vehicleService.deleteVehicle(id);
            redirectAttributes.addFlashAttribute("success", "Vehicle deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete vehicle: " + e.getMessage());
        }
        return "redirect:/vehicles";
    }
}
