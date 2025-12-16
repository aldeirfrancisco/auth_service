package com.auth.service.model;

public enum Roles {
    ADMIN("Admin");
    private final String nome;

    Roles(String nome){
        this.nome = nome;
    }

    private String getNome(){
        return  this.nome;
    }
}
