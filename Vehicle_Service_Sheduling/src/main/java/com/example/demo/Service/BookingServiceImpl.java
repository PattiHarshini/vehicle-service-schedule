package com.example.demo.Service;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.ServiceType;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.ServiceTypeRepository;
import com.example.demo.Repository.VehicleRepository;
import com.example.demo.dto.BookingRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              VehicleRepository vehicleRepository,
                              ServiceTypeRepository serviceTypeRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    @Transactional
    public Booking createBooking(BookingRequest bookingRequest, User user) {
        ServiceType serviceType = serviceTypeRepository.findById(bookingRequest.getServiceTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid service type ID: " + bookingRequest.getServiceTypeId()));

        if (!serviceType.isAvailable()) {
            throw new IllegalArgumentException("Service '" + serviceType.getName() + "' is currently unavailable.");
        }

        LocalDateTime bookingDate = bookingRequest.getBookingDate();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setServiceType(serviceType);
        booking.setBookingDate(bookingDate);
        booking.setStatus(Booking.BookingStatus.PENDING);
        booking.setVehicleBrand(bookingRequest.getVehicleBrand().trim());
        booking.setVehicleModel(bookingRequest.getVehicleModel().trim());
        booking.setVehicleRegistrationNumber(bookingRequest.getVehicleRegistrationNumber().trim());
        booking.setNotes(bookingRequest.getNotes() != null ? bookingRequest.getNotes().trim() : null);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findAllActiveBookings() {
        return bookingRepository.findByStatusNot(Booking.BookingStatus.CANCELLED);
    }

    @Override
    public List<Booking> findActiveBookingsByUser(User user) {
        return bookingRepository.findByUserAndStatusNot(user, Booking.BookingStatus.CANCELLED);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + id));
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        this.deleteById(id);
    }
}
