package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.Adress;
import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
/*
    User admin=new User("Mihai", "ahd",new Adress("Pitesti","independentei",4),
            "mihai@ymail.com", Set.of(Roles.ADMIN, Roles.EXPEDITOR,Roles.EDITOR));
    User client=new User("Mihela", "ahd",new Adress("Pitesti","independentei",4),
            "mihai@ymail.com", Set.of(Roles.CLIENT));
    userRepository.save(admin);
    userRepository.save(client);*/
}
 }