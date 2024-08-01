package com.values.appointments.service;

import com.values.appointments.repository.UserRepository;
import com.values.appointments.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int verifyUser(String email, String password) {
        try {
            return userRepository.verifyUser(email, password);
        } catch (DataAccessException e) {
            logger.error("Error verifying user with email: {}", email, e);
            return 0;
        }
    }

    @Override
    public int createUser(String email, String phone, String password) {
        return userRepository.createUser(email, phone, password);
    }
}
