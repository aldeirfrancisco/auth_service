package com.auth.service.controller;

import com.auth.service.model.User;
import com.auth.service.model.userDTO;
import com.auth.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class authController {

    private UserService userService;

    @GetMapping
    public  String getString(){
        return "Aldeir francisco da Silva 2";
    }

    @PostMapping()
    public User salvar(@RequestBody userDTO user){
        return this.userService.salvar(user);
    }
}
