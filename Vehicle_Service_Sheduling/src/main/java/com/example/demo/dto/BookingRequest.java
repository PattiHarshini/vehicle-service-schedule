package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

public class BookingRequest {

    @NotBlank(message = "Vehicle brand is required")
    private String vehicleBrand;

    @NotBlank(message = "Vehicle model is required")
    private String vehicleModel;

    @NotBlank(message = "Vehicle registration number is required")
    private String vehicleRegistrationNumber;

    @NotNull(message = "Service type is required")
    private Long serviceTypeId;

    @NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date must be in the present or future")
    private LocalDateTime bookingDate;

    private String notes;

    // Getters
    public String getVehicleBrand() { return vehicleBrand; }
    public String getVehicleModel() { return vehicleModel; }
    public String getVehicleRegistrationNumber() { return vehicleRegistrationNumber; }
    public Long getServiceTypeId() { return serviceTypeId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public String getNotes() { return notes; }

    // Setters
    public void setVehicleBrand(String vehicleBrand) { this.vehicleBrand = vehicleBrand; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) { this.vehicleRegistrationNumber = vehicleRegistrationNumber; }
    public void setServiceTypeId(Long serviceTypeId) { this.serviceTypeId = serviceTypeId; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public void setNotes(String notes) { this.notes = notes; }
}
