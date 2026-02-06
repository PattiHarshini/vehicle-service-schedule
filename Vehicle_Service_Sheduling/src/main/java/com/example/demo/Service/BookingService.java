package com.example.demo.Service;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.User;
import com.example.demo.dto.BookingRequest;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking createBooking(BookingRequest bookingRequest, User user);
    Booking save(Booking booking);
    Optional<Booking> findById(Long id);
    List<Booking> findByUser(User user);
    List<Booking> findAll();
    List<Booking> findAllActiveBookings();
    List<Booking> findActiveBookingsByUser(User user);
    void deleteById(Long id);
    void cancelBooking(Long id);
    Booking updateBooking(Booking booking);
    void deleteBooking(Long id);
}
