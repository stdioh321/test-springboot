package com.devsuperior.userdept.controller;

import com.devsuperior.userdept.controller.dto.UserCreateForm;
import com.devsuperior.userdept.entities.Departament;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.DepartmentRepository;
import com.devsuperior.userdept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController()
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody User user) {
        User createdUser = userRepository.save(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putById(@PathVariable Long id, @Valid @RequestBody User user) {
        return userRepository.findById(id).map((currUser) -> {
            user.setId(id);
            return ResponseEntity.ok(userRepository.save(user));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }
}
