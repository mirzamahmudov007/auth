package com.example.javaProject1.service;

import com.example.javaProject1.entity.User;
import com.example.javaProject1.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {

    private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existUsername(String username){
       if (userRepository.existsByUsername(username)){
           System.out.println("Bu username band");
           return false;
       }
       return true;
    }
}
