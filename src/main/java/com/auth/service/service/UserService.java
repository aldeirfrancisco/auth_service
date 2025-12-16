package com.auth.service.service;

import com.auth.service.model.User;
import com.auth.service.model.userDTO;
import com.auth.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User salvar(userDTO us){
               User user  = new User();
               user.setEmail(us.getEmail());
               user.setSenha(this.passwordEncoder.encode(us.getSenha()));

      return  this.userRepository.save(user);
    }
}
