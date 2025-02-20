package com.d3xtro.GastoTracker.Resources;

import com.d3xtro.GastoTracker.DTOs.UserDTO;
import com.d3xtro.GastoTracker.Services.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchUser(@RequestParam(required = false) Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String query){
        try{
            if (query==null){
                return ResponseEntity.badRequest().body("Query parameter is required");
            }
            switch (query.toLowerCase()){
                case "all":
                    return ResponseEntity.ok(userService.getAllUsers());
                case "id":
                    if (id==null){
                        return ResponseEntity.badRequest().body("Id parameter is required");
                    }
                    return ResponseEntity.ok(userService.getUserById(id));
                case "name":
                    if (name==null){
                        return ResponseEntity.badRequest().body("Name parameter is required");
                    }
                    return ResponseEntity.ok(userService.getUserByName(name));
                default:
                    return ResponseEntity.badRequest().body("Invalid query parameter");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        try{
            userService.addUser(userDTO);
            return ResponseEntity.ok("User added successfully");
            
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
