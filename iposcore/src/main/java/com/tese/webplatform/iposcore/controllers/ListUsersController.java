package com.tese.webplatform.iposcore.controllers;

import com.tese.webplatform.iposcore.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import com.tese.webplatform.iposcore.errors.UserNotFoundException;
import com.tese.webplatform.iposcore.models.*;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


 
import java.io.*;


@RestController
@CrossOrigin
public class ListUsersController {

    @Autowired
    public UserRepository usr;

    public ListUsersController(UserRepository userRepository) {
        this.usr = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return usr.findAll();
    }

    @GetMapping("users/user/{id}")
    public User findUsrById(@PathVariable Long id) {
        return usr.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @GetMapping(value = "users/users/{username}")
    public Optional<User> findByUsername(String username) {
        return usr.findByUsername(username);
    }

    @PutMapping(value = "/users/{id}")
    public User updateUserInfo(@PathVariable Long id, @RequestBody User newUser) {
        User u = usr.findById(id).orElseThrow(UserNotFoundException::new);

        // u.setId(newUser.getId());
        u.setEmail(newUser.getEmail());
        u.setName(newUser.getName());
        u.setUsername(newUser.getUsername());
        u.setPassword(newUser.getPassword());
        u.setRoles(newUser.getRoles());
        u.setPhotos(newUser.getPhotos());

        return usr.save(u);
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        usr.findById(id).orElseThrow(UserNotFoundException::new);
        usr.deleteById(id);
    }

    @PutMapping(value = "/users/profile/{id}")
    public User updateUserProfile(@PathVariable Long id, @RequestBody User newUser, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        
        User u = usr.findById(id).orElseThrow(UserNotFoundException::new);

        // String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());


        u.setEmail(newUser.getEmail());
        u.setName(newUser.getName());
        u.setUsername(newUser.getUsername());
        u.setPassword(newUser.getPassword());
        u.setRoles(newUser.getRoles());
        u.setPhotos(newUser.getPhotos());

        return usr.save(u);
    }


}