package com.example.demo.Service;

import com.example.demo.Entity.ServiceType;
import com.example.demo.Repository.ServiceTypeRepository;
import com.example.demo.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository, BookingRepository bookingRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<ServiceType> findAll() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public List<ServiceType> findAllByIds(List<Long> ids) {
        return serviceTypeRepository.findAllById(ids);
    }

    @Override
    public Optional<ServiceType> findById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    @Override
    public ServiceType save(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public void deleteById(Long id) {
        serviceTypeRepository.deleteById(id);
    }

    // Additional methods needed by ServiceController
    @Override
    public List<ServiceType> getAllServiceTypes() {
        return findAll();
    }

    @Override
    public ServiceType createServiceType(ServiceType serviceType) {
        return save(serviceType);
    }

    @Override
    public ServiceType getServiceTypeById(Long id) {
        return findById(id).orElse(null);
    }

    @Override
    public ServiceType updateServiceType(Long id, ServiceType serviceType) {
        return serviceTypeRepository.findById(id)
            .map(existingService -> {
                existingService.setName(serviceType.getName());
                existingService.setDescription(serviceType.getDescription());
                existingService.setPrice(serviceType.getPrice());
                existingService.setDuration(serviceType.getDuration());
                existingService.setAvailable(serviceType.isAvailable());
                return serviceTypeRepository.save(existingService);
            }).orElse(null);
    }

    @Override
    public void deleteServiceType(Long id) {
        deleteById(id);
    }

    @Override
    public boolean hasRelatedBookings(Long serviceId) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(serviceId);
        if (serviceType.isPresent()) {
            boolean hasBookings = bookingRepository.existsByServiceType(serviceType.get());
            System.out.println("Service ID: " + serviceId + ", Name: " + serviceType.get().getName() + ", Has related bookings: " + hasBookings);
            return hasBookings;
        }
        return false;
    }

    @Override
    @Transactional
    public void toggleAvailability(Long id) {
        serviceTypeRepository.findById(id).ifPresent(service -> {
            service.setAvailable(!service.isAvailable());
            serviceTypeRepository.save(service);
        });
    }
}
