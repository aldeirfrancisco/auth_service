package com.auth.service.service;

import com.auth.service.model.Roles;
import com.auth.service.model.User;
import com.auth.service.model.UserResponse;
import com.auth.service.model.userDTO;
import com.auth.service.repository.UserRepository;
import com.auth.service.securit.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse salvar(userDTO us){

        User user  = new User();
         this.repository.findByEmail(us.getEmail())
                .ifPresent( u -> {
                   throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,  "Usuário já existe.");
                });

                user.setEmail(us.getEmail());
                user.setSenha(this.passwordEncoder.encode(us.getSenha()));
                user.setRole(Roles.ADMIN.name());
              var usr =this.repository.save(user);
       return new UserResponse(usr.getEmail(), usr.getRole());
    }

    public  ResponseEntity<?> login(String email, String senha) {
        User usuario = this.repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return ResponseEntity.ok(Map.of("token", JwtUtil.generateToken(usuario)));

    }


}
