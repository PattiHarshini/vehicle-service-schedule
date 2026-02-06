package com.example.demo.Repository;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.User;
import com.example.demo.Entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByServiceType(ServiceType serviceType);
    boolean existsByUser(User user);
    boolean existsByServiceType(ServiceType serviceType);
    List<Booking> findByStatus(Booking.BookingStatus status);
    List<Booking> findByStatusNot(Booking.BookingStatus status);
    List<Booking> findByUserAndStatusNot(User user, Booking.BookingStatus status);
}
