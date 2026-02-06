package com.example.demo.config;

import com.example.demo.Entity.ServiceType;
import com.example.demo.Entity.Booking;
import com.example.demo.Repository.ServiceRepository;
import com.example.demo.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ServiceDataInitializer implements CommandLineRunner {

    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void run(String... args) {
        // Clean up any existing cancelled bookings
        cleanupCancelledBookings();
        
        // Only initialize if no services exist
        if (serviceRepository.count() == 0) {
            List<ServiceType> services = Arrays.asList(
                createService("Basic Oil Change", "Complete engine oil change with filter replacement", 999.00, "30 mins"),
                createService("Full Service", "Complete vehicle inspection and maintenance", 2499.00, "2 hours"),
                createService("Brake Service", "Brake pad replacement and system check", 1999.00, "1 hour"),
                createService("AC Service", "AC system cleaning and gas refill", 1499.00, "1 hour"),
                createService("Battery Replacement", "Battery check and replacement if needed", 2499.00, "30 mins"),
                createService("Tire Rotation", "Tire rotation and balancing", 799.00, "45 mins"),
                createService("Wheel Alignment", "Four-wheel alignment check and adjustment", 1299.00, "1 hour"),
                createService("Transmission Service", "Transmission fluid change and system check", 3499.00, "2 hours"),
                createService("Engine Tune-up", "Spark plug replacement and engine optimization", 1999.00, "1.5 hours"),
                createService("Suspension Check", "Complete suspension system inspection", 1499.00, "1 hour"),
                createService("Fuel System Cleaning", "Fuel injector cleaning and system check", 1799.00, "1 hour"),
                createService("Electrical System Check", "Complete electrical system diagnosis and repair", 1299.00, "1 hour"),
                createService("Cooling System Service", "Coolant flush and system check", 999.00, "1 hour"),
                createService("Exhaust System Check", "Exhaust system inspection and repair", 1499.00, "1 hour"),
                createService("Steering System Service", "Power steering fluid change and system check", 1299.00, "1 hour")
            );
            
            for (ServiceType service : services) {
                serviceRepository.save(service);
            }
        }
    }
    
    private void cleanupCancelledBookings() {
        try {
            // Delete all cancelled bookings from the database
            List<Booking> cancelledBookings = bookingRepository.findByStatus(Booking.BookingStatus.CANCELLED);
            if (!cancelledBookings.isEmpty()) {
                bookingRepository.deleteAll(cancelledBookings);
                System.out.println("Cleaned up " + cancelledBookings.size() + " cancelled bookings from database.");
            }
        } catch (Exception e) {
            System.err.println("Error cleaning up cancelled bookings: " + e.getMessage());
        }
    }

    private ServiceType createService(String name, String description, double price, String duration) {
        ServiceType service = new ServiceType();
        service.setName(name);
        service.setDescription(description);
        service.setPrice(price);
        service.setDuration(duration);
        return service;
    }
} 