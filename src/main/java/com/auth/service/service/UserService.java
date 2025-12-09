package com.auth.service.service;

import com.auth.service.model.User;
import com.auth.service.model.userDTO;
import com.auth.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;


    public User salvar(userDTO us){
               User user  = new User();
               user.setNome(us.getNome());

      return  this.userRepository.save(user);
    }
}
