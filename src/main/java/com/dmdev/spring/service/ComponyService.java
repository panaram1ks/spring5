package com.dmdev.spring.service;

import com.dmdev.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ComponyService {
    private final UserRepository userRepository;

    public ComponyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
