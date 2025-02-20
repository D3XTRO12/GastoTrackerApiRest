package com.d3xtro.GastoTracker.Services;

import com.d3xtro.GastoTracker.DTOs.UserDTO;
import com.d3xtro.GastoTracker.Models.User;
import com.d3xtro.GastoTracker.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepo.findAll();
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByName(String name){
        return userRepo.findByName(name);
    }
    public String addUser(UserDTO userDTO){
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }
        User user = new User();
        user.setName(userDTO.getName());
        userRepo.save(user);
        return "User added successfully";
    }
}
