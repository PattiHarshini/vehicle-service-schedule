package com.example.demo.Service;

import com.example.demo.Entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean emailExists(String email);
    boolean usernameExists(String username);
    void saveUser(User user);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long id);
    boolean hasRelatedBookings(Long userId);
}
