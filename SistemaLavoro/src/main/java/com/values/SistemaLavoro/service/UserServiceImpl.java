package com.values.SistemaLavoro.service;

import com.values.SistemaLavoro.model.User;
import com.values.SistemaLavoro.repository.RoleRepository;
import com.values.SistemaLavoro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setNome(request.getNome());
        user.setCognome(request.getCognome());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);
    }
}
