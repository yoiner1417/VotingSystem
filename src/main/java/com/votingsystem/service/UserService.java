package com.votingsystem.service;

import com.votingsystem.model.User;
import com.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyectamos PasswordEncoder

    public User findByEmail(String correo) {
        return userRepository.findByCorreo(correo).orElse(null);
    }


    public User save(User user) {
        // Encriptamos la contraseña antes de guardarla
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        return userRepository.save(user);
    }

    public Object findAll() {
        return null;
    }
}


