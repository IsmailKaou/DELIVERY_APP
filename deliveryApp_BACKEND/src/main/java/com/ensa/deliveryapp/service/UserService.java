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

       var user = userRepository.findByRole(Role.ADMIN);
       if(!user.isPresent()){
           User admin=new User();
           admin.setEmail("admin@admin.com");
           admin.setName("admin");
           admin.setPassword(passwordEncoder.encode("admin"));
           admin.setRole(Role.ADMIN);
       userRepository.save(admin);
   }}
}
