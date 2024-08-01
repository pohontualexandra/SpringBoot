package com.values.AppointmentsJPA.service;

import com.values.AppointmentsJPA.model.User;
import com.values.AppointmentsJPA.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public long selectUserId(String email){
        try {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return user.getUserId();
            } else {
                throw new IllegalArgumentException("User with email " + email + " not found");
            }
        } catch (Exception e) {
            logger.error("Error retrieving user ID for email: {}", email, e);
            throw e; // Or handle it as per your application's error handling strategy
        }
    }
    @Override
    public boolean verifyUser(String email, String password) {
        try {
            return userRepository.findByEmailAndPassword(email, password) != null;
        } catch (DataAccessException e) {
            logger.error("Error verifying user with email: {}", email, e);
            return false;
        }
    }

    @Override
    public boolean createUser(String email, String phone, String password) {
        try {
            if (userRepository.existsByEmail(email)) {
                return false;
            }
            User user = new User();
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } catch (DataAccessException e) {
            logger.error("Error creating user with email: {}", email, e);
            return false;
        }
    }
}
