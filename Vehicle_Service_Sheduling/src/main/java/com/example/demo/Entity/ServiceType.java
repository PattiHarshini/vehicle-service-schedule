package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Service name cannot be blank")
    @Size(min = 3, max = 100, message = "Service name must be between 3 and 100 characters")
    private String name;

    @PositiveOrZero(message = "Price must not be a negative value")
    private double price;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description cannot be more than 255 characters")
    private String description;

    @NotBlank(message = "Duration cannot be blank")
    private String duration; // Added field for duration if used in UI
    
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean available = true; // Added field for availability check

    // Explicit getter and setter for available field
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Other getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
