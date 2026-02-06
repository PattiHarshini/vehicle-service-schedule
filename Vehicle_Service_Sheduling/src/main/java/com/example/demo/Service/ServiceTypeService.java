package com.example.demo.Service;

import com.example.demo.Entity.ServiceType;
import java.util.List;
import java.util.Optional;

public interface ServiceTypeService {
    List<ServiceType> findAll();
    Optional<ServiceType> findById(Long id);
    ServiceType save(ServiceType serviceType);
    void deleteById(Long id);
    
    // Additional methods needed by ServiceController
    List<ServiceType> getAllServiceTypes();
    ServiceType createServiceType(ServiceType serviceType);
    ServiceType getServiceTypeById(Long id);
    ServiceType updateServiceType(Long id, ServiceType serviceType);
    void deleteServiceType(Long id);
    boolean hasRelatedBookings(Long serviceId);
    void toggleAvailability(Long id);
    List<ServiceType> findAllByIds(List<Long> ids);
}
