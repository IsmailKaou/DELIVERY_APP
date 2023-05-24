package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.model.Role;
import com.ensa.deliveryapp.model.User;
import com.ensa.deliveryapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   public void initAdminUsers(){
       User user = new User();
       user.setEmail("admin@admin.com");
       user.setName("admin");
       user.setPassword(passwordEncoder.encode("admin"));
       user.setRole(Role.ADMIN);
       userRepository.save(user);
   }
}
