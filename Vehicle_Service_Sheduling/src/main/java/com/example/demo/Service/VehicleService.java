package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    // Create a new vehicle
    Vehicle createVehicle(Vehicle vehicle);

    // Fetch all vehicles (admin purpose)
    List<Vehicle> getAllVehicles();

    // Get single vehicle by ID
    Vehicle getVehicleById(Long id);

    // Update a vehicle
    Vehicle updateVehicle(Long id, Vehicle updatedVehicle);

    // Delete a vehicle by ID
    void deleteVehicle(Long id);

    // Get vehicles linked to a specific user
    List<Vehicle> getVehiclesByUser(User user);

    // Get vehicles by username
    List<Vehicle> getVehiclesByUsername(String username);

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);
}
