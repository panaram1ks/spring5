package com.dmdev.spring.service;

import com.dmdev.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final UserRepository userRepository;

    public CompanyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
