package com.example.demo.Service;

import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public long getTotalBookings() {
        return bookingRepository.count();
    }

    @Override
    public long getTotalVehicles() {
        return vehicleRepository.count();
    }

    @Override
    public long getTotalServiceTypes() {
        return serviceRepository.count();
    }
}
