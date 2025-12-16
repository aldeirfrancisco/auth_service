package com.auth.service.controller;

import com.auth.service.model.LoginRequest;
import com.auth.service.model.User;
import com.auth.service.model.UserResponse;
import com.auth.service.model.userDTO;
import com.auth.service.securit.JwtUtil;
import com.auth.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class authController {

    private UserService userService;

    @GetMapping
    public String getString() {
        return "Aldeir francisco da Silva 2 : ";
    }

    @PostMapping("/cadastro")
    public UserResponse salvar(@RequestBody userDTO user) {
        return this.userService.salvar(user);
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
       return userService.login(request.email(), request.senha());

    }
}


